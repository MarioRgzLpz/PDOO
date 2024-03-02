#encoding utf-8

module Irrgarten
    class Weapon
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

        def to_s
            return "W[#{@protection},#{@uses}]"
        end
    end
end