package blackjack.model.person;


import blackjack.model.card.CardPack;
import blackjack.model.card.Cards;
import blackjack.model.card.MyCards;
import blackjack.view.InputView;

public class AbstractPerson implements Person {
    private final MyCards myCards;
    private Name name;
    private BetMoney betMoney;

    public AbstractPerson(Name name) {
        this.myCards = new MyCards();
        this.name = name;
    }

    @Override
    public void bet(int money) {
        this.betMoney = new BetMoney(money);
    }

    @Override
    public StringBuilder getNameAndCards() {
        StringBuilder message = new StringBuilder();
        message.append(name).append("카드: ");

        myCards.getCards().stream()
                .forEach((card) -> message.append(card.name()).append(", "));

        message.deleteCharAt(message.lastIndexOf(", ")).append("\n");

        return message;
    }

    @Override
    public StringBuilder getNameAndCards(int openLimitNum) {
        StringBuilder message = new StringBuilder();
        message.append(name).append("카드: ");

        myCards.getCards().stream().limit(openLimitNum)
                .forEach((card) -> message.append(card.name()).append(", "));

        message.deleteCharAt(message.lastIndexOf(", ")).append("\n");

        return message;
    }
    @Override
    public void askUntilNo(CardPack providedCards) {
        if (myCards.isOverLimit()) {
            return;
        }
        if (wantReceive(InputView.askReceiveMore(this))) {
            receiveCard(providedCards, 1);
            InputView.openCards(getNameAndCards().toString());
            askUntilNo(providedCards);
        }
    }

    @Override
    public boolean wantReceive(String askReceiveMore) {
        return askReceiveMore.equals("y");
    }

    @Override
    public void receiveCard(Cards providedCards, int amount) {
        for (int i = 0; i < amount; i++) {
            myCards.add(providedCards.getCards().remove(0));
        }
    }
    @Override
    public boolean receiveCardIfNeed(Cards cards) {
        if (needMoreCard()) {
            receiveCard(cards, 1);
            return true;
        }
        return false;
    }

    @Override
    public boolean needMoreCard() {
        return myCards.needMore();
    }

    @Override
    public boolean isOverLimit() {
        return myCards.isOverLimit();
    }

    public MyCards getMyCards() {
        return myCards;
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
