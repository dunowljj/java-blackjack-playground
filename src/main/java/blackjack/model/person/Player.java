package blackjack.model.person;

import blackjack.model.card.Cards;
import blackjack.model.card.PlayingCards;
import blackjack.model.state.State;

public class Player extends AbstractParticipant {

    public static final String MESSAGE_NAME_BETWEEN_CARDS = "카드: ";

    public Player(Name name, BetMoney betMoney, PlayingCards playingCards) {
        super(name , betMoney, playingCards);
    }

    public Player(Name name, PlayingCards playingCards) {
        super(name, playingCards);
    }
    public Player(Name name, Cards cards) {
        super(name, cards);
    }

    // todo: 테스트용 생성자들 정리
    public Player(Name name, State state) {
        super(name);
        setState(state);
    }

    @Override
    public boolean isPlayer() {
        return true;
    }

    @Override
    public State getState() {
        return super.getState();
    }

    @Override
    public int profit() {
        return (int) (getState().profit(getBetMoney().value()));
    }

}
