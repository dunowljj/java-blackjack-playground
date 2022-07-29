package blackjack.model.person;


public class Dealer extends AbstractPerson {
    public static final String NAME_OF_DEALER = "딜러";

    public Dealer() {
        super(new Name(NAME_OF_DEALER));
    }

    public void earn(double v) {
        setRevenue((int) v);
    }

    public boolean needMoreCard() {
        return getMyCards().needMore();
    }
}
