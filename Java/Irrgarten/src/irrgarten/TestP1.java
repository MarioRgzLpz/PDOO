/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author mariorl
 */
public class TestP1 {
    public static void main(String[] args) {
        System.out.println("Probando la practica 1:\n");
        
        System.out.println("---------------Enum Directions-------------------");
        System.out.println(Directions.DOWN);
        System.out.println(Directions.UP);
        System.out.println(Directions.LEFT);
        System.out.println(Directions.RIGHT);
    
        System.out.println("\n--------------Enum Orientation------------------");
        System.out.println(Orientation.HORIZONTAL);
        System.out.println(Orientation.VERTICAL);
        
        System.out.println("\n--------------Enum GameCharacter-----------------");
        System.out.println(GameCharacter.PLAYER);
        System.out.println(GameCharacter.MONSTER);
        
        System.out.println("\n--------------Creando instancias de clase-------------");
        Weapon arma1 = new Weapon(2.3f, 4);
        Weapon arma2 = new Weapon(1.7f, 1);
        Shield shield1 = new Shield(2.9f, 5);
        Shield shield2 = new Shield(4.1f, 2);
        GameState juego = new GameState("Garden of Bambam", "Mario:vivo, Miguel:vivo, Andrea:muerto", "Minotauro, Manticora",0, false,"Se ha creado el tablero de juego");
        
        System.out.println("\n--------------Muestra de armas y escudos----------------");
        System.out.println("arma1: " + arma1.toString());
        System.out.println("arma2: " + arma2.toString());
        System.out.println("shield1: " + shield1.toString());
        System.out.println("shield2: " + shield2.toString());
        
        System.out.println("\nAtaco con arma1: " + arma1.attack());
        System.out.println("arma1: " + arma1.toString());
        System.out.println("¿Tras el ataque se descarta?: " + arma1.discard());
        System.out.println("\nProtejo con shield1: " + shield1.protect());
        System.out.println("shield1: " + shield1.toString());
        System.out.println("¿Tras proteger se descarta?: " + shield1.discard());
        System.out.println("\nIntento de descartar arma2: " + arma2.discard());
        System.out.println("Intento de descartar shield2: " + arma2.discard());
        
        
        System.out.println("\n---------------Estado actual del juego------------------");
        System.out.println("labyrinth: " + juego.getLabyrinth());
        System.out.println("players: " + juego.getPlayers());
        System.out.println("monsters: " + juego.getMonsters());
        System.out.println("currentPlayer: " + juego.getCurrentPlayer());
        System.out.println("ganador: " + juego.getWinner());
        System.out.println("log: " + juego.getLog());
        
        System.out.println("\n---------------Prueba del dado---------------");
        for(int i = 0 ; i < 3 ; i++) {
            System.out.println("Posicion: " + Dice.randomPos(100));
            System.out.println("Inicia jugador: " + Dice.whoStarts(10));
            System.out.println("Con nivel de inteligencia: " + Dice.randomIntelligence());
            System.out.println("Con nivel de fuerza: " + Dice.randomStrenght());
            System.out.println("¿Resucita?: " + Dice.resurrectPlayer());
            System.out.println("Recompensa de armas: " + Dice.weaponsReward());
            System.out.println("Recompensa de escudos: " + Dice.shieldsReward());
            System.out.println("Recompensa de vida: " + Dice.healthReward());
            System.out.println("Poder del arma: " + Dice.weaponPower());
            System.out.println("Poder del escudo: " + Dice.shieldPower());
            System.out.println("Numero de usos restante: " + Dice.usesLeft());
            System.out.println("Intensidad: " + Dice.intensity(5.0f));
            System.out.println("\n");
        }
        
    }
}
