package blackjack.model.state;

import blackjack.model.card.Cards;

public class Win extends Finished {

    public Win(Cards cards) {
        super(cards);
    }

    @Override
    public double earningRate() {
        return 0;
    }

    @Override
    public double profit(double betMoney) {
        return 0;
    }
}
