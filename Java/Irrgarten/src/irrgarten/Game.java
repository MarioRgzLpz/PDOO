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
public class Game {
    private static int MAX_ROUNDS = 10;
    private int currentPlayerIndex;
    private String log;
    private Player currentPlayer;
    private Labyrinth labyrinth;
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Monster> monsters = new ArrayList<>();
    
    public Game(int nplayers){
        labyrinth = new Labyrinth(10,10,8,9);
    }
    
    public boolean finished(){
        return labyrinth.haveAWinner();
    }
    
    public boolean nextStep(Directions preferredDirections){
        throw new UnsupportedOperationException();
    }
    
    public GameState getGameState(){
        String namesp = "";
        String namesm = "";
        for(Player player : players){
            namesp += player.toString();
        }
        for(Monster monster : monsters){
            namesm += monster.toString();
        }
        return new GameState(labyrinth.toString(), namesp, namesm, currentPlayerIndex, this.finished(),log);
    }
    
   
}
