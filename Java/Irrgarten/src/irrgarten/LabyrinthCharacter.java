/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author mariorl
 */
public abstract class LabyrinthCharacter {
    private String name;
    private float intelligence;
    private float strength;
    private float health;
    private int row;
    private int col;

    public LabyrinthCharacter(String name, float intelligence, float strength, float health){
        this.name = name;
        this.intelligence = intelligence;
        this.strength = strength;
        this.health = health;
    }
    
    public LabyrinthCharacter(LabyrinthCharacter other){
        this.name = other.name;
        this.intelligence = other.intelligence;
        this.strength = other.strength;
        this.health = other.health;
        this.row = other.row;
        this.col = other.col;
    }

    /**
     * Checks if the player is dead.
     * @return True if the player's health is less than or equal to 0, false otherwise.
     */
    public boolean dead(){
        return health <= 0;
    }
    
    /**
     * Gets the row index of the player's position in the labyrinth.
     * @return The row index.
     */
    public int getRow(){
        return row;
    }
    
    /**
     * Gets the column index of the player's position in the labyrinth.
     * @return The column index.
     */
    public int getCol(){
        return col;
    }
    
    protected String getName() {
        return name;
    }

    protected float getIntelligence() {
        return intelligence;
    }

    protected float getStrength() {
        return strength;
    }

    protected float getHealth() {
        return health;
    }
    
    protected void setHealth(float health) {
        this.health = health;
    } 
    
    /**
     * Sets the position of the character in the labyrinth.
     * @param r The row index of the position.
     * @param c The column index of the position.
     */
    public void setPos(int r, int c) {
        row = r;
        col = c;
    }
    
    /**
     * Provides a string representation of the character including its attributes and position.
     * @return A string representing the monster.
     */
    public String toString(){
        return  name + " Intelligence: " + intelligence + " Strength: " + strength + " Health: " + health + " Position: (" + row + "," + col + ")";
    }
    
    
    /**
     * Reduces the health of the character when it gets wounded.
     */
    protected void gotWounded(){
        health -= 1;
    }
    
    public abstract float attack();
    
    public abstract boolean defend(float attack);
}
