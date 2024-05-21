/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represents a player in the game.
 * It has attributes such as name, number, intelligence, strength, health, and inventory of weapons and shields.
 * Provides methods for movement, attacking, defending, receiving rewards, and managing hits.
 * @author mariorgzlpz
 */
public class Player extends LabyrinthCharacter{
    private static int MAX_WEAPONS = 2;
    private static int MAX_SHIELDS = 3;
    private static int INITIAL_HEALTH = 10;
    private static int HITS2LOSE = 3;
    private char number;
    private int consecutiveHits = 0;
    private ArrayList<Weapon> weapons = new ArrayList<>();
    private ArrayList<Shield> shields = new ArrayList<>();
    private ShieldCardDeck shieldCardDeck = new ShieldCardDeck();
    private WeaponCardDeck weaponCardDeck = new WeaponCardDeck();
    /**
     * Constructor to initialize the player with a number, intelligence, and strength.
     * @param number The number representing the player.
     * @param intelligence The intelligence level of the player.
     * @param strength The strength level of the player.
     */
    public Player(char number, float intelligence, float strength){
        super("Player #" + number, intelligence, strength, INITIAL_HEALTH);
        this.number = number;
    }

    public Player(Player other){
        super(other);
        this.number = other.number;
        this.weapons = other.weapons;
        this.shields = other.shields;
        //this.shieldCardDeck = other.shieldCardDeck;
        //this.weaponCardDeck = other.weaponCardDeck;
    }
    
    /**
     * Resets the player's inventory and health when resurrecting.
     */
    public void resurrect(){
        weapons.clear();
        shields.clear();
        setHealth(10);
        consecutiveHits = 0;
    }
    
    /**
     * Gets the number representing the player.
     * @return The player's number.
     */
    public char getNumber(){
        return number;
    }
    
    /**
     * Determines the direction of movement for the player based on valid moves.
     * @param direction The preferred direction of movement.
     * @param validMoves The list of valid directions for movement.
     * @return The direction to move, considering the preferred direction and valid moves.
     */
    public Directions move(Directions direction, ArrayList<Directions> validMoves){
        int size = validMoves.size();
        boolean contained = validMoves.contains(direction);
        
        if(size > 0 && !contained){
            Directions firstElement = validMoves.get(0);
            return firstElement;
        }
        else{
            return direction;
        }
    }
    
    /**
     * Calculates the attack power of the player.
     * @return The attack power based on the player's strength and weapons.
     */
    @Override
    public float attack() {
        return getStrength() + this.sumWeapons();
    }
    
    /**
     * Defends against an attack received by the player.
     * @param receivedAttack The attack power received by the player.
     * @return True if the player loses after defending, false otherwise.
     */
    @Override
    public boolean defend(float receivedAttack){
        return this.manageHit(receivedAttack);
    }
    
    /**
     * Receives rewards including weapons, shields, and extra health.
     */
    public void receiveReward(){
        int wReward = Dice.weaponsReward();
        int sReward = Dice.shieldsReward();
        
        while(wReward >= 1){
            Weapon wnew = newWeapon();
            receiveWeapon(wnew);
            wReward-=1;
        }
        while(sReward >= 1){
            Shield snew = newShield();
            receiveShield(snew);
            sReward-=1;
        }
        int extraHealth = Dice.healthReward();
        setHealth(getHealth() + extraHealth);
    }

    /**
     * Provides a string representation of the player including its attributes and inventory.
     * @return A string representing the player.
     */
    @Override
    public String toString(){
        return super.toString() + " ConsecutiveHits: " + consecutiveHits + " Weapons: " + weapons.toString() + " Shields: " + shields.toString();
    }  
    
    /**
     * Receives a new weapon, discards old weapons if necessary, and adds the new weapon to the inventory.
     * @param w The new weapon to receive.
     */
    private void receiveWeapon(Weapon w){
        Iterator<Weapon> iterator = weapons.iterator();
        while (iterator.hasNext()) {
            Weapon weapon = iterator.next();
            boolean discard = weapon.discard();
            if (discard) {
                iterator.remove(); // Remove the weapon using the iterator
            }
        }

        int size = weapons.size();
        if (size < MAX_WEAPONS) {
            weapons.add(w);
        }
    }
    
    /**
     * Receives a new shield, discards old shields if necessary, and adds the new shield to the inventory.
     * @param s The new shield to receive.
     */
    private void receiveShield(Shield s){
        Iterator<Shield> iterator = shields.iterator();
        while (iterator.hasNext()) {
            Shield shield = iterator.next();
            boolean discard = shield.discard();
            if (discard) {
                iterator.remove(); // Remove the shield using the iterator
            }
        }

        int size = shields.size();
        if (size < MAX_SHIELDS) {
            shields.add(s);
        }
    }
    
    /**
     * Creates a new weapon with random power and remaining uses.
     * @return A new weapon object.
     */
    private Weapon newWeapon() {
        return weaponCardDeck.nextCard();
    }
    
    /**
     * Creates a new shield with random protection and remaining uses.
     * @return A new shield object.
     */
    private Shield newShield() {
        return shieldCardDeck.nextCard();
    }
    
    /**
     * Calculates the total attack power bonus from equipped weapons.
     * @return The total attack power bonus from weapons.
     */
    protected float sumWeapons(){
        float total = 0.0f;
        for(Weapon weapon : weapons){
            total += weapon.attack();
        }
        return total;
    }

    /**
     * Calculates the total protection bonus from equipped shields.
     * @return The total protection bonus from shields.
     */
    protected float sumShields(){
        float total = 0.0f;
        for(Shield shield : shields){
            total += shield.protect();
        }
        return total;
    }
    
    /**
     * Calculates the total defensive energy considering intelligence and shield protection.
     * @return The total defensive energy.
     */
    protected float defensiveEnergy(){
         return getIntelligence() + this.sumShields();
    }
    
    /**
     * Manages hits received from opponents, updates consecutive hits, and checks for player loss conditions.
     * @param receivedAttack The attack power received from an opponent.
     * @return True if the player loses the hit, false otherwise.
     */
    private boolean manageHit(float receivedAttack){
         float defense = defensiveEnergy();
         if(defense < receivedAttack){
             gotWounded();
             incConsecutiveHits();
         }
         else{
             resetHits();
         }
         
         boolean lose = false;
         
         if(consecutiveHits == HITS2LOSE || dead()){
             resetHits();
             lose = true;
         }
         else {
             lose = false;
         }
         
         return lose;
    }
    
    /**
     * Resets the consecutive hits counter.
     */
    private void resetHits(){
        consecutiveHits = 0;
    }
    
    /**
     * Increments the consecutive hits counter.
     */
    private void incConsecutiveHits(){
        consecutiveHits += 1;
    }
}
