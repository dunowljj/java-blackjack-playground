package blackjack.model.person;

import blackjack.model.card.Cards;
import blackjack.model.card.PlayingCard;
import blackjack.model.card.PlayingCards;
import blackjack.model.state.*;

import java.util.List;

public abstract class AbstractParticipant implements Participant{
    private final Name name;
    private BetMoney betMoney;
    private Profit profit;
    private State state;
    public AbstractParticipant(Name name) {
        this.name = name;
    }

    protected AbstractParticipant(Name name, BetMoney betMoney, PlayingCards playingCards) {
        this.name = name;
        this.betMoney = betMoney;
        initialDistribution(new Hit(playingCards));
    }

    public AbstractParticipant(Name name, PlayingCards playingCards) {
        this.name = name;
        initialDistribution(new Hit(playingCards));
    }

    public AbstractParticipant(Name name, Cards cards) {
        this.name = name;
        initialDistribution(new Hit(cards));
    }

    private void initialDistribution(Hit playingCards) {
        this.state = playingCards;
        if (state.cards().isBlackjack()) {
            this.state = new Blackjack(state.cards());
        }
    }

    @Override
    public void drawCard(PlayingCard playingCard) {
        state = state.drawCard(playingCard);
    }

    @Override
    public int sumOfScore() {
        return state.cards().sumOfScore();
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

    //todo: 상태안의 수익을 계산해서 저장해야한다. 혹은 바로 반환
    @Override
    public Profit getProfit() {
        return profit;
    }

    @Override
    public void setProfit(double profit) {
        this.profit = new Profit(profit);
    }

    @Override
    public List<PlayingCard> getCards() {
        return getState().cards().getCards();
    }
}
