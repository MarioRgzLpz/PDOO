/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deepspace;

/**
 *
 * @author mariorgzlpz
 */
public class Weapon {
    private String name;
    private WeaponType type;
    private int uses;

    Weapon(String name, WeaponType type, int uses) {
        this.name = name;
        this.type = type;
        this.uses = uses;
    }

    Weapon(Weapon w) {
        this(w.name, w.type, w.uses);
    }

    public String getName() {
        return name;
    }

    public WeaponType getType() {
        return type;
    }

    public int getUses() {
        return uses;
    }
    
    public float power(){
        return type.getPower();
    }
    
    public float useIt(){
        if(uses>0){
            uses-=1;
            return power();
        }
        else
            return 1.0f;
    }    
}
