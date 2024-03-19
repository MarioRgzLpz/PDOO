#encoding utf-8

require_relative 'GameState'



module Irrgarten
    class Game
        @@MAX_ROUNDS = 10
        
        def initialize(n_players)
            @labyrinth = Labyrinth.new(5,5,4,4)
            @log="Game starts"
            @players=Array.new(n_players)
            @monsters=Array.new(n_players)

            n_players.times do |i|
                @players << Player.new(i, Dice.random_intelligence, Dice.random_strenght)
            end


            n_players.times do |i|
                @monsters << Monster.new("Monster" + i.to_s, Dice.random_intelligence, Dice.random_strenght)
            end

            @current_player_index=Dice.who_starts(n_players)
            @current_player=@players[@current_player_index]

            configure_labyrinth

        end

        def finished
            return @labyrinth.have_a_winner
        end

        def next_step(preferredDirection)
            
        end

        def get_game_state
            string_players = ""
            string_monsters = ""
            @players.each do |player|
                string_players += player.to_s
            end
            @monsters.each do |monster|
                string_monsters += monster.to_s
            end

            return GameState.new(@labyrinth.to_s, string_players, string_monsters, @current_player, finished, @log)
        end

        def configure_labyrinth
            n_rows=5
            n_cols=5
            manual_labyrinth = [
                ['X', 'X', 'X', 'X', 'X'],
                ['X', '-', '-', '-', 'X'],
                ['X', '-', 'X', '-', 'X'],
                ['X', '-', '-', 'E', 'X'],
                ['X', 'X', 'X', 'X', 'X']
              ]
            n_rows.times do |i|
                n_cols.times do |j|
                    @labyrinth.set_square(i, j, manual_labyrinth[i][j])
                end
            end
        end

        def next_player
            @current_player_index += 1
            @current_player=players[@current_player_index]
        end

        def actual_direction(preferredDirection)

        end

        def combat(monster)

        end

        def manage_reward(winner)

        end

        def manage_resurrection

        end

        def log_player_won
            @log += "Player wins the combat" + "\n";
        end

        def log_monster_won
            @log += "Monster wins the combat" + "\n";
        end

        def log_resurrected
            @log += "Player resurrects" + "\n";
        end

        def log_player_skip_turn
            @log += "Player lost the turn" + "\n";
        end

        def log_player_no_orders
            @log += "Player couldnt follow the orders" + "\n";
        end

        def log_no_monster
            @log += "Player get into an empty square" + "\n";
        end

        def log_rounds(rounds, max)
            @log += "Max rounds achieved" + "\n";
        end

    end
end