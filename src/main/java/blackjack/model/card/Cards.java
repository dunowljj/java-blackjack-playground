package blackjack.model.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {
    private final List<Card> cards;
    private static final int START_CARD_NUM = 1;
    private static final int END_CARD_NUM = 9;


    public Cards() {
        cards = new ArrayList<>();
    }

    public void setUpWholeCard() {
        setUpDefaultCards();
        Collections.shuffle(cards);
    }
    public void setUpDefaultCards() {
        addCardShapeOf("Clover");
        addCardShapeOf("Spade");
        addCardShapeOf("Heart");
    }
    private void addCardShapeOf(String shape) {
        addNumberCards(shape);
        addAlphabetCards(shape);
    }
    private void addNumberCards(String shape) {
        for (int i = START_CARD_NUM; i <= END_CARD_NUM; i++) {
            cards.add(new Card(shape, i + ""));
        }
    }
    private void addAlphabetCards(String shape) {
        cards.add(new Card(shape, "J"));
        cards.add(new Card(shape, "K"));
        cards.add(new Card(shape, "Q"));
        cards.add(new Card(shape, "A"));
    }

    public List<Card> getCards() {
        return cards;
    }
}