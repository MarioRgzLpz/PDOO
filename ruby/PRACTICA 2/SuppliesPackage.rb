module Deepspace
    class SuppliesPackage
        def initialize(ap, fu, sp)
            @ammoPower = ap
            @fuelUnits = fu
            @shieldPower = sp
        end

        def self.newCopy(package)
            new(package.ammoPower, package.fuelUnits, package.shieldPower)
        end

        def ammoPower
            @ammoPower
        end

        def fuelUnits
            @fuelUnits
        end

        def shieldPower
            @shieldPower
        end
    end
end