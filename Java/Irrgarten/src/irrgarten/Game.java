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
        
        labyrinth = new Labyrinth(5,5,4,4);
        
        // Crear jugadores 
        
        for (int i = 1; i <= nPlayers; i++) {
            Player player = new Player((char)('1' + i - 1), Dice.randomIntelligence(), Dice.randomStrenght());
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

        // Instanciar un laberinto para inicializar el atributo labyrinth

        // Inicializar el resto de atributos con valores iniciales apropiados
        log = "";

        // Llamar al método que configura el laberinto
        
        configureLabyrinth();

        // Llamar al método que reparte los jugadores por el laberinto
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
    
    private void configureLabyrinth() {
        // Leer las dimensiones del laberinto
        int nRows = 5;
        int nCols = 5;

        char[][] manualLabyrinth = {
            {'X', 'X', 'X', 'X', 'X'},
            {'X', '-', '-', '-', 'X'},
            {'X', '-', 'X', '-', 'X'},
            {'X', '-', '-', 'E', 'X'},
            {'X', 'X', 'X', 'X', 'X'}
        };

        // Leer el laberinto desde el archivo
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCols; j++) {
                labyrinth.setLabyrinth(i, j, manualLabyrinth[i][j]);
            }
        }
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
