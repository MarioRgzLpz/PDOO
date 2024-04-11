/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 * Represents a monster in the game.
 * It has attributes such as name, intelligence, strength, and health.
 * Provides methods for attacking, defending, and checking if the monster is dead.
 * Also, it can set its position and provide a string representation of itself.
 * @author mariorgzlpz
 */
public class Monster {
    private static int INITIAL_HEALTH = 5;
    private String name;
    private float intelligence;
    private float strength;
    private float health;
    private int row;
    private int col;
    
    /**
     * Constructor to initialize the monster with a name, intelligence, and strength.
     * @param name The name of the monster.
     * @param intelligence The intelligence level of the monster.
     * @param strength The strength level of the monster.
     */
    public Monster(String name, float intelligence, float strength){
        this.name = name;
        this.intelligence = intelligence;
        this.strength = strength;
        health = INITIAL_HEALTH;
    }
    
    /**
     * Checks if the monster is dead.
     * @return True if the monster's health is less than or equal to 0, false otherwise.
     */
    public boolean dead(){
        if (health <= 0)
            return true;
        else
            return false;
    }
    
    /**
     * Calculates the attack power of the monster.
     * @return The attack power based on the monster's strength.
     */
    public float attack() {
        return Dice.intensity(strength);
    }
    
    /**
     * Defends against an attack received by the monster.
     * @param receivedAttack The attack power received by the monster.
     * @return True if the monster is dead after defending, false otherwise.
     */
    public boolean defend(float receivedAttack){
        boolean isDead = dead();
        if(!isDead){
            float defensiveEnergy = Dice.intensity(intelligence);
            if(defensiveEnergy < receivedAttack){
                gotWounded();
                isDead = dead();
            }
        }
        return isDead;
    }
    
    /**
     * Sets the position of the monster in the labyrinth.
     * @param r The row index of the position.
     * @param c The column index of the position.
     */
    public void setPos(int r, int c) {
        row = r;
        col = c;
    }
    
    /**
     * Provides a string representation of the monster including its attributes and position.
     * @return A string representing the monster.
     */
    public String toString(){
        return "Name: " + name + " Intelligence: " + intelligence + " Strength: " + strength + " Health: " + health + " Position: (" + row + "," + col + ")" + "\n";
    }
    
    /**
     * Reduces the health of the monster when it gets wounded.
     */
    private void gotWounded(){
        health -= 1;
    }
    
}
