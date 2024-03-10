/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author mariorl
 */
public class Monster {
    private String name;
    private float intelligence;
    private float strength;
    private float health;
    private int row;
    private int col;
    
    
    public Monster(String n, float i, float s){
        name = n;
        intelligence = i;
        strength = s;
    }
    
    public boolean dead(){
        if (health <= 0)
            return true;
        else
            return false;
    }
    
    public float attack() {
        return Dice.intensity(strength);
    }
    
    public boolean defend(float receivedAttack){
        throw new UnsupportedOperationException();
    }
    
    public void setPos(int r, int c) {
        row = r;
        col = c;
    }
    
    public String toString(){
        return "Name:" + name + "\n" + "Intelligence:" + intelligence + "\n" + "Strength:" + strength + "\n" + "Health:" + health + "\n" + "Position: R_" + row + " C_:" + col;
    }
    
    private void gotWounded(){
        health -= 1;
    }
    
}
