package blackjack.model;

import java.util.ArrayList;
import java.util.List;

public class AbstractPerson implements Person {

    private final List<Card> deck;
    private Name name;
    private int sumOfNumber = 0;
    private int revenue = 0;

    public AbstractPerson(Name name) {
        this.deck = new ArrayList<>();
        this.name = name;
    }

    @Override
    public Name getName() {
        return name;
    }
}
