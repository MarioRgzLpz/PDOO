#encoding utf-8

require_relative 'Dice'

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
end

monstruo = Irrgarten::Monster.new("Unicorn", 3, 5)

monstruo.set_pos(2,2)

puts "Monstuo ataca con fuerza: " + monstruo.attack.to_s

puts monstruo.to_s 