#encoding utf-8

require_relative 'Directions'
require_relative 'Dice'
require_relative 'GameCharacter'
require_relative 'Orientation'
require_relative 'Shield'
require_relative 'Weapon'
require_relative 'GameState'

class TestP1
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
        puts "labyrinth: " + juego.get_labyrinthv
        puts "players: " + juego.get_players
        puts "monsters: " + juego.get_monsters
        puts "currentplayer: " + juego.get_current_player.to_s
        puts "winner: " + juego.get_winner.to_s
        puts "log: " + juego.get_log

        puts "\n----------------Prueba del dado-------------------"
        dice = Irrgarten::Dice.new()
        25.times do
            puts "\nRandom Position: #{dice.random_pos(100)}"
            puts "Who Starts: #{dice.who_starts(10)}"
            puts "Random Intelligence: #{dice.randomIntelligence()}"
            puts "Random Strength: #{dice.randomStrenght()}"
            puts "Resurrect Player: #{dice.resurrectPlayer()}"
            puts "Weapons Reward: #{dice.weaponsReward()}"
            puts "Shields Reward: #{dice.shieldsReward()}"
            puts "Health Reward: #{dice.healthReward()}"
            puts "Weapon Power: #{dice.weaponPower()}"
            puts "Shield Power: #{dice.shieldPower()}"
            puts "Numero de usos restante: #{dice.usesLeft()}"
            puts "Intensidad: #{dice.intensity(5.0)}"
        end
        
    end
end

test = TestP1.new
test.main
