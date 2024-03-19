/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tests;

import irrgarten.Dice;
import irrgarten.Directions;
import irrgarten.GameCharacter;
import irrgarten.GameState;
import irrgarten.Orientation;
import irrgarten.Shield;
import irrgarten.Weapon;
import irrgarten.Labyrinth;
import irrgarten.Monster;
import irrgarten.Player;
import irrgarten.Game;

/**
 *
 * @author mariorl
 */
public class TestP2 {
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
        
        System.out.println("Probando la practica 2:\n");
        System.out.println("--------------Creando instancias de clase-------------");
        Monster unicorn = new Monster("Unicorn",7.0f,2.0f);
        Monster minotaur = new Monster("Minotaur",4.0f,7.0f);
        Monster dragon = new Monster("Dragon",8.0f,9.0f);
        Player player0 = new Player('0',3.0f,7.0f);
        Player player1 = new Player('1',4.0f,6.0f);
        Player player2 = new Player('2',1.0f,9.0f);
        Labyrinth labyrinth = new Labyrinth(3,3,2,2);
        
        System.out.println("\n--------------Muestra de monstruos y jugadores----------------");
        System.out.println(unicorn.toString());
        System.out.println("------------------------------");
        System.out.println(minotaur.toString());
        System.out.println("------------------------------");
        System.out.println(dragon.toString());
        System.out.println("------------------------------");
        System.out.println(player0.toString());
        System.out.println("------------------------------");
        System.out.println(player1.toString());
        System.out.println("------------------------------");
        System.out.println(player2.toString());
        
        System.out.println("\n--------------Muestra de funciones----------------");
        player0.setPos(3, 4);
        System.out.println(player0.toString());
        System.out.println("------------------------------");
        System.out.println("getRow " + player0.getRow());
        System.out.println("getCol " + player0.getCol());
        System.out.println("Unicorn attack: " + unicorn.attack());
        System.out.println("Unicorn dead: " + unicorn.dead());
        System.out.println("Player0 attack: " + player0.attack());
        System.out.println("Player0 dead: " + player0.dead());
        
        System.out.println("\n--------------Muestra de laberinto----------------");
        System.out.println(labyrinth.toString());
        System.out.println("\n--------------Añadimos un monstruo----------------");
        labyrinth.addMonster(2, 2, minotaur);
        System.out.println(labyrinth.toString());
        labyrinth.addMonster(1, 1, unicorn);
        System.out.println(labyrinth.toString());
        Game game = new Game(3);
        System.out.println("\n--------------Muestra del juego----------------");
        GameState estado = game.getGameState();
        System.out.println(estado.getMonsters().toString());
        System.out.println(estado.getPlayers().toString());
        System.out.println(estado.getLabyrinth().toString());

        
    }
}
