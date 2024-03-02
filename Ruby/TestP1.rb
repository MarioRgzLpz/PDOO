#encoding utf-8

require_relative 'Directions'
require_relative 'Dice'
require_relative 'GameCharacter'
require_relative 'Orientation'
require_relative 'Shield'
require_relative 'Weapon'
require_relative 'GameState'

class TestP1
    def main()
        puts "Probando la practica 1:\n"

        puts "Enum Directions:"
        puts Irrgarten::Directions::LEFT
        puts Irrgarten::Directions::RIGHT
        puts Irrgarten::Directions::UP
        puts Irrgarten::Directions::DOWN

        puts "\nEnum GameCharacter:"
        puts Irrgarten::GameCharacter::PLAYER
        puts Irrgarten::GameCharacter::MONSTER

        puts "\nEnum Orientation:"
        puts Irrgarten::Orientation::VERTICAL
        puts Irrgarten::Orientation::HORIZONTAL

        puts "\nCreando instancias de clases:"
        arma1 = Irrgarten::Weapon.new(3.2, 4)
        arma2 = Irrgarten::Weapon.new(1.1, 2)

end
