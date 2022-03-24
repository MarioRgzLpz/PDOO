require_relative 'GameCharacter'
module Deepspace
    class Dice
        def initialize()
            @NHANGARSPROB=0.25
            @NSHIELDSPROB=0.25
            @NWEAPONSPROB=0.33
            @FIRSTSHOTPROB=0.5
            @generator=Randow.new()
        end
        
        def initWithNHangars()
            if @generator.rand()<@NHANGARSPROB
                return 0
            else
                return 1
            end
        end

        def initWithNShields()
            if @generator.rand()<@NSHIELDSPROB
                return 0
            else
                return 1
            end
        end

        def initWithNWeapons()
            if @generator.rand()<@NWEAPONSPROB
                return 0
            else
                return 1
            end
        end

        def whoStarts(nPlayers)
            return @generator.rand(nPlayers)
        end

        def firstShot()
            if @generator.rand() < @FIRSTSHOTPROB
                return GameCharacter::SPACESTATION
            else
                return GameCharacter::ENEMYSTARSHIP
            end
        end

        def spaceStationMoves(speed)
            if @generator.rand() < speed
                return true
            else
                return false
            end
        end
    end
end