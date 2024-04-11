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
        
        monsters = new Monster[nRows][nCols];
        players = new Player[nRows][nCols];
        labyrinth = new char[nRows][nCols];
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCols; j++) {
                labyrinth[i][j] = EMPTY_CHAR;
            }
            labyrinth[exitRow][exitCol] = EXIT_CHAR;
        }
    }
    
    public void spreadPlayers(ArrayList<Player> players){
        for (Player p : players) {
          int[] pos = randomEmptyPos();
          int oldRow = -1 ,oldCol = -1;
          Monster monster = putPlayer2D(oldRow,oldCol,pos[0],pos[1],p);
        }
    }
    
    public boolean haveAWinner(){
        if (players[exitRow][exitCol] != null) {
            return true; // There is a winner
        } else {
            return false; // No player on the exit cell
        }
    }
    
    public String toString() {
        String result = "";

        // Recorre cada fila del laberinto
        for (int i = 0; i < nRows; i++) {
            // Recorre cada columna del laberinto en la fila actual
            for (int j = 0; j < nCols; j++) {
                // Agrega el carácter en la posición actual a la cadena de texto resultante
                result += labyrinth[i][j] + "   ";
            }
            // Agrega un salto de línea al final de cada fila
            result += "\n";
        }

        // Devuelve la cadena de texto resultante
        return result;
    }
    
    public void addMonster(int row, int col, Monster monster){
        if(this.emptyPos(row, col) && this.posOK(row, col)){
            labyrinth[row][col] = 'M';
            monsters[row][col] = monster;
            monster.setPos(row, col);
        }
    }
    
    public Monster putPlayer(Directions direction, Player player){
        int oldRow = player.getRow();
        int oldCol = player.getCol();
        int[] newPos = dir2Pos(oldRow,oldCol,direction);
        Monster monster = putPlayer2D(oldRow,oldCol,newPos[0],newPos[1],player);
        return monster;
    }
    
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
    
    private boolean posOK(int row, int col){
        return (row >= 0 && row < nRows && col >= 0 && col < nCols){       
    }
    
    private boolean emptyPos(int row, int col){
        return labyrinth[row][col] == '-';
    }
    
    private boolean monsterPos(int row, int col){
        return labyrinth[row][col] == 'M';
    }
    
    private boolean exitPos(int row, int col){
        return labyrinth[row][col] == 'E';  
    }
    
    private boolean combatPos(int row, int col){
        return labyrinth[row][col] == 'C';
    }
    
    private boolean canStepOn(int row, int col){
        return this.posOK(row, col) && (this.emptyPos(row, col) || this.monsterPos(row, col) || this.exitPos(row, col));
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
        return new int[]{row, col};
    }
    
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
    
    // main solo para probar la clase labyrinth
    public static void main(String[] args) {
        Labyrinth labyrinth = new Labyrinth(5, 5, 2, 2);
        Monster monstruo = new Monster("unicorn", 9.0f,3.0f);
        System.out.println(labyrinth.toString());
        labyrinth.addMonster(1, 1, monstruo);
        System.out.println(labyrinth.toString());
        int posicion[] = labyrinth.randomEmptyPos();
        System.out.println("Random empty position: (" + posicion[0] + ", " + posicion[1] + ")");
        System.out.println(labyrinth.canStepOn(posicion[0], posicion[1]));
        int newposicion[] = labyrinth.dir2Pos(4, 3, Directions.DOWN);
        System.out.println("New position: (" + newposicion[0] + ", " + newposicion[1] + ")");
        System.out.println(labyrinth.canStepOn(newposicion[0], newposicion[1]));
        
    }
    
}
