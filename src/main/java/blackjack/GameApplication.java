package blackjack;

import blackjack.controller.BlackjackController;

public class GameApplication {
    public static void main(String[] args) {
        BlackjackController controller = new BlackjackController();
        controller.run();
    }
}
