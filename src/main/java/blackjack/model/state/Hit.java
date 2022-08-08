package blackjack.model.state;

import blackjack.model.card.Cards;
import blackjack.model.card.PlayingCard;
import blackjack.model.card.PlayingCards;

public class Hit extends Running {
    public Hit(PlayingCards playingCards) {
        super(playingCards);
    }

    public Hit(Cards cards) {
        super(cards);
    }

    @Override
    public State drawCard(PlayingCard playingCard) {
        cards.add(playingCard);
        if (isBust()) {
            return new Bust(cards);
        }
        return new Hit(cards);
    }
    private boolean isBust() {
        return cards.isBust();
    }

    @Override
    public State stay() {
        return new Stay(cards);
    }

    @Override
    public Cards cards() {
        return super.cards();
    }
}
