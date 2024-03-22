/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author mariorl
 */
public class Shield {
    private float protection;
    private int uses;
    
    public Shield(float protection, int uses){
        this.protection = protection;
        this.uses = uses;
    }
    
    public float protect(){
        if (uses > 0) {
            uses -= 1;
            return protection;
        }
        else
            return 0;
    }
    
    public String toString(){
        return "S[" + protection + "," + uses + "]";
    }
    
    public boolean discard() {
        return Dice.discardElement(uses);
    }
}
