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

public class Weapon {
    
    private float power;
    private int uses;
    
    public Weapon(float power, int uses){
        this.power = power;
        this.uses = uses;
    }
    
    public float attack(){
        if(uses > 0){
            uses -= 1;
            return power;
        } else
            return 0;
    }
    
    public String toString(){
        DecimalFormat formato = new DecimalFormat("#.###");
        String formatpower = formato.format(power);
        return "W[" + formatpower + "," + uses + "]";
    }
    
    public boolean discard() {
        return Dice.discardElement(uses);
    }
}
