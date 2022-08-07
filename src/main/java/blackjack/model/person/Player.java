package blackjack.model.person;

import blackjack.model.card.PlayingCards;

public class Player extends AbstractParticipant {

    public static final String MESSAGE_NAME_BETWEEN_CARDS = "카드: ";

    public Player(Name name) {
        super(name);
    }

    public Player(Name name, PlayingCards playingCards) {
        super(name, playingCards);
    }
    @Override
    public StringBuilder nameAndCards() {
        StringBuilder nameAndCard = new StringBuilder();

        nameAndCard.append(getName()).append(MESSAGE_NAME_BETWEEN_CARDS)
                .append(getState().cards().allCards());

        return nameAndCard;
    }

    @Override
    public boolean isPlayer() {
        return true;
    }


}
