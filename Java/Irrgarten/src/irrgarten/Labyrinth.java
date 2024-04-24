/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

import java.util.ArrayList;

/**
 * Represents the labyrinth in the game.
 * @author mariorgzlpz
 */
public class Labyrinth {
    private static char BLOCK_CHAR = 'X'; // Character representing a block in the labyrinth
    private static char EMPTY_CHAR = '-'; // Character representing an empty space in the labyrinth
    private static char MONSTER_CHAR = 'M'; // Character representing a monster in the labyrinth
    private static char COMBAT_CHAR = 'C'; // Character representing a combat space in the labyrinth
    private static char EXIT_CHAR = 'E'; // Character representing the exit in the labyrinth
    private static int ROW = 0; // Index of the row in an array
    private static int COL = 1; // Index of the column in an array
    private int nRows; // Number of rows in the labyrinth
    private int nCols; // Number of columns in the labyrinth
    private int exitRow; // Row index of the exit
    private int exitCol; // Column index of the exit
    private Monster[][] monsters; // 2D array to store monsters in the labyrinth
    private char[][] labyrinth; // 2D array to represent the labyrinth
    private Player[][] players; // 2D array to store players in the labyrinth

    /**
     * Constructor for the Labyrinth class.
     * @param nRows Number of rows in the labyrinth.
     * @param nCols Number of columns in the labyrinth.
     * @param exitRow Row index of the exit.
     * @param exitCol Column index of the exit.
     */
    public Labyrinth(int nRows, int nCols, int exitRow, int exitCol){
        this.nRows = nRows ;
        this.nCols = nCols;
        this.exitRow = exitRow;
        this.exitCol = exitCol;
        
        monsters = new Monster[nRows][nCols];
        players = new Player[nRows][nCols];
        labyrinth = new char[nRows][nCols];
        
        // Initialize the labyrinth with empty spaces
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCols; j++) {
                labyrinth[i][j] = EMPTY_CHAR;
            }
            labyrinth[exitRow][exitCol] = EXIT_CHAR;
        }
    }
    
    /**
     * Spread players randomly across the labyrinth.
     * @param players The list of players to spread.
     */
    public void spreadPlayers(ArrayList<Player> players){
        for (Player p : players) {
          int[] pos = randomEmptyPos();
          int oldRow = -1 ,oldCol = -1;
          Monster monster = putPlayer2D(oldRow,oldCol,pos[ROW],pos[COL],p);
        }
    }
    
    /**
     * Checks if there's a winner in the game.
     * @return True if there's a winner, false otherwise.
     */
    public boolean haveAWinner(){
        return players[exitRow][exitCol] != null;
    }
    
    /**
     * Gets the string representation of the labyrinth.
     * @return String representation of the labyrinth.
     */
    public String toString() {
        String result = "";

        // Iterate over each row of the labyrinth
        for (int i = 0; i < nRows; i++) {
            // Iterate over each column of the labyrinth in the current row
            for (int j = 0; j < nCols; j++) {
                // Add the character at the current position to the resulting string
                result += labyrinth[i][j] + "   ";
            }
            // Add a newline at the end of each row
            result += "\n";
        }

        // Return the resulting string
        return result;
    }
    
    /**
     * Adds a monster to the specified position in the labyrinth.
     * @param row The row index of the position.
     * @param col The column index of the position.
     * @param monster The monster to add.
     */
    public void addMonster(int row, int col, Monster monster){
        if(this.emptyPos(row, col) && this.posOK(row, col)){
            labyrinth[row][col] = 'M';
            monsters[row][col] = monster;
            monster.setPos(row, col);
        }
    }
    
    /**
     * Moves a player in the specified direction and handles combat if necessary.
     * @param direction The direction in which to move the player.
     * @param player The player to move.
     * @return The monster involved in the combat if any, null otherwise.
     */
    public Monster putPlayer(Directions direction, Player player){
        int oldRow = player.getRow();
        int oldCol = player.getCol();
        int[] newPos = dir2Pos(oldRow,oldCol,direction);
        Monster monster = putPlayer2D(oldRow,oldCol,newPos[0],newPos[1],player);
        return monster;
    }
    
    /**
     * Adds a block to the labyrinth.
     * @param orientation The orientation of the block (HORIZONTAL or VERTICAL).
     * @param startRow The starting row index of the block.
     * @param startCol The starting column index of the block.
     * @param length The length of the block.
     */
    public void addBlock(Orientation orientation, int startRow, int startCol, int length){
        int incRow, incCol;
        if(orientation == Orientation.VERTICAL){
            incRow = 1;
            incCol = 0;
        }
        else{
            incRow = 0;
            incCol = 1;
        }
        
        int row = startRow;
        int col = startCol;
        
        while((posOK(row,col) && emptyPos(row,col)) && length > 0){
            labyrinth[row][col] = BLOCK_CHAR;
            row += incRow;
            col += incCol;
            length -=1;
            
        }
    }
    
    /**
     * Gets the valid moves for a player at the specified position in the labyrinth.
     * @param row The row index of the player's position.
     * @param col The column index of the player's position.
     * @return The list of valid moves.
     */
    public ArrayList<Directions> validMoves(int row, int col){
        ArrayList<Directions> output = new ArrayList<>();

        if (canStepOn(row + 1, col)) {
            output.add(Directions.DOWN);
        }
        if (canStepOn(row - 1, col)) {
            output.add(Directions.UP);
        }
        if (canStepOn(row, col + 1)) {
            output.add(Directions.RIGHT);
        }
        if (canStepOn(row, col - 1)) {
            output.add(Directions.LEFT);
        }

        return output;
    }
    
    /**
     * Checks if the given position is within the bounds of the labyrinth.
     * @param row The row index.
     * @param col The column index.
     * @return True if the position is within bounds, false otherwise.
     */
    private boolean posOK(int row, int col){
        return (row >= 0 && row < nRows && col >= 0 && col < nCols);       
    }
    
    /**
     * Checks if the specified position in the labyrinth is empty.
     * @param row The row index.
     * @param col The column index.
     * @return True if the position is empty, false otherwise.
     */
    private boolean emptyPos(int row, int col){
        return labyrinth[row][col] == '-';
    }
    
    /**
     * Checks if the specified position in the labyrinth contains a monster.
     * @param row The row index.
     * @param col The column index.
     * @return True if the position contains a monster, false otherwise.
     */
    private boolean monsterPos(int row, int col){
        return labyrinth[row][col] == 'M';
    }
    
    /**
     * Checks if the specified position in the labyrinth is the exit.
     * @param row The row index.
     * @param col The column index.
     * @return True if the position is the exit, false otherwise.
     */
    private boolean exitPos(int row, int col){
        return labyrinth[row][col] == 'E';  
    }
    
    /**
     * Checks if the specified position in the labyrinth is a combat space.
     * @param row The row index.
     * @param col The column index.
     * @return True if the position is a combat space, false otherwise.
     */
    private boolean combatPos(int row, int col){
        return labyrinth[row][col] == 'C';
    }
    
    /**
     * Checks if a player can step on the specified position in the labyrinth.
     * @param row The row index.
     * @param col The column index.
     * @return True if the player can step on the position, false otherwise.
     */
    private boolean canStepOn(int row, int col){
        return this.posOK(row, col) && (this.emptyPos(row, col) || this.monsterPos(row, col) || this.exitPos(row, col));
    }
    
    /**
     * Updates the old position of a player or monster after moving.
     * @param row The row index of the old position.
     * @param col The column index of the old position.
     */
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
    
    /**
     * Converts a direction to a new position.
     * @param row The current row index.
     * @param col The current column index.
     * @param direction The direction to move.
     * @return An array containing the new row and column indices.
     */
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
    
    /**
     * Finds a random empty position in the labyrinth.
     * @return An array containing the row and column indices of the empty position.
     */
    private int[] randomEmptyPos(){
        int row = Dice.randomPos(nRows);
        int col = Dice.randomPos(nCols);
        while(!this.emptyPos(row, col)){
            row = Dice.randomPos(nRows);
            col = Dice.randomPos(nCols);
        }
        return new int[]{row, col};
    }
    
    /**
     * Places a player in the specified position in the labyrinth and handles combat.
     * @param oldRow The old row index of the player.
     * @param oldCol The old column index of the player.
     * @param row The new row index of the player.
     * @param col The new column index of the player.
     * @param player The player to place.
     * @return The monster involved in combat, if any.
     */
    private Monster putPlayer2D(int oldRow, int oldCol, int row, int col, Player player){
        Monster output = null;
        if(canStepOn(row,col)){
            if(posOK(oldRow,oldCol)){
                Player p = players[oldRow][oldCol];
                if(p == player){
                    updateOldPos(oldRow,oldCol);
                    players[oldRow][oldCol] = null;
                }
            }
            boolean monsterPos = monsterPos(row,col);
            if(monsterPos){
                labyrinth[row][col] = COMBAT_CHAR;
                output = monsters[row][col];
            }
            else {
                char number = player.getNumber();
                labyrinth[row][col] = number;
            }
            
            players[row][col] = player;
            player.setPos(row, col);
        }
        return output;
    } 
}
