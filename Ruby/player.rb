#encoding utf-8

require_relative 'dice'
require_relative 'weapon'
require_relative 'shield'
require_relative 'labyrinth_character'

module Irrgarten
    class Player < LabyrinthCharacter
        @@MAX_WEAPONS=2
        @@MAX_SHIELDS=3
        @@INITIAL_HEALTH=10
        @@HITS2LOSE=3

        def initialize (a_number, a_intelligence, a_strength)

            super("Player " + a_number.to_s, a_intelligence, a_strength, @@INITIAL_HEALTH)
            @number = a_number
            @consecutive_hits = 0
            @weapons = Array.new
            @shields = Array.new
        end


        def copia(other)
            super(other)
            @number = other.number
            @consecutive_hits = other.consecutive_hits
            @weapons = other.weapons
            @shields = other.shields
        end

        attr_reader :weapons, :shields, :number, :consecutive_hits

        def resurrect
            @health = @@INITIAL_HEALTH
            @weapons = Array.new
            @shields = Array.new
            @consecutive_hits = 0
        end

        def set_pos(a_row,a_col)
            @row = a_row
            @col = a_col
        end

        def dead
            return @health <= 0
        end

        def move(direction, valid_moves)
            size = valid_moves.size
            contained = valid_moves.include?(direction)
            if (size > 0 && !contained)
                return valid_moves[0]
            else
                return direction
            end
        end

        def attack
            return Dice.intensity(@strength)
        end

        def defend(received_attack)
            manage_hit(received_attack)
        end

        def receive_reward
            w_reward = Dice.weapons_reward
            s_reward = Dice.shields_reward
            w_reward.times do
                wnew = new_weapon
                receive_weapon(wnew)
            end
            s_reward.times do
                snew = new_shield
                receive_shield(snew)
            end
            extra_health = Dice.health_reward
            @health += extra_health
        end

        def to_s
            weapons_str = @weapons.map(&:to_s).join(', ')
            shields_str = @shields.map(&:to_s).join(', ')
            return super() + ", Weapons: #{weapons_str}, " \
            "Shields: #{shields_str}"
        end

        def receive_weapon(w)
            @weapons.each do |w|
                discard = w.discard
                if discard
                    @weapons.delete(w)
                end
            end
            if @weapons.size < @@MAX_WEAPONS
                @weapons << w
            end
        end

        def receive_shield(s)
            @shields.each do |s|
                discard = s.discard
                if discard
                    @shields.delete(s)
                end
            end
            if @shields.size < @@MAX_SHIELDS
                @shields << s
            end
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
                total += s.protect
            end
            return total
        end

        def defensive_energy
            return sum_shields + @intelligence
        end

        def manage_hit(received_attack)
            defense = defensive_energy
            if received_attack > defense
                got_wounded
                inc_consecutive_hits
            else
                reset_hits
            end
            if ((@consecutive_hits == @@HITS2LOSE) || dead() )
                reset_hits
                lose = true
            else
                lose = false
            end
            return lose
        end

        def reset_hits
            @consecutive_hits = 0
        end

        def inc_consecutive_hits
            @consecutive_hits += 1
        end

        protected :sum_weapons, :sum_shields, :defensive_energy

    end

end

