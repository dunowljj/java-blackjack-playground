package blackjack.model.person;

import blackjack.model.card.Cards;
import blackjack.model.card.PlayingCard;
import blackjack.model.card.PlayingCards;
import blackjack.model.state.*;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public abstract class AbstractParticipant implements Participant{
    public static final String MESSAGE_CARD_START = "카드: ";
    public static final String MESSAGE_RESULT_START = "- 결과: ";
    private final Name name;
    private BetMoney betMoney;

    private Profit profit;

    private State state;
    protected AbstractParticipant(Name name) {
        this.name = name;
    }

    public AbstractParticipant(Name name, PlayingCards playingCards) {
        this.name = name;
        this.state = new Hit(playingCards);
        if (state.cards().isBlackjack()) {
            this.state = new Blackjack(state.cards());

        }
    }

    public AbstractParticipant(Name name, Cards cards) {
        this.name = name;
        this.state = new Hit(cards);
        if (state.cards().isBlackjack()) {
            this.state = new Blackjack(state.cards());
        }
    }

    @Override
    public void drawCard(PlayingCard playingCard) {
        state = state.drawCard(playingCard);
    }

    @Override
    public void bet(BetMoney betMoney) {
        this.betMoney = betMoney;
        return;
    }

    @Override
    public StringBuilder allNamesAndCards() {
        StringBuilder nameAndCard = new StringBuilder();

        nameAndCard.append(getName()).append(MESSAGE_CARD_START)
                .append(state.cards().allCards())
                .append(MESSAGE_RESULT_START).append(state.cards().sumOfScore());

        return nameAndCard;
    }

    @Override
    public void askHitUntilNo(PlayingCards playingCards) {
        while (!isFinished()) {
            askHit(playingCards);
        }
    }

    private void askHit(PlayingCards playingCards) {
        boolean yes = InputView.askWantHitMore(name);

        if (yes) {
            state = state.drawCard(playingCards.nextCard());
            OutputView.printInfo(nameAndCards().toString());
        }

        if (!yes) {
            state = state.stay();
        }
    }

    public void tie() {
        state = new Tie(state.cards());
    }

    public void bust() {
        state = new Bust(state.cards());
    }

    public void win() {
        state = new Win(state.cards());
    }

    public void stay() {state = new Stay(state.cards());
    }

    @Override
    public boolean isFinished() {
        return state.isFinished();
    }

    @Override
    public boolean isBlackjack() {
        return state.getClass().equals(Blackjack.class);
    }

    @Override
    public boolean isBust() {
        return getState().getClass().equals(Bust.class);
    }

    @Override
    public boolean isStay() {
        return getState().getClass().equals(Stay.class);
    }

    @Override
    public boolean isWinner(int max) {
        return getState().cards().sumOfScore() == max;
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public void setState(State state) {
        this.state = state;
    }

    @Override
    public Name getName() {
        return name;
    }

    @Override
    public BetMoney getBetMoney() {
        return betMoney;
    }

    @Override
    public Profit getProfit() {
        return profit;
    }

    @Override
    public void setProfit(double profit) {
        this.profit = new Profit(profit);
    }
}
