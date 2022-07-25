package blackjack.model.card;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Cards {
    public static final int BLACKJACK_NUM = 21;
    public static final int DEALER_NEED_RECEIVE_NUM = 16;
    private final int START_CARD_NUM = 1;
    private final int END_CARD_NUM = 9;
    private final List<Card> cards;


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


    public void add(Card card) {
        cards.add(card);
    }

    public boolean isBlackjack() {
       return cards.stream().map(Card::getNum)
                .reduce(0, (x, y) -> x + y) == BLACKJACK_NUM;
    }

    public boolean isOver() {
        return cards.stream().map(Card::getNum)
                .reduce(0, (x, y) -> x + y) > BLACKJACK_NUM;
    }

    public boolean needMore() {
        return cards.stream().map(Card::getNum)
                .reduce(0, (x, y) -> x + y) <= DEALER_NEED_RECEIVE_NUM;
    }

    public int size() {
        return cards.size();

    }

    public List<Card> getCards() {
        return cards;
    }
}