/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package irrgarten.UI;
import irrgarten.Directions;
import irrgarten.GameState;
/**
 *
 * @author mariorl
 */
public interface UI {
    public Directions nextMove();
    public void showGame(GameState gameState);
}
