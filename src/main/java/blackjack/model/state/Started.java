package blackjack.model.state;

import blackjack.model.card.Cards;
import blackjack.model.card.PlayingCards;


public abstract class Started implements State {

    protected final Cards cards;

    public Started(Cards cards) {
        this.cards = cards;
    }

    public Started(PlayingCards playingCards) {
        this.cards = new Cards(playingCards);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    public Cards cards() {
        return cards;
    }

}
