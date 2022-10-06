package blackjack.model.person;

import blackjack.model.card.PlayingCard;
import blackjack.model.state.State;

import java.util.List;

public interface Participant {

    void drawCard(PlayingCard playingCard);

    int profit();

    boolean isBlackjack();

    boolean isBust();

    boolean isFinished();

    boolean isPlayer();

    State getState();

    void setState(State state);

    Name getName();

    BetMoney getBetMoney();

    Profit getProfit();

    void setProfit(double profit);

    void tie();

    void bust();

    void win();

    void stay();

    boolean isWinner(int max);

    boolean isStay();
    List<PlayingCard> getCards();

    int sumOfScore();
}

