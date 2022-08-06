package blackjack.model.state;

import blackjack.model.card.PlayingCards;

public class Bust extends Finished {
    public Bust(PlayingCards playingCards) {
        super(playingCards);
    }
}
