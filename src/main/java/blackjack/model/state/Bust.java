package blackjack.model.state;

import blackjack.model.card.Cards;

public class Bust extends Finished {
    public Bust(Cards cards) {
        super(cards);
    }

    @Override
    public double earningRate() {
        return -1.0;
    }

    @Override
    public double profit(double betMoney) {
        return betMoney * earningRate();
    }
}
