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
        String namesplayers = "";
        String namesmonsters = "";
        for(Player player : players){
            namesplayers += player.toString();
        }
        for(Monster monster : monsters){
            namesmonsters += monster.toString();
        }
        return new GameState(labyrinth.toString(), namesplayers, namesmonsters, currentPlayerIndex, this.finished(),log);
    }
    
    private void configureLabyrinth(){
        throw new UnsupportedOperationException();    
    }
    
    public void nextPlayer(){
        currentPlayerIndex += 1;
        currentPlayer = players.get(currentPlayerIndex);
    }
    
    private Directions actualDirection(Directions preferredDirection){
        throw new UnsupportedOperationException();
    }
    
    private GameCharacter combat(Monster monster){
        throw new UnsupportedOperationException();
    }
    
    private void manageReward(GameCharacter winner){
        throw new UnsupportedOperationException();
    }
    
    private void manageResurrection(){
        throw new UnsupportedOperationException();
    }
    
    private void logPlayerWon(){
        log += "Player wins the combat" + "\n";
    }
    
    private void logMonsterWon(){
        log += "Monster wins the combat" + "\n";
    }
    
    private void logResurrected(){
        log += "Player resurrects" + "\n";
    }
    
    private void logPlayerSkipTurn(){
        log += "Player lost the turn" + "\n";
    }
    
    private void logPlayerNoOrders(){
        log += "Player couldnt follow the orders" + "\n";
    }
    
    private void logNoMonster(){
        log += "Player get into an empty square" + "\n";
    }
    
    private void logRounds(int rounds, int max){
        log += "Max rounds achieved" + "\n";
    }
    
}
