package blackjack.model.person;

import blackjack.model.card.PlayingCard;
import blackjack.model.card.PlayingCards;
import blackjack.model.state.State;

public interface Participant {

    void drawCard(PlayingCard playingCard);

    void bet(BetMoney money);

    StringBuilder allNamesAndCards();

    StringBuilder nameAndCards();

    void askHitUntilNo(PlayingCards playingCards);

    int profit();

    boolean isBlackjack();

    boolean isFinished();

    boolean isPlayer();

    State getState();

    Name getName();

    BetMoney getBetMoney();

    StringBuilder namesAndProfits();

    Profit getProfit();

    void total(double money);

    void setProfit(double profit);
}

