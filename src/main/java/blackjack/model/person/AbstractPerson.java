package blackjack.model.person;


import blackjack.model.card.Cards;

public class AbstractPerson implements Person {
    private final Cards myCards;
    private Name name;
    private BetMoney betMoney;

    public AbstractPerson(Name name) {
        this.myCards = new Cards();
        this.name = name;
    }

    public Cards getMyCards() {
        return myCards;
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
