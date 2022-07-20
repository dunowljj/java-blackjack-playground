package blackjack;

import blackjack.controller.BlackjackGame;

public class BlackjackApplication {
    public static void main(String[] args) {
        BlackjackGame game = new BlackjackGame();
        game.run();
    }
}
