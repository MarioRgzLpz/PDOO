/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tests;

import irrgarten.Game;
import irrgarten.UI.UI;
import irrgarten.UI.TextUI;
import irrgarten.controller.Controller;



/**
 *
 * @author mariorgzlpz
 */
public class TestP3 {
    public static void main(String[] args) {
        int nplayers = 3;
        Game game = new Game(nplayers);
        UI view = new TextUI();
        Controller controller = new Controller(game,view);
        controller.play();
    }
}
