package blackjack.model.person;

import blackjack.model.card.PlayingCards;
import blackjack.model.state.Running;
import blackjack.model.state.State;

public abstract class AbstractParticipant implements Participant{
    public static final String MESSAGE_NAME_BETWEEN_CARDS = "카드: ";
    private final Name name;
    private BetMoney betMoney;
    private State state;

    protected AbstractParticipant(Name name) {
        this.name = name;
    }

    public AbstractParticipant(Name name, PlayingCards playingCards) {
        this.state = new Running(playingCards);
        this.name = name;
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
