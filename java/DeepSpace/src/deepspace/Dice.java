/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deepspace;
import java.util.Random;

/**
 *
 * @author mariorgzlpz
 */
public class Dice {
    private final float NHANGARSPROB=0.25f;
    private final float NSHIELDSPROB=0.25f;
    private final float NWEAPONSPROB=0.25f;
    private final float FIRSTSHOTPROB=0.25f;
    private Random generator=new Random();
    
    int initWithNHangars(){
        if(generator.nextFloat() < NHANGARSPROB)
            return 0;
        else
            return 1;
    }
    
    int initWithNWEAPONS(){
        float weaponprob = generator.nextFloat();
        if(weaponprob < NWEAPONSPROB)
            return 1;
        else if(weaponprob < 2*NWEAPONSPROB && weaponprob >= NWEAPONSPROB)
            return 2;
        else if(weaponprob >= 2*NWEAPONSPROB)
            return 3;
    }

    int initWithNShields(){
        if(generator.nextFloat() < NSHIELDSPROB)
            return 0;
        else
            return 1;
    }
    
    int whoStarts(int nPlayers){
        return generator.nextInt(nPlayers);
    }
    
    GameCharacter firstShot(){
        if(generator.nextFloat() < FIRSTSHOTPROB)
            return GameCharacter.SPACESTATION;
        else
            return GameCharacter.ENEMYSTARSHIP;
    }
    
    boolean spaceStationMoves(float speed){
        if(generator.nextFloat() < speed)
            return true;
        else
            return false;
    }
}
