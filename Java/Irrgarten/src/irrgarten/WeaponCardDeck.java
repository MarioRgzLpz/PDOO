/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author mariorl
 */
public class WeaponCardDeck extends CardDeck<Weapon>{
    @Override
    protected void addCards(){
        int nCards = 4;
        Weapon elArma = new Weapon(5,5);
        addCard(elArma);
        for (int i = 0; i < nCards; i++){
            float power = Dice.weaponPower();
            int uses = Dice.usesLeft();
            addCard(new Weapon(power,uses));     
        }
    }    
}
