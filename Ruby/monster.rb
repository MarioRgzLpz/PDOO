#encoding utf-8

require_relative 'dice'

module Irrgarten
    class Monster
        @@INITIAL_HEALTH=5

        def initialize (a_name, a_intelligence, a_strength)
            @name = a_name
            @intelligence = a_intelligence
            @strength = a_strength
            @health = @@INITIAL_HEALTH
            @row = nil
            @col = nil
        end

        def dead
            return @health <= 0
        end

        def attack
            return Dice.intensity(@strength)
        end

        def defend(received_attack)
            is_dead = dead
            if !is_dead
                defensive_energy = Dice.intensity(@intelligence)
                if received_attack > defensive_energy
                    got_wounded
                    is_dead = dead
                end
            end
            return is_dead
        end

        def set_pos(a_row,a_col)
            @row = a_row
            @col = a_col
        end


        def to_s
            "Name: #{@name}, " +
            "Intelligence: #{@intelligence}, " +
            "Strength: #{@strength}, " +
            "Health: #{@health}, " +
            "Position: (#{@row},#{@col})\n"
        end

        
        def got_wounded
            @health -= 1
        end

        private :got_wounded
    end
end
