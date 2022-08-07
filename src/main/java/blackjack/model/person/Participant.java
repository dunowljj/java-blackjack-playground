package blackjack.model.person;

import blackjack.model.card.PlayingCards;
import blackjack.model.state.State;

public interface Participant {

    void bet(BetMoney money);

    StringBuilder allNameAndCards();

    State getState();

    Name getName();
    BetMoney getBetMoney();

    StringBuilder nameAndCards();

    boolean isPlayer();

    boolean isFinished();

    void askHitUntilNo(PlayingCards playingCards);
}
