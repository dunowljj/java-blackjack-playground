package blackjack.model.card;


public class MyCards extends AbstractCards {
    public static final int BLACKJACK_NUM = 21;
    public static final int DEALER_NEED_RECEIVE_NUM = 16;
    public static final int ACE_DIFFERENCE = 10;

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
        int limit = BLACKJACK_NUM;

        /*if (isIncludeAce()) {
            limit = BLACKJACK_NUM + ACE_DIFFERENCE;
        }*/
        return getCards().stream()
                .map(Card::getNum).reduce(0, (x, y) -> x + y) > limit;
    }

    public boolean isIncludeAce() {
        return getCards().stream()
                .filter(card -> card.isAce())
                .findAny().isPresent();
    }

    public boolean needMore() {
        return getCards().stream().map(Card::getNum)
                .reduce(0, (x, y) -> x + y) <= DEALER_NEED_RECEIVE_NUM;
    }

    public int size() {
        return getCards().size();
    }
}