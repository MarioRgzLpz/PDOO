#encoding utf-8

require_relative 'dice'

module Irrgarten
    class FuzzyPlayer < Player
        def initialize (other)
            copia(other)
        end

        def move(direction, valid_moves)
            direction_preference = super(direction, valid_moves)
            return Dice.next_step(direction_preference, valid_moves, @intelligence)
        end

        def attack
            return Dice.intensity(@strength + sum_weapons)
        end

        def defensive_energy
            return Dice.intensity(@intelligence + sum_shields)
        end

        def to_s
            return "Fuzzy" + super
        end

        private :got_wounded
    end
end