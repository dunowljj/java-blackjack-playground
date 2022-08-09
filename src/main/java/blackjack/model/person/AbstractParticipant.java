package blackjack.model.person;

import blackjack.model.card.Cards;
import blackjack.model.card.PlayingCard;
import blackjack.model.card.PlayingCards;
import blackjack.model.state.Blackjack;
import blackjack.model.state.Hit;
import blackjack.model.state.State;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public abstract class AbstractParticipant implements Participant{
    public static final String MESSAGE_NAME_BETWEEN_CARDS = "카드: ";
    private final Name name;
    private BetMoney betMoney;
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
    public StringBuilder allNameAndCards() {
        StringBuilder nameAndCard = new StringBuilder();

        nameAndCard.append(getName()).append(MESSAGE_NAME_BETWEEN_CARDS)
                .append(getState().cards().allCards());

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
            OutputView.printNameAndCards(nameAndCards().toString());
        }

        if (!yes) {
            state = state.stay();
        }
    }

    @Override
    public boolean isFinished() {
        return state.isFinished();
    }


    @Override
    public State getState() {
        return state;
    }
    @Override
    public Name getName() {
        return name;
    }

    @Override
    public BetMoney getBetMoney() {
        return betMoney;
    }
}
