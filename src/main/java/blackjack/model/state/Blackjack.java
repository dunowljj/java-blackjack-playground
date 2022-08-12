package blackjack.model.state;

import blackjack.model.card.Cards;

public class Blackjack extends Finished{
    public Blackjack(Cards cards) {
        super(cards);
    }


    @Override
    public double earningRate() {
        return 1.5;
    }

    @Override
    public double profit(double betMoney) {
        return betMoney * earningRate();
    }
}
