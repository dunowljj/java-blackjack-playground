package blackjack.model.state;

import blackjack.model.card.Cards;

public class Stay extends Finished {

    public Stay(Cards cards) {
        super(cards);
        cards.checkChangeAce();
    }

    @Override
    public double earningRate() {
        return -1.0;
    }
}
