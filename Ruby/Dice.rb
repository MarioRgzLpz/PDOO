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
    end
end