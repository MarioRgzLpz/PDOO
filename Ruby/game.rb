#encoding utf-8

require_relative 'game_state'
require_relative 'labyrinth'
require_relative 'orientation'
require_relative 'game_character'



module Irrgarten
    class Game
        @@MAX_ROUNDS = 10
        
        def initialize(n_players)
            n_rows = 9
            n_cols = 9
            exit_row = 7
            exit_col = 8
            @labyrinth = Labyrinth.new(n_rows,n_cols,exit_row,exit_col)
            @log=""
            @players = Array.new
            @monsters = Array.new

            n_players.times do |i|
                @players << Player.new(i, Dice.random_intelligence, Dice.random_strenght)
            end


            n_players.times do |i|
                @monsters << Monster.new("Monster " + i.to_s, Dice.random_intelligence, Dice.random_strenght)
            end

            @monsters << Monster.new("Final Boss ", 10, 10)
            @current_player_index=Dice.who_starts(n_players)
            @current_player=@players[@current_player_index]

            configure_labyrinth()

        end

        def finished
            return @labyrinth.have_a_winner
        end

        def next_step(preferredDirection)
            @log = ""
            dead = @current_player.dead
            if(!dead)
                direction = actual_direction(preferredDirection)
                if(direction != preferredDirection)
                    log_player_no_orders()
                end
                monster = @labyrinth.put_player(direction, @current_player)
                if(monster == nil)
                    log_no_monster()
                else
                    winner = combat(monster)
                    manage_reward(winner)
                end
            else
                manage_resurrection()
            end
            end_game = finished()
            if(!end_game)
                next_player()
            end
            return end_game
        end

        def game_state
            string_players = ""
            string_monsters = ""
            @players.each do |player|
                string_players += player.to_s
            end
            @monsters.each do |monster|
                string_monsters += monster.to_s
            end

            return GameState.new(@labyrinth.to_s, string_players, string_monsters, @current_player_index, finished(), @log)
        end

        def configure_labyrinth
            @labyrinth.add_block(Orientation::HORIZONTAL, 0, 0, 9);
            @labyrinth.add_block(Orientation::HORIZONTAL, 8, 0, 9);
            @labyrinth.add_block(Orientation::HORIZONTAL, 5, 1, 2);
            @labyrinth.add_block(Orientation::HORIZONTAL, 6, 2, 3);
            @labyrinth.add_block(Orientation::HORIZONTAL, 2, 4, 2);
            @labyrinth.add_block(Orientation::HORIZONTAL, 4, 4, 2);
            @labyrinth.add_block(Orientation::VERTICAL, 1, 0, 5);
            @labyrinth.add_block(Orientation::VERTICAL, 2, 2, 3);
            @labyrinth.add_block(Orientation::VERTICAL, 1, 8, 6);
            @labyrinth.add_block(Orientation::VERTICAL, 2, 6, 7);

            @labyrinth.add_monster(4, 1, @monsters[0]);
            @labyrinth.add_monster(7, 7, @monsters[1]);
            @labyrinth.add_monster(3, 4, @monsters[2]);
            @labyrinth.add_monster(5, 3, @monsters[3]);

            @labyrinth.spread_players(@players)
        end

        def next_player
            index = (@current_player_index + 1)% @players.size
            @current_player_index = index
            @current_player=@players[@current_player_index]
        end

        def actual_direction(preferredDirection)
            current_row = @current_player.row
            current_col = @current_player.col
            valid_moves = @labyrinth.valid_moves(current_row, current_col)
            return @current_player.move(preferredDirection, valid_moves)
        end

        def combat(monster)
            rounds = 0
            winner = GameCharacter::PLAYER
            player_attack = @current_player.attack
            lose = monster.defend(player_attack)
            while(!lose && rounds < @@MAX_ROUNDS)
                rounds += 1
                winner = GameCharacter::MONSTER
                monster_attack = monster.attack
                lose = @current_player.defend(monster_attack)
                if(!lose)
                    player_attack = @current_player.attack
                    winner = GameCharacter::PLAYER
                    lose = monster.defend(player_attack)
                end
            end
            log_rounds(rounds, @@MAX_ROUNDS)
            return winner
        end

        def manage_reward(winner)
            if(winner == GameCharacter::PLAYER)
                @current_player.receive_reward()
                log_player_won()
            else 
                log_monster_won()
            end

        end

        def manage_resurrection
            resurrect = Dice.resurrect_player
            if(resurrect)
                @current_player.resurrect
                log_resurrected()
            else
                log_player_skip_turn()
            end
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
            @log += "Round " + rounds.to_s + " achieved with a max of " + max.to_s + " rounds" + "\n";
        end

    end
end