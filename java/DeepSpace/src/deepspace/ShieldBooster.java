/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deepspace;

/**
 *
 * @author mariorgzlpz
 */
public class ShieldBooster {
    String name;
    float boost;
    int uses;

    ShieldBooster(String name, float boost, int uses) {
        this.name = name;
        this.boost = boost;
        this.uses = uses;
    }

    ShieldBooster(ShieldBooster s){
        this(s.name, s.boost, s.uses);
    }

    public float getBoost() {
        return boost;
    }

    public int getUses() {
        return uses;
    }
    
    public float useIt(){
        if(uses>0){
            uses-=1;
            return boost;
        }
        else
            return 1.0f;
    }
}
