package blackjack.model;

import java.util.ArrayList;
import java.util.List;

public class AbstractPerson implements Person {

    private final List<Card> deck;
    private Name name;
    private BetMoney betMoney;

    public AbstractPerson(Name name) {
        this.deck = new ArrayList<>();
        this.name = name;
    }

    @Override
    public void bet(int money) {
        this.betMoney = new BetMoney(money);
    }

    @Override
    public int getBetMoney() {
        return this.betMoney.getMoney();
    }


    @Override
    public Name getName() {
        return name;
    }
}
