/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;


/**
 * Represents a shield in the game.
 * It has attributes such as protection level and remaining uses.
 * Provides methods for protecting against attacks, obtaining a string representation, and discarding.
 * @author mariorgzlpz
 */
public class Shield extends CombatElement{
    
    /**
     * Constructor to initialize the shield with protection level and remaining uses.
     * @param protection The protection level of the shield.
     * @param uses The remaining uses of the shield.
     */
    public Shield(float protection, int uses){
        super(protection,uses);
    }
    
    /**
     * Protects against an attack and decreases the remaining uses.
     * @return The protection level if the shield has remaining uses, otherwise 0.
     */
    public float protect(){
        return this.produceEffect();
    }
    
    public String toString(){
        return "S" + super.toString();
    }
}