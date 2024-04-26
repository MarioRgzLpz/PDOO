#encoding utf-8

require_relative 'dice'
require_relative 'labyrinth_character'

module Irrgarten
    class Monster < LabyrinthCharacter
        @@INITIAL_HEALTH=5

        def initialize (a_name, a_intelligence, a_strength)
            super(a_name,a_intelligence,a_strength, @@INITIAL_HEALTH)
        end

        def attack
            return Dice.intensity(@strength)
        end

        def defend(received_attack)
            is_dead = dead()
            if !is_dead
                defensive_energy = Dice.intensity(@intelligence)
                if received_attack > defensive_energy
                    got_wounded
                    is_dead = dead()
                end
            end
        end

        private :got_wounded
    end
end
