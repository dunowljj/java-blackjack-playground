package blackjack.model.card;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Cards {
    private final List<Card> cards;
    private final int START_CARD_NUM = 1;
    private final int END_CARD_NUM = 9;


    public Cards() {
        cards = new LinkedList<>();
    }

    public void setUpWholeCard() {
        addDefaultCards();
        Collections.shuffle(cards);
    }
    private void addDefaultCards() {
        addCardShapeOf("클로버");
        addCardShapeOf("스페이드");
        addCardShapeOf("하트");
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

    public void add(Card card) {
        cards.add(card);
    }
}