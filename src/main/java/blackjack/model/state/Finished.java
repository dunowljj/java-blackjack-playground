package blackjack.model.state;

import blackjack.model.card.Cards;
import blackjack.model.card.PlayingCard;

public abstract class Finished extends Started{
    public Finished(Cards cards) {
        super(cards);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public State drawCard(PlayingCard playingCard) {
        return null;
    }

    @Override
    public State stay() {
        return null;
    }

    @Override
    public double profit(double betMoney) {
        return earningRate() * betMoney;
    }

}
