#encoding utf-8

require_relative 'Dice'

module Irrgarten
    class FuzzyPlayer < Player
        def initialize (other)
            new=other
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