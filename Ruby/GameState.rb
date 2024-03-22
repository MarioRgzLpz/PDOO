#encoding utf-8

module Irrgarten
    class GameState

        def initialize(a_labyrinth, a_players, a_monsters, a_current_player, a_winner, a_log)
            @labyrinth = a_labyrinth
            @players = a_players
            @monsters = a_monsters
            @current_player = a_current_player
            @winner = a_winner
            @log = a_log
        end

        attr_reader :labyrinth, :players, :monsters, :current_player, :winner, :log
        
        def get_labyrinth
            @labyrinth
        end
        
        def get_players
            @players
        end
        
        def get_monsters
            @monsters
        end
        
        def get_current_player
            @current_player
        end
        
        def get_winner
            @winner
        end
        
        def get_log
            @log
        end
    end

end