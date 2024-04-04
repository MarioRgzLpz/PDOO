/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author mariorl
 */
public class Player {
    private static int MAX_WEAPONS = 2;
    private static int MAX_SHIELDS = 3;
    private static int INITIAL_HEALTH = 10;
    private static int HITS2LOSE = 3;
    private String name;
    private char number;
    private float intelligence;
    private float strength;
    private float health;
    private int row;
    private int col;
    private int consecutiveHits = 0;
    private ArrayList<Weapon> weapons = new ArrayList<>();
    private ArrayList<Shield> shields = new ArrayList<>();
    
    
    public Player(char number, float intelligence, float strength){
        this.name = "Player #" + number;
        this.number = number;
        this.intelligence = intelligence;
        this.strength = strength;
        health = INITIAL_HEALTH;
    }
    
    public void resurrect(){
        weapons.clear();
        shields.clear();
        health = 10;
        consecutiveHits = 0;
    }
    
    public int getRow(){
        return row;
    }
    public int getCol(){
        return col;
    }
    
    public char getNumber(){
        return number;
    }
    
    public void setPos(int r, int c) {
        row = r;
        col = c;
    }
    
    public boolean dead(){
        return health <= 0;
    }
    
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
    
    public float attack() {
        return strength + this.sumWeapons();
    }
    
    public boolean defend(float receivedAttack){
        return this.manageHit(receivedAttack);
    }
    
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
        health += extraHealth;
    }

    public String toString(){
        DecimalFormat formato = new DecimalFormat("#.###");
        String formatstrength = formato.format(strength);
        String formatintelligence = formato.format(intelligence);
        return  "Name: " + name + " Number: " + number + " Intelligence: " + formatintelligence 
                + " Strength: " + formatstrength + " Health: " + health + " Position: (" + row + "," + col + ")"
                + " ConsecutiveHits: " + consecutiveHits + " Weapons: " + weapons.toString() + " Shield: " + shields.toString() + "\n";
    }    
    
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
    
    private Weapon newWeapon() {
        float power = Dice.weaponPower();
        int uses = Dice.usesLeft();
        return new Weapon(power,uses);
    }
    
    private Shield newShield() {
        float power = Dice.shieldPower();
        int uses = Dice.usesLeft();
        return new Shield(power,uses);
    }
    
    private float sumWeapons(){
        float total = 0.0f;
        for(Weapon weapon : weapons){
            total += weapon.attack();
        }
        return total;
    }

    private float sumShields(){
        float total = 0.0f;
        for(Shield shield : shields){
            total += shield.protect();
        }
        return total;
    }
    
    private float defensiveEnergy(){
         return intelligence + this.sumShields();
    }
    
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
    
    private void resetHits(){
        consecutiveHits = 0;
    }
    
    private void gotWounded(){
        health -= 1;
    }
    
    private void incConsecutiveHits(){
        consecutiveHits += 1;
    }
}
