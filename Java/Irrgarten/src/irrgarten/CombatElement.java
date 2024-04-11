/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

import java.text.DecimalFormat;

/**
 *
 * @author mariorl
 */
public abstract class CombatElement {
    private float effect;
    private int uses;
    
    public CombatElement(float effect, int uses){
        this.effect = effect;
        this.uses = uses;
    }
    
    protected float produceEffect(){
        if (uses > 0) {
            uses -= 1;
            return effect;
        }
        else
            return 0;
    }
    
    /**
     * Provides a string representation of the combatElement including its effect level and remaining uses.
     * @return A string representing the combatElement.
     */
    public String toString(){
        DecimalFormat formato = new DecimalFormat("#.###");
        String formateffect = formato.format(effect);
        return "[" + formateffect + "," + uses + "]";
    }
    
    /**
     * Checks if the shield should be discarded based on its remaining uses.
     * @return True if the shield should be discarded, false otherwise.
     */
    public boolean discard() {
        return Dice.discardElement(uses);
    }    
    
    
}
