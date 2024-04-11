/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 * Represents the state of the game at a particular moment.
 * @author mariorgzlpz
 */
public class GameState {
    private String labyrinth; // String representation of the labyrinth.
    private String players; // String representation of the players.
    private String monsters; // String representation of the monsters.
    private int currentPlayer; // Index of the current player.
    private boolean winner; // Indicates if there's a winner.
    private String log; // Log of events in the game.

    /**
     * Constructor for the GameState class.
     * @param labyrinth String representation of the labyrinth.
     * @param players String representation of the players.
     * @param monsters String representation of the monsters.
     * @param currentPlayer Index of the current player.
     * @param winner Indicates if there's a winner.
     * @param log Log of events in the game.
     */
    public GameState(String labyrinth, String players, String monsters, int currentPlayer, boolean winner, String log) {
        this.labyrinth = labyrinth;
        this.players = players;
        this.monsters = monsters;
        this.currentPlayer = currentPlayer;
        this.winner = winner;
        this.log = log;
    }
    
    /**
     * Gets the string representation of the labyrinth.
     * @return String representation of the labyrinth.
     */
    public String getLabyrinth() {
        return labyrinth;
    }
    
    /**
     * Gets the string representation of the players.
     * @return String representation of the players.
     */
    public String getPlayers() {
        return players;
    }
    
    /**
     * Gets the string representation of the monsters.
     * @return String representation of the monsters.
     */
    public String getMonsters() {
        return monsters;
    }
    
    /**
     * Gets the index of the current player.
     * @return Index of the current player.
     */
    public int getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Checks if there's a winner.
     * @return True if there's a winner, false otherwise.
     */
    public boolean getWinner() {
        return winner;
    }
    
    /**
     * Gets the log of events in the game.
     * @return Log of events in the game.
     */
    public String getLog() {
        return log;
    }
}
