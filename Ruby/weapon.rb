#encoding utf-8

require_relative 'combat_element'

module Irrgarten
    class Weapon < CombatElement
        def attack
            return produce_effect
        end

        def to_s
            return "W" + super
        end
    end
end