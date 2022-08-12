package blackjack.model.state;

import blackjack.model.card.Cards;
import blackjack.model.card.PlayingCards;

public abstract class Running extends Started {
    public Running(PlayingCards playingCards) {
        super(playingCards);
    }
    public Running(Cards cards) {
        super(cards);
    }

    @Override
    public double profit(double betMoney) {
        return 0;
    }

    @Override
    public double earningRate() {
        return 0;
    }
}
