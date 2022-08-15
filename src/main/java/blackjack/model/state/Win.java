package blackjack.model.state;

import blackjack.model.card.Cards;

public class Win extends Finished {

    public Win(Cards cards) {
        super(cards);
    }

    @Override
    public double earningRate() {
        return 1.0;
    }
}
