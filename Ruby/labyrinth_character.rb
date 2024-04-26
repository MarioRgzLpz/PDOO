#encoding utf-8

module Irrgarten
    class LabyrinthCharacter
        def initialize (a_name, a_intelligence, a_strength, a_health)
            @name = a_name
            @intelligence = a_intelligence
            @strength = a_strength
            @health = a_health
            @row = nil
            @col = nil
        end

        def copia(otro)
            @name = otro.name
            @intelligence = otro.intelligence
            @strength = otro.strength
            @health = otro.health
            set_pos(otro.row, otro.col)

        end

        attr_reader :row, :col, :name

        def intelligence
            return @intelligence
        end

        def strength
            return @strength
        end

        def health
            return @health
        end

        def health= a_health
            @health = a_health
        end

        def new=(otro)
            new(otro.intelligence, otro.strength, otro.health)
        end

        def set_pos(a_row,a_col)
            @row = a_row
            @col = a_col
        end

        def dead
            return @health <= 0
        end

        def attack
        end

        def defend(received_attack)
        end

        def to_s
            return "#{@name}, " \
            "Intelligence: #{@intelligence}, " \
            "Strength: #{@strength}, " \
            "Health: #{@health}, " \
            "Position: (#{@row},#{@col}) "
        end

        def got_wounded
            @health -= 1
        end

        protected :got_wounded, :health=, :intelligence, :strength, :health
    end

end