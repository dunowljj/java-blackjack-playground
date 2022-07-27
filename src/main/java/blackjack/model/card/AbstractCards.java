package blackjack.model.card;

import java.util.ArrayList;
import java.util.List;

public class AbstractCards implements Cards{

    private final List<Card> cards;

    public AbstractCards() {
        this.cards = new ArrayList<>();
    }

    @Override
    public List<Card> getCards() {
        return cards;
    }
}
