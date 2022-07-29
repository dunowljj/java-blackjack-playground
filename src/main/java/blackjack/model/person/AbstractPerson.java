package blackjack.model.person;


import blackjack.model.card.CardPack;
import blackjack.model.card.Cards;
import blackjack.model.card.DeckStatus;
import blackjack.model.card.MyCards;
import blackjack.view.InputView;

public class AbstractPerson implements Person {
    public static final String NAME_OF_DEALER = "딜러";
    private final MyCards myCards;
    private Name name;
    private BetMoney betMoney;
    private DeckStatus deckStatus = DeckStatus.LOSE;
    private int revenue = 0;

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
    public void markIfMax(int max) {
        if (this.isMax(max)) {
            setWinner();
        }
    }

    private boolean isMax(int max) {
        return getSumOfCardNum() == max;
    }
    @Override
    public int getSumOfCardNum() {
        return getMyCards().getCards().stream()
                .map(card -> card.getNum())
                .reduce(0, (x,y) -> x + y);
    }

    @Override
    public void markIfBlackjack() {
        if (getMyCards().isBlackjack()) {
            deckStatus = DeckStatus.BLACKJACK;
        }
    }

    @Override
    public boolean isDealer() {
        return getName().equals(new Name(NAME_OF_DEALER));
    }

    @Override
    public boolean isOverLimit() {
        if (myCards.isIncludeAce() && myCards.isOverLimit()) {

        }
        return myCards.isOverLimit();
    }

    public MyCards getMyCards() {
        return myCards;
    }

    @Override
    public void calculateRevenue() {
        if (isBlackjack()) {
            revenue = (int) (1.5 * getBetMoney());
        }
        if (isWinner()) {
            revenue = (int) (1.0 * getBetMoney());
        }
        if (isLoser()) {
            revenue = (int) (-1.0 * getBetMoney());
        }
        if (isDrawer()) {
            revenue = 0;
        }
    }

    private boolean isDrawer() {
        return deckStatus.equals(DeckStatus.DRAW);
    }

    @Override
    public boolean isBlackjack() {
        return deckStatus.equals(DeckStatus.BLACKJACK);
    }

    private boolean isLoser() {
        return deckStatus.equals(DeckStatus.LOSE);
    }

    @Override
    public boolean isWinner() {
        return deckStatus.equals(DeckStatus.WIN);
    }

    @Override
    public String info() {
        return getName() + ": "+getRevenue();
    }

    @Override
    public int getBetMoney() {
        return betMoney.getMoney();
    }

    @Override
    public Name getName() {
        return name;
    }

    @Override
    public DeckStatus getStatus() {
        return deckStatus;
    }

    @Override
    public void setWinner() {
        deckStatus = DeckStatus.WIN;
    }

    @Override
    public void setDrawer() {
        deckStatus = DeckStatus.DRAW;
    }

    @Override
    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }
}
