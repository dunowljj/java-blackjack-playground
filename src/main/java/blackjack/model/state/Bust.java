package blackjack.model.state;

import blackjack.model.card.Cards;

public class Bust extends Finished {
    public Bust(Cards cards) {
        super(cards);
    }
}
