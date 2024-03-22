#encoding utf-8

require_relative 'Directions'
require_relative 'Dice'
require_relative 'GameCharacter'
require_relative 'Orientation'
require_relative 'Shield'
require_relative 'Weapon'
require_relative 'GameState'
require_relative 'Labyrinth'
require_relative 'Game'



class TestP2
    def main
        puts "Probando la practica 1:\n"

        puts "---------------Enum Directions-------------------"
        puts Irrgarten::Directions::LEFT
        puts Irrgarten::Directions::RIGHT
        puts Irrgarten::Directions::UP
        puts Irrgarten::Directions::DOWN

        puts "\n--------------Enum GameCharacter------------------"
        puts Irrgarten::GameCharacter::PLAYER
        puts Irrgarten::GameCharacter::MONSTER

        puts "\n--------------Enum Orientation-----------------"
        puts Irrgarten::Orientation::VERTICAL
        puts Irrgarten::Orientation::HORIZONTAL

        puts "\n--------------Creando instancias de clase-----------------"
        arma1 = Irrgarten::Weapon.new(3.2, 4)
        arma2 = Irrgarten::Weapon.new(1.1, 2)
        shield1 = Irrgarten::Shield.new(2.3, 3)
        shield2 = Irrgarten::Shield.new(1.2, 1)
        juego = Irrgarten::GameState.new("Garden of Bambam", "Mario:vivo, Miguel:vivo, Andrea:muerto", "Minotauro, Manticora",0, false,"Se ha creado el tablero de juego")

        puts "\n--------------Muestra de armas y escudos-----------------"
        puts "Arma 1: " + arma1.to_s
        puts "Arma 2: " + arma2.to_s
        puts "Escudo 1: " + shield1.to_s
        puts "Escudo 2: " + shield2.to_s
        
        puts "\nAtacando con arma 1, poder: " + arma1.attack.to_s
        puts "Compruebo usos: " + arma1.to_s
        puts "¿Tras el ataque se descarta? " + arma1.discard.to_s
        puts "\nProtegiendo con shield1, proteccion: " + shield1.protect.to_s
        puts "Compruebo usos: " + shield1.to_s
        puts "¿Tras proteger se descarta? " + shield1.discard.to_s

        puts "\n--------------Estado actual del juego-----------------"
        puts "labyrinth: " + juego.get_labyrinth
        puts "players: " + juego.get_players
        puts "monsters: " + juego.get_monsters
        puts "currentplayer: " + juego.get_current_player.to_s
        puts "winner: " + juego.get_winner.to_s
        puts "log: " + juego.get_log

        puts "\n----------------Prueba del dado-------------------"
        2.times do
            puts "\nRandom Position: #{Irrgarten::Dice.random_pos(100)}"
            puts "Who Starts: #{Irrgarten::Dice.who_starts(10)}"
            puts "Random Intelligence: #{Irrgarten::Dice.random_intelligence()}"
            puts "Random Strength: #{Irrgarten::Dice.random_strenght()}"
            puts "Resurrect Player: #{Irrgarten::Dice.resurrect_player()}"
            puts "Weapons Reward: #{Irrgarten::Dice.weapons_reward()}"
            puts "Shields Reward: #{Irrgarten::Dice.shields_reward()}"
            puts "Health Reward: #{Irrgarten::Dice.health_reward()}"
            puts "Weapon Power: #{Irrgarten::Dice.weapon_power()}"
            puts "Shield Power: #{Irrgarten::Dice.shield_power()}"
            puts "Numero de usos restante: #{Irrgarten::Dice.uses_left()}"
            puts "Intensidad: #{Irrgarten::Dice.intensity(5.0)}"
        end

        puts "\nProbando la practica 2:\n"
        puts "---------------Creando instancias de clase-------------------"
        unicorn = Irrgarten::Monster.new("Unicornio", Irrgarten::Dice.random_intelligence, Irrgarten::Dice.random_strenght)
        dragon = Irrgarten::Monster.new("Dragon", Irrgarten::Dice.random_intelligence, Irrgarten::Dice.random_strenght)
        minotaur = Irrgarten::Monster.new("Minotauro", Irrgarten::Dice.random_intelligence, Irrgarten::Dice.random_strenght)
        player1 = Irrgarten::Player.new('1', Irrgarten::Dice.random_intelligence, Irrgarten::Dice.random_strenght)
        player2 = Irrgarten::Player.new('2', Irrgarten::Dice.random_intelligence, Irrgarten::Dice.random_strenght)
        player3 = Irrgarten::Player.new('3', Irrgarten::Dice.random_intelligence, Irrgarten::Dice.random_strenght)
        labyrinth = Irrgarten::Labyrinth.new(3, 3, 2, 2)

        puts "\n---------------Muestra de instancias de clase-------------------"
        puts "Unicornio: " + unicorn.to_s
        puts "---------------------------------"
        puts "Dragon: " + dragon.to_s
        puts "---------------------------------"
        puts "Minotauro: " + minotaur.to_s
        puts "---------------------------------"
        puts "Player 1: " + player1.to_s
        puts "---------------------------------"
        puts "Player 2: " + player2.to_s
        puts "---------------------------------"
        puts "Player 3: " + player3.to_s
        puts "---------------Muestra de funciones------------------"
        player1.set_pos(1, 1)
        puts "Change position: " + player1.to_s
        puts "---------------------------------"
        puts "Player 1 dead: " + player1.dead.to_s
        puts "Player 1 resurrect: " + player1.resurrect.to_s
        puts "Player 1 attack: " + player1.attack.to_s
        puts "Unicorn dead: " + unicorn.dead.to_s
        puts "Unicorn attack: " + unicorn.attack.to_s

        puts "\n---------------Muestra de laberinto------------------"
        puts labyrinth.to_s
        puts "-----------------Add monster----------------"
        labyrinth.add_monster(2, 2, unicorn)
        puts labyrinth.to_s
        labyrinth.add_monster(1, 1, unicorn)
        puts labyrinth.to_s
        game = Irrgarten::Game.new(3)
        puts "-----------------Muestra de Game ----------------"
        estado = game.get_game_state
        puts estado.players.to_s
        puts "---------------------------------"
        puts estado.monsters.to_s
        puts "---------------------------------"
        puts estado.labyrinth.to_s

    end
end

test = TestP2.new
test.main
