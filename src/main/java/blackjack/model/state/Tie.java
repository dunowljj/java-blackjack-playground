package blackjack.model.state;

import blackjack.model.card.Cards;

public class Tie extends Finished{
    public Tie(Cards cards) {
        super(cards);
    }

    @Override
    public double earningRate() {
        return 0;
    }
}
