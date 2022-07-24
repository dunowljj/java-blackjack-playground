package blackjack.model.person;


import blackjack.model.card.Cards;

import java.util.concurrent.Delayed;

public class AbstractPerson implements Person {
    public static final int NUM_OF_FIRST_DISTRIBUTION = 2;
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
    public void recieveCard(Cards cards) {
        for (int i = 0; i < NUM_OF_FIRST_DISTRIBUTION; i++) {
            myCards.add(cards.getCards().remove(0));
        }
    }

    @Override
    public void bet(int money) {
        this.betMoney = new BetMoney(money);
    }

    @Override
    public StringBuilder getCurrentOwnCards(int num) {
        StringBuilder message = new StringBuilder();
        message.append(name).append("카드: ");

        myCards.getCards().stream().limit(num)
                .forEach((card) -> message.append(card.name()).append(", "));

        message.deleteCharAt(message.lastIndexOf(", ")).append("\n");

        return message;
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
