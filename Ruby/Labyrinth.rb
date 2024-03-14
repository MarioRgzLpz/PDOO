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
            @nRows = a_n_rows
            @nCols = a_n_cols
            @exitRow = a_exit_row
            @exitCol = a_exit_col
            @monsters = Array.new(@nRows, Array.new(@nCols))
            @players = Array.new(@nRows, Array.new(@nCols))
            @labyrinth = Array.new(a_n_rows) { Array.new(a_n_cols, @@EMPTY_CHAR) }
        
            @nRows.times do |i|
              @nCols.times do |j|
                @labyrinth[i][j] = @@BLOCK_CHAR if i == 0 || j == 0 || i == @nRows - 1 || j == @nCols - 1
                @labyrinth[i][j] = @@EXIT_CHAR if i == @exitRow && j == @exitCol
              end
            end
        end

        def spread_players(players)
            raise NotImplementedError.new("spread_players method is not implemented yet.")
        end
    
        def have_a_winner
            !@players[exit_row][exit_col].nil?
        end
    
        def to_s
        result = ""
    
        @nRows.times do |i|
            @nCols.times do |j|
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
            raise NotImplementedError.new("put_player method is not implemented yet.")
        end
    
        def add_block(orientation, start_row, start_col, length)
            raise NotImplementedError.new("add_block method is not implemented yet.")
        end
    
        def valid_moves(row, col)
            raise NotImplementedError.new("valid_moves method is not implemented yet.")
        end
    
        def pos_ok(row, col)
            row >= 0 && row < @nRows && col >= 0 && col < @nCols
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
            if pos_oK(row, col)
                if combat_pos(row, col)
                @labyrinth[row][col] = @@MONSTER_CHAR
                else
                @labyrinth[row][col] = @@EMPTY_CHAR
                end
            end
        end
    
        def dir_2_pos(row, col, direction)
            case direction
            when :LEFT
                col -= 1
            when :RIGHT
                col += 1
            when :UP
                row -= 1
            when :DOWN
                row += 1
            end
            return [row, col]
        end
    
        def random_empty_pos
            row = Dice.random_pos(@nRows)
            col = Dice.random_pos(@nCols)
            row, col = Dice.random_pos(@nRows), Dice.random_pos(@nCols) until empty_pos(row, col)
            return [row, col]
        end
    
        def put_player_2D(oldRow, oldCol, row, col, player)
            raise NotImplementedError.new("putPlayer2D method is not implemented yet.")
        end

        private :pos_ok, :empty_pos, :monster_pos, :exit_pos, :combat_pos, :can_step_on, :update_old_pos, :dir_2_pos, :random_empty_pos, :put_player_2D
    end
end

labyrinth = Irrgarten::Labyrinth.new(5, 5, 2, 2)
monstruo = Irrgarten::Monster.new("unicorn", 9.0, 3.0)
puts labyrinth.to_s
labyrinth.add_monster(1, 1, monstruo)
puts "After adding monster"
puts labyrinth.to_s
posicion = labyrinth.send(:random_empty_pos)
puts "Random empty position: (#{posicion[0]}, #{posicion[1]})"
puts labyrinth.send(:can_step_on,posicion[0], posicion[1])
newposicion = labyrinth.send(:dir_2_pos,4, 3, Irrgarten::Directions::DOWN)
puts "New position: (#{newposicion[0]}, #{newposicion[1]})"
puts labyrinth.send(:can_step_on,newposicion[0], newposicion[1])