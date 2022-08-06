package blackjack.model.state;

import blackjack.model.card.Cards;
import blackjack.model.card.PlayingCards;


public abstract class Started implements State {

    private final Cards cards;

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
