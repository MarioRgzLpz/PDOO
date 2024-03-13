/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;
import java.util.ArrayList;

/**
 *
 * @author mariorl
 */
public class Player {
    private static int MAX_WEAPONS = 2;
    private static int MAX_SHIELDS = 3;
    private static int MAX_HEALTH = 10;
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
        if (health <= 0)
            return true;
        else
            return false;
    }
    
    public Directions move(Directions direction, Directions[] validMoves){
        throw new UnsupportedOperationException();
    }
    
    public float attack() {
        return strength + this.sumWeapons();
    }
    
    public boolean defend(float receivedAttack){
        return this.manageHit(receivedAttack);
    }
    
    public void receiveReward(){
        throw new UnsupportedOperationException();
    }

    public String toString(){
        return "Name: " + name + "\n" + "Number: " + number + "\n" + "Intelligence: " + intelligence + "\n" 
                + "Strength: " + strength + "\n" + "Health: " + health + "\n" + "Position: R_: " + row + " C_: " + col 
                + "\n" + "ConsecutiveHits: " + consecutiveHits + "\n" + "Weapons: " + weapons.toString() + "\n" + "Shield: " + shields.toString();
    }    
    
    private void receiveWeapon(Weapon w){
        throw new UnsupportedOperationException();
    }
    
    private void receiveShield(Shield s){
        throw new UnsupportedOperationException();
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
         throw new UnsupportedOperationException();
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
