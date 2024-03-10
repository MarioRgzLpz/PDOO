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
public class Labyrinth {
    private static char BLOCK_CHAR = 'X';
    private static char EMPTY_CHAR = '-';
    private static char MONSTER_CHAR = 'M';
    private static char COMBAT_CHAR = 'C';
    private static char EXIT_CHAR = 'E';
    private static int ROW = 0;
    private static int COL = 1;
    private int nRows;
    private int nCols;
    private int exitRow;
    private int exitCol;
    private Monster[][] monsters;
    private char[][] labyrinth;
    private Player[][] players;
    
    
    public Labyrinth(int nRows, int nCols, int exitRow, int exitCol){
        this.nRows = nRows ;
        this.nCols = nCols;
        this.exitRow = exitRow;
        this.exitCol = exitCol;
    }
    
    public void spreadPlayers(Player[] players){
        throw new UnsupportedOperationException();
    }
    
    public boolean haveAWinner(){
        if (players[exitRow][exitCol] != null) {
            return true; // There is a winner
        } else {
            return false; // No player on the exit cell
        }
    }
    
    public String toString(){
        StringBuilder result = new StringBuilder();

        // Loop through each row and column in the labyrinth
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCols; j++) {
                // Append the character representing the cell in the labyrinth
                result.append(labyrinth[i][j]);

                // Check if there is a player in the current cell
                if (players[i][j] != null) {
                    result.append("P"); // Append 'P' to represent a player
                } else {
                    result.append(" "); // Append a space if no player is in the cell
                }

                // Check if there is a monster in the current cell
                if (monsters[i][j] != null) {
                    result.append("M"); // Append 'M' to represent a monster
                } else {
                    result.append(" "); // Append a space if no monster is in the cell
                }

                // Append a newline character to move to the next row
                result.append("\n");
            }
        }

        // Return the generated string
        return result.toString();
    }
    
    public void addMonster(int row, int col, Monster monster){
        if(this.emptyPos(row, col) && this.posOK(row, col)){
            labyrinth[row][col] = 'M';
            monsters[row][col] = monster;
            monster.setPos(row, col);
        }
    }
    
    public Monster putPlayer(Directions direction, Player player){
        throw new UnsupportedOperationException();
    }
    
    public void addBlock(Orientation orientation, int startRow, int startCol, int lenght){
        throw new UnsupportedOperationException();
    }
    
    public Directions[] validMoves(int row, int col){
        throw new UnsupportedOperationException();
    }
    
    private boolean posOK(int row, int col){
        if(row >= 0 && row < nRows && col >= 0 && col < nCols){
            return true;
        }
        else{
            return false;
        }
                   
    }
    
    private boolean emptyPos(int row, int col){
        if(labyrinth[row][col] == '-'){
            return true;
        }
        else {
            return false;
        }
    }
    
    private boolean monsterPos(int row, int col){
        if(labyrinth[row][col] == 'M'){
            return true;
        }
        else {
            return false;
        } 
    }
    
    private boolean exitPos(int row, int col){
        if(labyrinth[row][col] == 'E'){
            return true;
        }
        else {
            return false;
        }    
    }
    
    private boolean combatPos(int row, int col){
        if(labyrinth[row][col] == 'C'){
            return true;
        }
        else {
            return false;
        }    
    }
    
    private boolean canStepOn(int row, int col){
        if(this.emptyPos(row, col) && this.posOK(row, col) && this.monsterPos(row, col) && this.exitPos(row, col)){
            return true;
        }
        else{
            return false;
        }
    }
    
    private void updateOldPos(int row, int col){
        if(this.posOK(row, col)){
            if(this.combatPos(row, col)){
                labyrinth[row][col] = 'M';
            }
            else{
                labyrinth[row][col] = '-';
            }    
        }
    }
    
    private int[] dir2Pos(int row, int col, Directions direction){
        switch(direction){
            case LEFT:
                col -= 1;
                break;
            case RIGHT:
                col += 1;
                break;
            case UP:
                row -= 1;
                break;
            case DOWN:
                row += 1; 
                break;
        }
        int[] posicion = new int[]{row, col};
        return posicion;
    }
    
    private int[] randomEmptyPos(){
        int row = Dice.randomPos(nRows);
        int col = Dice.randomPos(nCols);
        while(!this.emptyPos(row, col)){
            row = Dice.randomPos(nRows);
            col = Dice.randomPos(nCols);
        }
        int[] posicion = new int[]{row, col};
        return posicion;
    }
    
    private Monster putPlayer2D(int oldRow, int oldCol, int row, int col, Player player){
        throw new UnsupportedOperationException();
    }
    
}
