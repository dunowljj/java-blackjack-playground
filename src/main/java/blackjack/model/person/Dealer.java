package blackjack.model.person;

import blackjack.model.card.Cards;
import blackjack.model.card.PlayingCards;

public class Dealer extends AbstractParticipant{

    public static final String NAME_OF_DEALER = "딜러";
    public static final int DEALER_SCORE_UNDER_BOUND = 16;

    public Dealer(PlayingCards playingCards) {
        super(new Name(NAME_OF_DEALER), playingCards);
    }

    public Dealer(Cards cards) {
        super(new Name(NAME_OF_DEALER), cards);
    }

    @Override
    public StringBuilder nameAndCards() {
        StringBuilder nameAndCard = new StringBuilder();

        nameAndCard.append(getName()).append(" 카드: ")
                .append(getState().cards().firstCard());

        return nameAndCard;
    }
    @Override
    public StringBuilder namesAndProfits() {
        StringBuilder namesAndRevenues = new StringBuilder();

        namesAndRevenues.append(getName())
                .append(": ")
                .append(getProfit().value())
                .append("\n");

        return namesAndRevenues;
    }

    @Override
    public boolean isPlayer() {
        return false;
    }

    @Override
    public int profit() {
        return (int) (getProfit().value());
    }

    @Override
    public void total(double sum) {
        setProfit((-1) * sum);
    }

    public boolean needMoreCard() {
       return getState().cards().sumOfScore() <= DEALER_SCORE_UNDER_BOUND;
    }

}
