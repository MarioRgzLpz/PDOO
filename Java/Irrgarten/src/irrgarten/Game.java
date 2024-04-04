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
    
    public Game(int nPlayers){
        
        int nRows = 9;
        int nCols = 9;
        int exitRow = 7;
        int exitCol = 8;

        labyrinth = new Labyrinth(nRows,nCols,exitRow,exitCol);
        
        // Crear jugadores 
        
        for (int i = 1; i <= nPlayers; i++) {
            Player player = new Player((char)('1' + i - 1), Dice.randomIntelligence(), Dice.randomStrenght());
            player.receiveReward();
            players.add(player);
        }

        // Crear varios monstruos
        
        for (int i = 0; i < nPlayers; i++) {
            Monster monster = new Monster("Monster " + (i + 1), Dice.randomIntelligence(), Dice.randomStrenght());
            monsters.add(monster);
        }

        // Determinar quién va a empezar
        currentPlayerIndex = Dice.whoStarts(nPlayers);
        currentPlayer = players.get(currentPlayerIndex);

        // Inicializar el resto de atributos con valores iniciales apropiados
        log = "";

        // Llamar al método que configura el laberinto
        
        configureLabyrinth();

        // Llamar al método que reparte los jugadores por el laberinto
    }
    
    public boolean finished(){
        return labyrinth.haveAWinner();
    }
    
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
    
    private void configureLabyrinth() {
        
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
        
        labyrinth.addMonster(4, 1, monsters.get(0));
        labyrinth.addMonster(7, 7, monsters.get(1));
        labyrinth.addMonster(3, 4, monsters.get(2));
        
        labyrinth.spreadPlayers(players);

    }
    
    public void nextPlayer(){
        int index = (currentPlayerIndex + 1) % players.size();
        currentPlayerIndex = index;
        currentPlayer = players.get(currentPlayerIndex);
    }
    
    private Directions actualDirection(Directions preferredDirection){
        int currentRow = currentPlayer.getRow();
        int currentCol = currentPlayer.getCol();
        ArrayList<Directions> validMoves = labyrinth.validMoves(currentRow, currentCol);
        return currentPlayer.move(preferredDirection, validMoves);
    }
    
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
    
    private void manageReward(GameCharacter winner){
        if(winner == GameCharacter.PLAYER){
            currentPlayer.receiveReward();
            logPlayerWon();
        }
        else{
            logMonsterWon();
        }
    }
    
    private void manageResurrection(){
        boolean resurrect = Dice.resurrectPlayer();
        if(resurrect){
            currentPlayer.resurrect();
            logResurrected();
        }
        else{
            logPlayerSkipTurn();
        }
            
    }
    
    private void logPlayerWon(){
        log += "Player " + (currentPlayerIndex+1) + " wins the combat" + "\n" + currentPlayer;
    }
    
    private void logMonsterWon(){
        log += "Monster wins the combat" + "\n";
    }
    
    private void logResurrected(){
        log +=  "Player " + (currentPlayerIndex+1) +  " resurrects" + "\n";
    }
    
    private void logPlayerSkipTurn(){
        log += "Player " + (currentPlayerIndex+1) + " lost the turn" + "\n";
    }
    
    private void logPlayerNoOrders(){
        log += "Player " + (currentPlayerIndex+1) + " couldnt follow the orders" + "\n";
    }
    
    private void logNoMonster(){
        log += "Player " + (currentPlayerIndex+1) + " get into an empty square" + "\n";
    }
    
    private void logRounds(int rounds, int max){
        log += "Reached round " + rounds + ", Max rounds  " + max + "\n";
    }
    
}
