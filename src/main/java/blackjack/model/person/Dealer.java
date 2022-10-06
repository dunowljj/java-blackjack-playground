package blackjack.model.person;

import blackjack.model.card.Cards;
import blackjack.model.card.PlayingCards;
import blackjack.model.state.State;

public class Dealer extends AbstractParticipant{

    public static final String NAME_OF_DEALER = "딜러";
    public static final int DEALER_SCORE_UNDER_BOUND = 16;

    public Dealer(PlayingCards playingCards) {
        super(new Name(NAME_OF_DEALER), playingCards);
        if (!needMoreCard()) {
            setState(getState().stay());
        }
    }

    public Dealer(Cards cards) {
        super(new Name(NAME_OF_DEALER), cards);
    }

    public Dealer(State state) {
        super(new Name(NAME_OF_DEALER));
        setState(state);
    }


    @Override
    public boolean isPlayer() {
        return false;
    }


    @Override
    public int profit() {
        return (getProfit().value());
    }

    public void earn(double playerSum) {
        setProfit((-1) * playerSum);
    }

    public boolean needMoreCard() {
       return getState().cards().sumOfScore() <= DEALER_SCORE_UNDER_BOUND;
    }

    public void stayOrHit() {
    }
}
