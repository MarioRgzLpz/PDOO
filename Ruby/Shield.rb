#encoding utf-8

module Irrgarten
    class Shield
        def initialize (a_protection, a_uses)
            @protection = a_protection
            @uses = a_uses
        end
        
        def protect
            if @uses > 0
                @uses -= 1
                return @protection
            else
                return 0
            end
        end

        def to_s
            return "W[#{@protection},#{@uses}]"
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