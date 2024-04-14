/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class representing the labyrinth game.
 */
public class Game {
    private static int MAX_ROUNDS = 10;
    private int currentPlayerIndex;
    private String log;
    private Player currentPlayer;
    private Labyrinth labyrinth;
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Monster> monsters = new ArrayList<>();
    
    /**
     * Constructor for the Game class.
     * @param nPlayers Number of players in the game.
     */
    public Game(int nPlayers){
        // Labyrinth setup
        int nRows = 9;
        int nCols = 9;
        int exitRow = 7;
        int exitCol = 8;
        labyrinth = new Labyrinth(nRows,nCols,exitRow,exitCol);
        
        // Creating players
        for (int i = 0; i < nPlayers; i++) {
            Player player = new Player((char)('0' + i ), Dice.randomIntelligence(), Dice.randomStrenght());
            players.add(player);
        }
        Player player = new Player((char)('0' + 1 ), 0, 0);
        players.add(player);
        // Creating monsters
        for (int i = 0; i < nPlayers; i++) {
            Monster monster = new Monster("Monster " + i , Dice.randomIntelligence(), Dice.randomStrenght());
            monsters.add(monster);
        }
        Monster monster = new Monster("Boss " , 10, 10);
        monsters.add(monster);
        // Determine who starts
        currentPlayerIndex = Dice.whoStarts(nPlayers);
        currentPlayer = players.get(currentPlayerIndex);
        
        // Initialize other attributes
        log = "";

        // Configure the labyrinth
        configureLabyrinth();
    }
    
    /**
     * Checks if the game has finished.
     * @return true if the game has finished, false otherwise.
     */
    public boolean finished(){
        return labyrinth.haveAWinner();
    }
    
    /**
     * Performs the next step in the game.
     * @param preferredDirection Preferred direction to move.
     * @return true if the game has finished after the step, false otherwise.
     */
    public boolean nextStep(Directions preferredDirection){
        log = "";
        boolean dead = currentPlayer.dead();
        if(!dead){
            Directions direction = actualDirection(preferredDirection);
            if(direction != preferredDirection){
                logPlayerNoOrders();
            }
            Monster monster = labyrinth.putPlayer(direction,currentPlayer);
            
            if(monster == null){
                logNoMonster();
            }
            else{
                GameCharacter winner = combat(monster);
                manageReward(winner);
            }
        }
        else{
            manageResurrection();
        }
        
        boolean endGame = finished();
        
        if(!endGame){
            nextPlayer();
        }
        
        return endGame;
    }
    
    /**
     * Gets the current state of the game.
     * @return Current state of the game.
     */
    public GameState getGameState(){
        String namesplayers = "";
        String namesmonsters = "";
        for(Player player : players){
            namesplayers += player.toString() + "\n";
        }
        for(Monster monster : monsters){
            namesmonsters += monster.toString() + "\n";
        }
        return new GameState(labyrinth.toString(), namesplayers, namesmonsters, currentPlayerIndex, this.finished(),log);
    }
    
    /**
     * Configures the labyrinth with blocks and monsters.
     */
    private void configureLabyrinth() {
        // Add blocks
        labyrinth.addBlock(Orientation.HORIZONTAL, 0, 0, 9);
        labyrinth.addBlock(Orientation.HORIZONTAL, 8, 0, 9);
        labyrinth.addBlock(Orientation.HORIZONTAL, 5, 1, 2);
        labyrinth.addBlock(Orientation.HORIZONTAL, 6, 2, 3);
        labyrinth.addBlock(Orientation.HORIZONTAL, 2, 4, 2);
        labyrinth.addBlock(Orientation.HORIZONTAL, 4, 4, 2);
        labyrinth.addBlock(Orientation.VERTICAL, 1, 0, 5);
        labyrinth.addBlock(Orientation.VERTICAL, 2, 2, 3);
        labyrinth.addBlock(Orientation.VERTICAL, 1, 8, 6);
        labyrinth.addBlock(Orientation.VERTICAL, 2, 6, 7);
        
        // Add monsters
        labyrinth.addMonster(4, 1, monsters.get(0));
        labyrinth.addMonster(7, 7, monsters.get(1));
        //labyrinth.addMonster(3, 4, monsters.get(2));
        //labyrinth.addMonster(5, 4, monsters.get(3));
         
        // Spread players in the labyrinth
        labyrinth.spreadPlayers(players);
    }
    
    /**
     * Moves to the next player in turn.
     */
    public void nextPlayer(){
        int index = (currentPlayerIndex + 1) % players.size();
        currentPlayerIndex = index;
        currentPlayer = players.get(currentPlayerIndex);
    }
    
    /**
     * Gets the current direction for the player.
     * @param preferredDirection Preferred direction to move.
     * @return Current direction for the player.
     */
    private Directions actualDirection(Directions preferredDirection){
        int currentRow = currentPlayer.getRow();
        int currentCol = currentPlayer.getCol();
        ArrayList<Directions> validMoves = labyrinth.validMoves(currentRow, currentCol);
        return currentPlayer.move(preferredDirection, validMoves);
    }
    
    /**
     * Simulates a combat between the player and a monster.
     * @param monster Monster to combat against.
     * @return The winner of the combat.
     */
    private GameCharacter combat(Monster monster){
        int rounds = 0;
        GameCharacter winner = GameCharacter.PLAYER;
        float playerAttack = currentPlayer.attack();
        boolean lose = monster.defend(playerAttack);
        while(!lose && (rounds < MAX_ROUNDS)){
            winner = GameCharacter.MONSTER;
            rounds++;
            float monsterAttack = monster.attack();
            lose = currentPlayer.defend(monsterAttack);
            if(!lose){
                playerAttack = currentPlayer.attack();
                winner = GameCharacter.PLAYER;
                lose = monster.defend(playerAttack);
            }
        }
        logRounds(rounds,MAX_ROUNDS);
        return winner;
    }
    
    /**
     * Manages the reward after a combat.
     * @param winner The winner of the combat.
     */
    private void manageReward(GameCharacter winner){
        if(winner == GameCharacter.PLAYER){
            currentPlayer.receiveReward();
            logPlayerWon();
        }
        else{
            logMonsterWon();
        }
    }
    
    /**
     * Manages player resurrection.
     */
    private void manageResurrection(){
        boolean resurrect = Dice.resurrectPlayer();
        if(resurrect){
            currentPlayer.resurrect();
            FuzzyPlayer fuzzyPlayer = new FuzzyPlayer(currentPlayer);
            players.set(currentPlayerIndex, fuzzyPlayer);
            labyrinth.setFuzzyPlayer(currentPlayer.getRow(), currentPlayer.getCol(), fuzzyPlayer);
            currentPlayer = fuzzyPlayer;
            logResurrected();
        }
        else{
            logPlayerSkipTurn();
        }   
    }
    
    // Methods to log events in the game log
    
    private void logPlayerWon(){
        log += "Player " + currentPlayerIndex + " wins the combat" + "\n" + currentPlayer;
    }
    
    private void logMonsterWon(){
        log += "Monster wins the combat" + "\n";
    }
    
    private void logResurrected(){
        log +=  "Player " + currentPlayerIndex +  " resurrects" + "\n";
    }
    
    private void logPlayerSkipTurn(){
        log += "Player " + currentPlayerIndex + " lost the turn" + "\n";
    }
    
    private void logPlayerNoOrders(){
        log += "Player " + currentPlayerIndex + " couldnt follow the orders" + "\n";
    }
    
    private void logNoMonster(){
        log += "Player " + currentPlayerIndex + " get into an empty square" + "\n";
    }
    
    private void logRounds(int rounds, int max){
        log += "Reached round " + rounds + ", Max rounds  " + max + "\n";
    }
    
}