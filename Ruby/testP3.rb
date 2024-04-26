#encoding utf-8

require_relative 'game'
require_relative './controller/controller'
require_relative './ui/textUI'


class TestP3
    def main
        n_players = 3
        game = Irrgarten::Game.new(n_players)
        view = UI::TextUI.new
        controller = Control::Controller.new(game,view)
        controller.play
    end
end

test = TestP3.new
test.main