
package irrgarten.UI;

import irrgarten.Directions;
import irrgarten.GameState;
import java.util.Scanner;


public class TextUI {
    
    private static Scanner in = new Scanner(System.in);
    
    private char readChar() {
        String s = in.nextLine();     
        return s.charAt(0);
    }
    

    public Directions nextMove() {
        System.out.print("Where do you want to move (w:UP,s:DOWN,d:RIGHT,a:LEFT)? ");
        
        Directions direction = Directions.DOWN;
        boolean gotInput = false;
        
        while (!gotInput) {
            char c = readChar();
            switch(c) {
                case 'w':
                    System.out.print(" UP\n");
                    direction = Directions.UP;
                    gotInput = true;
                    break;
                case 's':
                    System.out.print(" DOWN\n");
                    direction = Directions.DOWN;
                    gotInput = true;
                    break;
                case 'd':
                    System.out.print("RIGHT\n");
                    direction = Directions.RIGHT;
                    gotInput = true;
                    break;
                case 'a':
                    System.out.print(" LEFT\n");
                    direction = Directions.LEFT;
                    gotInput = true;    
                    break;
            }
        }    
        return direction;
    }
    
    public void showGame(GameState gameState) {
        if(!gameState.getWinner()){
            System.out.println(gameState.getLog().toString());
            System.out.println("Stats: ");
            System.out.println(gameState.getPlayers());
            System.out.println(gameState.getLabyrinth().toString());
            System.out.println("Player " + gameState.getCurrentPlayer() + " moves");
        }
        else
            System.out.println("Player " + gameState.getCurrentPlayer() + " wins");
    }
    
}
