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

            configure_labyrinth()

        end

        def finished
            return @labyrinth.have_a_winner
        end

        def next_step(preferredDirection)
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

            return GameState.new(@labyrinth.to_s, string_players, string_monsters, @current_player, finished(), @log)
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
            current_row = @current_player.row
            current_col = @current_player.col
            valid_moves = @labyrinth.valid_moves(current_row, current_col)
            output = @current_player.move(preferredDirection, valid_moves)
            return output
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
                log_rounds(rounds, @@MAX_ROUNDS)
            end
            return winner
        end

        def manage_reward(winner)
            if(winner == GameCharacter::PLAYER)
                receive_reward()
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
            @log += "Max rounds achieved" + "\n";
        end

    end
end