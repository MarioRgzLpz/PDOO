/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

import java.util.Random;
import java.util.ArrayList;

/**
 * Utility class for simulating dice rolls and game mechanics.
 * @author mariorgzlpz
 */
public class Dice {
    
    private static final int MAX_USES = 5; // Maximum number of uses for weapons and shields
    private static final float MAX_INTELLIGENCE = 10.0f; // Maximum value for intelligence of players and monsters
    private static final float MAX_STRENGTH = 10.0f; // Maximum value for strength of players and monsters
    private static final float RESURRECT_PROB = 0.3f; // Probability of a player being resurrected each turn
    private static final int WEAPONS_REWARD = 2; // Maximum number of weapons received when winning a combat
    private static final int SHIELDS_REWARD = 3; // Maximum number of shields received when winning a combat
    private static final int HEALTH_REWARD = 5; // Maximum number of health units received when winning a combat
    private static final int MAX_ATTACK = 3; // Maximum power of weapons
    private static final int MAX_SHIELD = 2; // Maximum power of shields
    
    private static final Random generator = new Random();
    
    /**
     * Generates a random positive integer less than the given maximum.
     * @param max The maximum value (exclusive) for the random number.
     * @return A random positive integer less than max.
     */
    public static int randomPos(int max) {
        return generator.nextInt(max);
    }
    
    /**
     * Determines which player starts the game.
     * @param nplayers The total number of players.
     * @return The index of the player who starts the game.
     */
    public static int whoStarts(int nplayers) {
        return generator.nextInt(nplayers);
    }
    
    /**
     * Generates a random intelligence value for players and monsters.
     * @return A random intelligence value.
     */
    public static float randomIntelligence() {
        return generator.nextFloat() * MAX_INTELLIGENCE;
    }
    
    /**
     * Generates a random strength value for players and monsters.
     * @return A random strength value.
     */
    public static float randomStrenght() {
        return generator.nextFloat() * MAX_STRENGTH;
    }
    
    /**
     * Determines if a player is resurrected based on a predefined probability.
     * @return True if the player is resurrected, false otherwise.
     */
    public static boolean resurrectPlayer() {
        return generator.nextFloat() < RESURRECT_PROB;
    }
    
    /**
     * Determines the number of weapons received as a reward for winning a combat.
     * @return The number of weapons received.
     */
    public static int weaponsReward() {
        return generator.nextInt(WEAPONS_REWARD + 1);
    }
    
    /**
     * Determines the number of shields received as a reward for winning a combat.
     * @return The number of shields received.
     */
    public static int shieldsReward() {
        return generator.nextInt(SHIELDS_REWARD + 1);
    }
    
    /**
     * Determines the number of health units received as a reward for winning a combat.
     * @return The number of health units received.
     */
    public static int healthReward() {
        return generator.nextInt(HEALTH_REWARD + 1);
    }
    
    /**
     * Generates a random power value for weapons.
     * @return A random weapon power value.
     */
    public static float weaponPower() {
        return generator.nextFloat() * MAX_ATTACK;
    }
 
    /**
     * Generates a random power value for shields.
     * @return A random shield power value.
     */
    public static float shieldPower() {
        return generator.nextFloat() * MAX_SHIELD;
    }
    
    /**
     * Determines the remaining uses for weapons and shields.
     * @return The number of uses left.
     */
    public static int usesLeft() {
        return generator.nextInt(MAX_USES + 1);
    }
    
    /**
     * Determines the intensity of an action based on a competence level.
     * @param competence The competence level.
     * @return The intensity of the action.
     */
    public static float intensity(float competence) {
        return generator.nextFloat() * competence;
    }
    
    /**
     * Determines if an element should be discarded based on the number of uses left.
     * @param usesLeft The number of uses left for the element.
     * @return True if the element should be discarded, false otherwise.
     */
    public static boolean discardElement(int usesLeft) {
        float discardProbability = (float) (MAX_USES - usesLeft) / MAX_USES;
        return generator.nextFloat() < discardProbability;
    }
    
    public static Directions nextStep(Directions preference, ArrayList<Directions> validMoves, float intelligence){
        Directions choosen_direction = null;
        if (generator.nextFloat() <= (intelligence*0.1)){
            choosen_direction = preference;
        }
        else {
            int tam = validMoves.size();
            float prob = 1.0f /tam;
            float random = generator.nextFloat();
            for(int i = 0; i < tam ; i++){
                if(random >= prob*i && random < prob*(i+1)){
                    choosen_direction = validMoves.get(i);
                }
            }
        }
        return choosen_direction;
    }
}

