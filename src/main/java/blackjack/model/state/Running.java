package blackjack.model.state;

import blackjack.model.card.Cards;
import blackjack.model.card.PlayingCard;
import blackjack.model.card.PlayingCards;

public class Running extends Started {
    public Running(PlayingCards playingCards) {
        super(playingCards);
    }

    public Running(Cards cards) {
        super(cards);
    }

    //Todo: 허수아비같은 메서드를 어찌해야하는가?
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
