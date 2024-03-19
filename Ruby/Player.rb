#encoding utf-8

require_relative 'Dice'
require_relative 'Weapon'
require_relative 'Shield'

module Irrgarten
    class Player
        @@MAX_WEAPONS=2
        @@MAX_SHIELDS=3
        @@INITIAL_HEALTH=10
        @@HITS2LOSE=3

        def initialize (a_number, a_intelligence, a_strength)
            @name = "Player #" + a_number.to_s
            @number = a_number
            @intelligence = a_intelligence
            @strength = a_strength
            @health = @@INITIAL_HEALTH
            @row = nil
            @col = nil
            @consecutive_hits=0
            @weapons = nil
            @shields = nil
        end

        attr_reader :row, :col

        def resurrect
            return Dice.resurrect_player
        end

        def set_pos(a_row,a_col)
            @row = a_row
            @col = a_col
        end

        def dead
            if @health <= 0
                return true
            else
                return false
            end
        end

        def move(direction, valid_moves)

        end

        def attack
            return Dice.intensity(@strength)
        end

        def defend(received_attack)
            manage_hit(received_attack)
        end

        def receive_reward

        end

        def to_s
            "\nName: #{@name}\n" +
            "Intelligence: #{@intelligence}\n" +
            "Strength: #{@strength}\n" +
            "Health: #{@health}\n" +
            "Position: (#{@row},#{@col})\n"
        end

        def receive_weapon(w)

        end

        def receive_shield(s)

        end

        def new_weapon
            power = Dice.weapon_power
            uses = Dice.uses_left
            w = Weapon.new(power, uses)
            return w
        end

        def new_shield
            power = Dice.shield_power
            uses = Dice.uses_left
            s = Shield.new(power, uses)
            return s
        end

        def sum_weapons
            total = 0
            @weapons.each do |w|
                total += w.attack
            end
            return total
        end

        def sum_shields
            total = 0
            @shields.each do |s|
                total += s.defend
            end
            return total
        end

        def defensive_energy
            return sum_shields + @intelligence
        end

        def manage_hit(received_attack)

        end

        def reset_hits
            @consecutive_hits = 0
        end

        def got_wounded
            @health -= 1
        end

        def inc_consecutive_hits
            @consecutive_hits += 1
        end

        private :got_wounded
    end

end

player = Irrgarten::Player.new('0', 3, 5)
w = player.new_weapon
s = player.new_shield
puts player.to_s
puts "Weapon: " + w.to_s
puts "Shield: " + s.to_s
puts player.resurrect
