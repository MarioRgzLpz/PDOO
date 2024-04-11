/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;
import java.text.DecimalFormat;

/**
 * Represents a weapon in the game.
 * It has attributes such as power and remaining uses.
 * Provides methods for attacking, discarding, and obtaining a string representation.
 * @author mariorgzlpz
 */
public class Weapon {
    
    private float power; // The power of the weapon
    private int uses;    // The remaining uses of the weapon
    
    /**
     * Constructor to initialize a weapon with a given power and number of uses.
     * @param power The power of the weapon.
     * @param uses The remaining uses of the weapon.
     */
    public Weapon(float power, int uses){
        this.power = power;
        this.uses = uses;
    }
    
    /**
     * Performs an attack using the weapon and decrements the remaining uses.
     * @return The power of the weapon if it has remaining uses, otherwise 0.
     */
    public float attack(){
        if(uses > 0){
            uses -= 1;
            return power;
        } else
            return 0;
    }
    
    /**
     * Provides a string representation of the weapon including power and remaining uses.
     * @return A string representing the weapon.
     */
    public String toString(){
        DecimalFormat formato = new DecimalFormat("#.###");
        String formatpower = formato.format(power);
        return "W[" + formatpower + "," + uses + "]";
    }
    
    /**
     * Checks if the weapon should be discarded based on its remaining uses.
     * @return True if the weapon should be discarded, otherwise false.
     */
    public boolean discard() {
        return Dice.discardElement(uses);
    }
}
