/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author mariorl
 */
public class GameState {
    private String labyrinthv;
    private String players;
    private String monsters;
    private int currentPlayer;
    private boolean winner;
    private String log;
    
    public GameState( String l, String p, String m, int cp, boolean w, String lg) {
        labyrinthv = l;
        players = p;
        monsters = m;
        currentPlayer = cp;
        winner = w;
        log = lg;
    }
    
    public String getLabyrinthv() {
        return labyrinthv;
    }
    
    public String getPlayers() {
        return players;
    }
    
    public String getMonsters() {
        return monsters;
    }
    
    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean getWinner() {
        return winner;
    }
    
    public String getLog() {
        return log;
    }
}
