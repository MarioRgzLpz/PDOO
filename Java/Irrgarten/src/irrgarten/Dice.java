/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

import java.util.Random;

/**
 *
 * @author mariorl
 */
public class Dice {
    
    private static final int MAX_USES = 5; //Número maximo de usos de armas y escudos
    private static final float MAX_INTELLIGENCE = 10.0f; //Valor máximo para la inteligencia de jugadores y monstruos
    private static final float MAX_STRENGTH = 10.0f; //Valor máximo para la fuerza de jugadores y monstruos
    private static final float RESURRECT_PROB = 0.3f; //Probabilidad de que un jugador sea resucitado en cada turno
    private static final int WEAPONS_REWARD = 2; //Número maximo de armas recibidos al ganar un combate
    private static final int SHIELDS_REWARD = 3; //Número maximo de escudos recibidos al ganar un combate
    private static final int HEALTH_REWARD = 5; //Número maximo de unidades de salud recibidas al ganar un combate
    private static final int MAX_ATTACK = 3; //Maxima potencia de as armas
    private static final int MAX_SHIELD = 2; //Maxima potencia de los escudos
    
    private static final Random generator = new Random();
    
    public int randomPos(int max) {
        return generator.nextInt(max);
    }
    
    public int whoStarts (int nplayers) {
        return generator.nextInt(nplayers);
    }
    
    public float randomIntelligence() {
        return generator.nextFloat()*MAX_INTELLIGENCE;
    }
    
    public float randomStrenght() {
        return generator.nextFloat()*MAX_STRENGTH;
    }
    
    public boolean resurrectPlayer() {
        return generator.nextFloat() < RESURRECT_PROB;
    }
    
    public int weaponsReward() {
        return generator.nextInt(WEAPONS_REWARD + 1);
    }
    
    public int shieldsReward() {
        return generator.nextInt(SHIELDS_REWARD + 1);
    }
    
    public int healthReward() {
        return generator.nextInt(HEALTH_REWARD + 1);
    }
    
    public float weaponPower() {
        return generator.nextFloat()*MAX_ATTACK;
    }
 
    public float shieldPower() {
        return generator.nextFloat()*MAX_SHIELD;
    }
    
    public int usesLeft() {
        return generator.nextInt(MAX_USES + 1);
    }
    
    public float intensity(float competence) {
        return generator.nextFloat()*competence;
    }
    
    public static boolean discardElement(int usesLeft) {
        float probabilidadDiscard = (float)(MAX_USES - usesLeft)/MAX_USES;
        return generator.nextFloat() < probabilidadDiscard ;
    }
}

