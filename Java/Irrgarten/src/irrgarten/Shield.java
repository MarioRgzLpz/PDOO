/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;
import java.text.DecimalFormat;


/**
 * Represents a shield in the game.
 * It has attributes such as protection level and remaining uses.
 * Provides methods for protecting against attacks, obtaining a string representation, and discarding.
 * @author mariorgzlpz
 */
public class Shield {
    private float protection;
    private int uses;
    
    /**
     * Constructor to initialize the shield with protection level and remaining uses.
     * @param protection The protection level of the shield.
     * @param uses The remaining uses of the shield.
     */
    public Shield(float protection, int uses){
        this.protection = protection;
        this.uses = uses;
    }
    
    /**
     * Protects against an attack and decreases the remaining uses.
     * @return The protection level if the shield has remaining uses, otherwise 0.
     */
    public float protect(){
        if (uses > 0) {
            uses -= 1;
            return protection;
        }
        else
            return 0;
    }
    
    /**
     * Provides a string representation of the shield including its protection level and remaining uses.
     * @return A string representing the shield.
     */
    public String toString(){
        DecimalFormat formato = new DecimalFormat("#.###");
        String formatprotection = formato.format(protection);
        return "S[" + formatprotection + "," + uses + "]";
    }
    
    /**
     * Checks if the shield should be discarded based on its remaining uses.
     * @return True if the shield should be discarded, false otherwise.
     */
    public boolean discard() {
        return Dice.discardElement(uses);
    }
}