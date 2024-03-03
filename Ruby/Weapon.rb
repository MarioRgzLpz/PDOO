#encoding utf-8

module Irrgarten
    class Weapon
        def initialize (a_power, a_uses)
            @power = a_power
            @uses = a_uses
        end
        
        def attack
            if @uses > 0
                @uses -= 1
                return @power
            else
                return 0 
            end
        end

        def to_s
            return "W[#{@power},#{@uses}]"
        end

        def discard
            if Dice.new.discardElement(@uses)
                return true
            else
                return false
            end
        end
    end
end