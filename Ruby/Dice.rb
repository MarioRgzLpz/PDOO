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
        @@generator=Random.new


        def self.random_pos(max)
            return @@generator.rand(max)
        end

        def self.who_starts(nplayers)
            return @@generator.rand(nplayers)
        end
        
        def self.randomIntelligence()
            return @@generator.rand() * @@MAX_INTELLIGENCE
        end

        def self.randomStrenght()
            return @@generator.rand() * @@MAX_STRENGTH
        end

        def self.resurrectPlayer()
            return @@generator.rand() < @@RESURRECT_PROB
        end

        def self.weaponsReward()
            return @@generator.rand(@@WEAPONS_REWARD + 1)
        end

        def self.shieldsReward()
            return @@generator.rand(@@SHIELDS_REWARD + 1)
        end

        def self.healthReward()
            return @@generator.rand(@@HEALTH_REWARD + 1)
        end

        def self.weaponPower()
            return @@generator.rand() * @@MAX_ATTACK
        end

        def self.shieldPower()
            return @@generator.rand() * @@MAX_SHIELD
        end

        def self.usesLeft()
            return @@generator.rand(@@MAX_USES + 1)
        end

        def self.intensity(competence)
            return @@generator.rand() * competence
        end

        def self.discardElement(usesLeft)
            probabilidadDiscard = (@@MAX_USES - usesLeft).to_f / @@MAX_USES
            return @@generator.rand() < probabilidadDiscard
        end
    end
end
