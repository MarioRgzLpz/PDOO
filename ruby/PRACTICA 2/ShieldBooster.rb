module Deepspace
    class ShieldBooster
        @@NULLPOWER=1.0
        def initialize(na, bo, us)
            @name = na
            @boost = bo
            @uses = us
        end

        def self.newCopy(sbooster)
            new(sbooster.name, sbooster.boost, sbooster.uses)
        end

        def boost
            @boost
        end

        def uses
            @uses
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