#encoding utf-8

module Irrgarten
    class Dice
        @@MAX_USES=5
        @@MAX_INTELLIGENCE=10.0
        @@MAX_STRENGTH=10.0
        @@RESURRECT_PROB=0.3
        @@WEAPONS_REWARD=2
        @@SHIELDS_REWARD=3
        @@HEALTH_REWARD=5
        @@MAX_ATTACK=3
        @@MAX_SHIELD=2

        def initialize()
            @generator=Random.new
        end

        def random_pos(max)
            return @generator.rand(max)
        end

        def who_starts(nplayers)
            return @generator.rand(nplayers)
        end
        
        def randomIntelligence()
            return @generator.rand() * @@MAX_INTELLIGENCE
        end

        def randomStrenght()
            return @generator.rand() * @@MAX_STRENGTH
        end

        def resurrectPlayer()
            return @generator.rand() < @@RESURRECT_PROB
        end

        def weaponsReward()
            return @generator.rand(@@WEAPONS_REWARD + 1)
        end

        def shieldsReward()
            return @generator.rand(@@SHIELDS_REWARD + 1)
        end

        def healthReward()
            return @generator.rand(@@HEALTH_REWARD + 1)
        end

        def weaponPower()
            return @generator.rand() * @@MAX_ATTACK
        end

        def shieldPower()
            return @generator.rand() * @@MAX_SHIELD
        end

        def usesLeft()
            return @generator.rand(@@MAX_USES + 1)
        end

        def intensity(competence)
            return @generator.rand() * competence
        end

        def discardElement(usesLeft)
            probabilidadDiscard = (@@MAX_USES - usesLeft).to_f / @@MAX_USES
            return @generator.rand() < probabilidadDiscard
        end
        
    end
end
