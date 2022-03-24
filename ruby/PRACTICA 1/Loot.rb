module Deepspace
    class Loot
        def initialize(supplies, weapons, shields, hangars, medals)
            @nSupplies = supplies
            @nWeapons  = weapons
            @nShields  = shields
            @nHangars  = hangars
            @nMedals   = medals
        end

        def nSupplies
            @nSupplies
        end

        def nWeapons
            @nWeapons
        end

        def nShields
            @nShields
        end

        def nHangars
            @nHangars
        end

        def nMedals
            @nMedals
        end
    end
end