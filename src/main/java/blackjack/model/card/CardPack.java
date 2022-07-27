package blackjack.model.card;

import java.util.Collections;

public class CardPack extends AbstractCards {
    private final int START_CARD_NUM = 1;
    private final int END_CARD_NUM = 9;

    public CardPack() {
        super();
        setUpWholeCard();
    }

    public void setUpWholeCard() {
        addDefaultCards();
        Collections.shuffle(getCards());
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
            getCards().add(new Card(shape, i + ""));
        }
    }
    private void addAlphabetCards(String shape) {
        getCards().add(new Card(shape, "J"));
        getCards().add(new Card(shape, "K"));
        getCards().add(new Card(shape, "Q"));
        getCards().add(new Card(shape, "A"));
    }

}
