require_relative 'WeaponType'
module Deepspace
    class Weapon
        @@NULLPOWER=1.0
        
        def initialize(na ,ty ,us)
            @name = na
            @type = ty
            @uses = us
        end

        def self.newCopy(weapon)
            new(weapon.name, weapon.type, weapon.us)
        end

        def type
            @type
        end

        def uses
            @uses
        end

        def power()
            @type.power
        end

        def useIt
            if uses > 0
                uses-=1
                @boost
            else
                @@NULLPOWER
            end
        end
    end
end
