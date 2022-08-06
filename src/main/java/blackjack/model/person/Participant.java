package blackjack.model.person;

import blackjack.model.state.State;

public interface Participant {

    void bet(BetMoney money);

    StringBuilder allNameAndCards();

    State getState();

    Name getName();
    BetMoney getBetMoney();

    StringBuilder nameAndCards();

    boolean isPlayer();
}
