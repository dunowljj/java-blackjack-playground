package blackjack.model.card;


public class MyCards extends AbstractCards {
    public static final int BLACKJACK_NUM = 21;
    public static final int DEALER_NEED_RECEIVE_NUM = 16;

    public MyCards() {
        super();
    }

    public void add(Card card) {
        getCards().add(card);
    }

    public boolean isBlackjack() {
       return getCards().stream().map(Card::getNum)
                .reduce(0, (x, y) -> x + y) == BLACKJACK_NUM;
    }

    public boolean isOverLimit() {
        return getCards().stream().map(Card::getNum)
                .reduce(0, (x, y) -> x + y) > BLACKJACK_NUM;
    }

    public boolean needMore() {
        return getCards().stream().map(Card::getNum)
                .reduce(0, (x, y) -> x + y) <= DEALER_NEED_RECEIVE_NUM;
    }

    public int size() {
        return getCards().size();
    }
}