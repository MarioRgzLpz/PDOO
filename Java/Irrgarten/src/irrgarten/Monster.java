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
    private static int INITIAL_HEALTH = 5;
    private String name;
    private float intelligence;
    private float strength;
    private float health;
    private int row;
    private int col;
    
    
    public Monster(String name, float intelligence, float strength){
        this.name = name;
        this.intelligence = intelligence;
        this.strength = strength;
        health = INITIAL_HEALTH;
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
        return "\n" + "Name: " + name + "\n" + "Intelligence: " + intelligence + "\n" + "Strength: " + strength + "\n" + "Health: " + health + "\n" + "Position: (" + row + "," + col + ")" + "\n";
    }
    
    private void gotWounded(){
        health -= 1;
    }
    
}
