package blackjack.model.state;

import blackjack.model.card.PlayingCards;

public class Stay extends Finished {

    public Stay(PlayingCards playingCards) {
        super(playingCards);
    }
}
