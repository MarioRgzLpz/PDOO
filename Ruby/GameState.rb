#encoding utf-8

module Irrgarten
    class GameState

        def initialize(l, pl, m, cp, w, lg)
            @labyrinthv = l
            @players = pl
            @monsters = m
            @currentPlayer = cp
            @winner = w
            @log = lg
        end
        
        def get_labyrinthv
            @labyrinthv
        end
        
        def get_players
            @players
        end
        
        def get_monsters
            @monsters
        end
        
        def get_current_player
            @currentPlayer
        end
        
        def get_winner
            @winner
        end
        
        def get_log
            @log
        end
    end

end