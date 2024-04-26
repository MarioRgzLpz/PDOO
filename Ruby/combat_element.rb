#encoding utf-8

module Irrgarten
    class CombatElement
        def initialize (a_effect, a_uses)
            @effect = a_effect
            @uses = a_uses
        end
        
        def produce_effect
            if @uses > 0
                @uses -= 1
                return @effect
            else
                return 0 
            end
        end

        def to_s
            return "[#{@effect},#{@uses}]"
        end

        def discard
            return Dice.discard_element(@uses)
        end

        protected :produce_effect
    end
end