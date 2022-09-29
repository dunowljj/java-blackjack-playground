package blackjack.model.person;

import blackjack.model.card.Cards;
import blackjack.model.card.PlayingCard;
import blackjack.model.card.PlayingCards;
import blackjack.model.state.*;
import blackjack.view.InputView;

import java.util.List;

public abstract class AbstractParticipant implements Participant{
    private final Name name;
    private BetMoney betMoney;
    private Profit profit;
    private State state;
    public AbstractParticipant(Name name) {
        this.name = name;
    }

    protected AbstractParticipant(Name name, BetMoney betMoney) {
        this.name = name;
        this.betMoney = betMoney;
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
    public void askHitUntilNo(PlayingCards playingCards) {
        while (!isFinished()) {
            askHit(playingCards);
        }
    }

    private void askHit(PlayingCards playingCards) {
//        boolean yes = InputView.askWantHitMore(name);
//
//        if (yes) {
//            state = state.drawCard(playingCards.nextCard());
////            OutputView.printInfo(nameAndCards().toString());
//            // todo: 컨트롤러에서 해당 내용 구현할 예정. Participants객체와 새로 생성한 메시지 클래스를 활용한다.
//        }
//
//        if (!yes) {
//            state = state.stay();
//        }
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
        return state.cards().getCards();
    }
}
