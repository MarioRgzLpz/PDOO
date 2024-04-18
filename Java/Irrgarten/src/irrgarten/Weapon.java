/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 * Represents a weapon in the game.
 * It has attributes such as power and remaining uses.
 * Provides methods for attacking, discarding, and obtaining a string representation.
 * @author mariorgzlpz
 */
public class Weapon extends CombatElement {
    /**
     * Constructor to initialize a weapon with a given power and number of uses.
     * @param power The power of the weapon.
     * @param uses The remaining uses of the weapon.
     */
    public Weapon(float power, int uses){
        super(power,uses);
    }
    /**
     * Performs an attack using the weapon and decrements the remaining uses.
     * @return The power of the weapon if it has remaining uses, otherwise 0.
     */
    public float attack(){
        return super.produceEffect();
    } 
    
    public String toString(){
        return "W" + super.toString();
    }
}
