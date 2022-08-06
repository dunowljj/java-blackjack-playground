package blackjack.model.person;

import blackjack.model.card.PlayingCards;

public class Dealer extends AbstractParticipant{

    public static final String NAME_OF_DEALER = "딜러";

    public Dealer(PlayingCards playingCards) {
        super(new Name(NAME_OF_DEALER), playingCards);
    }

    @Override
    public StringBuilder nameAndCards() {
        StringBuilder nameAndCard = new StringBuilder();

        nameAndCard.append(getName()).append(" 카드: ")
                .append(getState().cards().firstCard());

        return nameAndCard;
    }

    @Override
    public boolean isPlayer() {
        return false;
    }
}
