#encoding utf-8

require_relative 'Monster'
require_relative 'Player'
require_relative 'Directions'

module Irrgarten
    class Labyrinth

        @@BLOCK_CHAR = 'X'
        @@EMPTY_CHAR = '-'
        @@MONSTER_CHAR = 'M'
        @@COMBAT_CHAR = 'C'
        @@EXIT_CHAR = 'E'
        @@ROW = 0
        @@COL = 1

        def initialize(a_n_rows, a_n_cols, a_exit_row, a_exit_col)
            @n_rows = a_n_rows
            @n_cols = a_n_cols
            @exit_row = a_exit_row
            @exit_col = a_exit_col
            @monsters = Array.new(@n_rows) { Array.new(@n_cols, nil) }
            @players = Array.new(@n_rows) { Array.new(@n_cols, nil) }
            @labyrinth = Array.new(@n_rows) { Array.new(@n_cols, @@EMPTY_CHAR) }
        
            @n_rows.times do |i|
              @n_cols.times do |j|
                @labyrinth[i][j] = @@EMPTY_CHAR
              end
            end
            @labyrinth[@exit_row][@exit_col] = @@EXIT_CHAR
        end

        def set_square(row, col, value)
            @labyrinth[row][col] = value
        end

        def set_fuzzy_player(row, col, fuzzy)
            @players[row][col] = fuzzy
        end


        def spread_players(players)
            players.each do |player|
                pos = random_empty_pos
                old_row = -1
                old_col = -1
                monster = put_player_2D(old_row,old_col,pos[@@ROW], pos[@@COL], player)
            end
        end
    
        def have_a_winner
            return @players[@exit_row][@exit_col] != nil
        end
    
        def to_s
        result = ""
    
        @n_rows.times do |i|
            @n_cols.times do |j|
            result += "#{@labyrinth[i][j]}   "
            end
            result += "\n"
        end
    
        result
        end
        
        def add_monster(row, col, monster)
            if empty_pos(row, col) && pos_ok(row, col)
                @labyrinth[row][col] = @@MONSTER_CHAR
                @monsters[row][col] = monster
                monster.set_pos(row, col)
            end
        end
    
        def put_player(direction, player)
            old_row = player.row
            old_col = player.col
            new_pos = dir_2_pos(player.row, player.col, direction)
            monster = put_player_2D(old_row, old_col, new_pos[@@ROW], new_pos[@@COL], player)
            return monster
        end
    
        def add_block(orientation, start_row, start_col, length)
            if (orientation == Orientation::HORIZONTAL)
                inc_row = 0
                inc_col = 1 
            else
                inc_row = 1
                inc_col = 0 
            end
            row = start_row
            col = start_col
            length.times do
                if (pos_ok(row, col) && empty_pos(row, col))
                    @labyrinth[row][col] = @@BLOCK_CHAR
                end
                row += inc_row
                col += inc_col
            end
        end
    
        def valid_moves(row, col)
            output = []
            output << Directions::UP if can_step_on(row - 1, col)
            output << Directions::DOWN if can_step_on(row + 1, col)
            output << Directions::LEFT if can_step_on(row, col - 1)
            output << Directions::RIGHT if can_step_on(row, col + 1)
            return output

        end
    
        def pos_ok(row, col)
            return row >= 0 && row < @n_rows && col >= 0 && col < @n_cols
        end
    
        def empty_pos(row, col)
            return @labyrinth[row][col] == @@EMPTY_CHAR
        end
    
        def monster_pos(row, col)
            return @labyrinth[row][col] == @@MONSTER_CHAR
        end
    
        def exit_pos(row, col)
            return @labyrinth[row][col] == @@EXIT_CHAR
        end
    
        def combat_pos(row, col)
            return @labyrinth[row][col] == @@COMBAT_CHAR
        end
    
        def can_step_on(row, col)
            pos_ok(row, col) && (empty_pos(row, col) || monster_pos(row, col) || exit_pos(row, col))
        end
    
        def update_old_pos(row, col)
            if pos_ok(row, col)
                if combat_pos(row, col)
                    @labyrinth[row][col] = @@MONSTER_CHAR
                else
                    @labyrinth[row][col] = @@EMPTY_CHAR
                end
            end
        end
    
        def dir_2_pos(row, col, direction)
            case direction
            when Directions::LEFT
                col -= 1
            when Directions::RIGHT
                col += 1
            when Directions::UP
                row -= 1
            when Directions::DOWN
                row += 1
            end
            return [row, col]
        end
    
        def random_empty_pos
            row = Dice.random_pos(@n_rows)
            col = Dice.random_pos(@n_cols)
            while(!empty_pos(row,col))
                row = Dice.random_pos(@n_rows)
                col = Dice.random_pos(@n_cols)
            end
            return [row, col]
        end
    
        def put_player_2D(old_row, old_col, row, col, player)
            output = nil
            if (can_step_on(row, col))
                if pos_ok(old_row, old_col)
                    jugador = @players[old_row][old_col]
                    if(jugador == player)
                        update_old_pos(old_row, old_col)
                        @players[old_row][old_col] = nil
                    end
                end
                monster_p = monster_pos(row, col)
                if monster_p
                    @labyrinth[row][col] = @@COMBAT_CHAR
                    output = @monsters[row][col]
                else
                    number = player.number
                    @labyrinth[row][col] = number.to_s
                end
                @players[row][col] = player
                player.set_pos(row, col)
            end
            return output
        end

        private :pos_ok, :empty_pos, :monster_pos, :exit_pos, :combat_pos, :can_step_on, :update_old_pos, :dir_2_pos, :random_empty_pos, :put_player_2D
    end
end