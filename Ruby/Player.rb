#encoding utf-8

require_relative 'Dice'

module Irrgarten
    class Player
        @@MAX_WEAPONS=2
        @@MAX_SHIELDS=3
        @@INITIAL_HEALTH=10
        @@HITS2LOSE=3

        def initialize (a_number, a_intelligence, a_strength)
            @name = "Player #" + a_number.to_s
            @number = a_number
            @intelligence = a_intelligence
            @strength = a_strength
            @health = @@INITIAL_HEALTH
            @row = nil
            @col = nil
            @consecutive_hits=0
            @weapons
            @shields
        end

        attr_reader :row, :col

        def resurrect
            return Dice.resurrectPlayer
        end

        def dead
            if @health <= 0
                return true
            else
                return false
            end
        end

        def attack
            return Dice.intensity(@strength)
        end

        def defend

        end

        def set_pos(a_row,a_col)
            @row = a_row
            @col = a_col
        end


        def to_s
            "Name: #{@name}\n" +
            "Intelligence: #{@intelligence}\n" +
            "Strength: #{@strength}\n" +
            "Health: #{@health}\n" +
            "Position: (#{@row},#{@col})"
        end

        def got_wounded
            @health -= 1
        end

        private :got_wounded
    end
    player = Irrgarten::Player.new('0', 3, 5)

    puts player.resurrect 

end
