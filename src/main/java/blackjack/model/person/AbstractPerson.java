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
        if (myCards.isOverLimitAceConsidered()) {
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
        return calculateDeckScore() == max;
    }
    @Override
    public void setWinner() {
        deckStatus = DeckStatus.WIN;
    }

    @Override
    public void markIfBlackjack() {
        if (getMyCards().isBlackjack()) {
            deckStatus = DeckStatus.BLACKJACK;
        }
    }
    @Override
    public boolean isBlackjack() {
        return deckStatus.equals(DeckStatus.BLACKJACK);
    }

    @Override
    public int calculateDeckScore() {
        int sum = calculateSumOfCards();

        if (myCards.includeAce()) {
            int aceSize = myCards.getNumOfAce();
            return chooseSmallAceUntilInLimit(sum, aceSize);
        }

        return sum;
    }

    private Integer calculateSumOfCards() {
        return getMyCards().getCards().stream()
                .map(card -> card.getNum())
                .reduce(0, (x, y) -> x + y);
    }

    private int chooseSmallAceUntilInLimit(int sum, int aceSize) {
        while (aceSize-- > 0 && isOverLimit()) {
            sum -= 10;
        }
        return sum;
    }

    @Override
    public boolean isDealer() {
        return getName().equals(new Name(NAME_OF_DEALER));
    }

    @Override
    public boolean isOverLimit() {
        return myCards.isOverLimit();
    }

    @Override
    public boolean isOverLimitAceConsidered() {
        return myCards.isOverLimitAceConsidered();
    }

    public MyCards getMyCards() {
        return myCards;
    }

    @Override
    public void calculateRevenue() {
        revenue = deckStatus.calculate(getBetMoney());
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
