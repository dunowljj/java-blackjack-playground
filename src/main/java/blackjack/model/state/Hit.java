package blackjack.model.state;

import blackjack.model.card.Cards;
import blackjack.model.card.PlayingCard;
import blackjack.model.card.PlayingCards;

public class Hit extends Started {
    public Hit(PlayingCards playingCards) {
        super(playingCards);
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
    public Cards cards() {
        return super.cards();
    }
}
