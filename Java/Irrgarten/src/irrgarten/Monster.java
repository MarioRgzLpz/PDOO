/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 * Represents a monster in the game.
 * It has attributes such as name, intelligence, strength, and health.
 * Provides methods for attacking, defending, and checking if the monster is dead.
 * Also, it can set its position and provide a string representation of itself.
 * @author mariorgzlpz
 */
public class Monster extends LabyrinthCharacter{
    private static int INITIAL_HEALTH = 5;
    
    /**
     * Constructor to initialize the monster with a name, intelligence, and strength.
     * @param name The name of the monster.
     * @param intelligence The intelligence level of the monster.
     * @param strength The strength level of the monster.
     */
    public Monster(String name, float intelligence, float strength){
        super(name, intelligence,strength, INITIAL_HEALTH);
    }
    
    /**
     * Calculates the attack power of the monster.
     * @return The attack power based on the monster's strength.
     */
    public float attack() {
        return Dice.intensity(getStrength());
    }
    
    /**
     * Defends against an attack received by the monster.
     * @param receivedAttack The attack power received by the monster.
     * @return True if the monster is dead after defending, false otherwise.
     */
    public boolean defend(float receivedAttack){
        boolean isDead = dead();
        if(!isDead){
            float defensiveEnergy = Dice.intensity(getIntelligence());
            if(defensiveEnergy < receivedAttack){
                gotWounded();
                isDead = dead();
            }
        }
        return isDead;
    }
}
