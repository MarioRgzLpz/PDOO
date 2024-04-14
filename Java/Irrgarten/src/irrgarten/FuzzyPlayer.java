/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

import java.util.ArrayList;

/**
 *
 * @author mariorl
 */
public class FuzzyPlayer extends Player{
    
    public FuzzyPlayer(Player other){
        super(other);
    }
    
    @Override
    public Directions move(Directions direction, ArrayList<Directions> validMoves){
        Directions directionReturned = super.move(direction, validMoves);
        return Dice.nextStep(directionReturned, validMoves, getIntelligence());
    }
    
    
    /**
     * Calculates the attack power of the player.
     * @return The attack power based on the player's strength and weapons.
     */
    @Override
    public float attack() {
        return Dice.intensity(getStrength()) + this.sumWeapons();
    }
    
    /**
     * Calculates the total defensive energy considering intelligence and shield protection.
     * @return The total defensive energy.
     */
    @Override
    protected float defensiveEnergy(){
         return Dice.intensity(getIntelligence()) + this.sumShields();
    }
    
    @Override
    public String toString(){
        return "Fuzzy" + super.toString();
    }   
}
