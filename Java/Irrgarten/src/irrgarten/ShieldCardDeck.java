/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author mariorl
 */
public class ShieldCardDeck extends CardDeck<Shield>{
    @Override
    protected void addCards(){
        int nCards = 4;
        Shield elEscudo = new Shield(5,5);
        addCard(elEscudo);
        for (int i = 0; i < nCards; i++){
            float protection = Dice.shieldPower();
            int uses = Dice.usesLeft();
            addCard(new Shield(protection,uses));     
        }
    }
}
