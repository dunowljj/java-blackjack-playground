package blackjack.model.person;

import blackjack.model.card.Cards;
import blackjack.model.card.PlayingCards;
import blackjack.model.state.State;

public class Player extends AbstractParticipant {

    public static final String MESSAGE_NAME_BETWEEN_CARDS = "카드: ";

    public Player(Name name) {
        super(name);
    }

    public Player(Name name, PlayingCards playingCards) {
        super(name, playingCards);
    }
    public Player(Name name, Cards cards) {
        super(name, cards);
    }

    public Player(Name name, State state) {
        super(name);
        setState(state);
    }

    @Override
    public StringBuilder nameAndCards() {
        StringBuilder nameAndCard = new StringBuilder();

        nameAndCard.append(getName()).append(MESSAGE_NAME_BETWEEN_CARDS)
                .append(getState().cards().allCards());

        return nameAndCard;
    }

    @Override
    public StringBuilder namesAndProfits() {
        StringBuilder namesAndRevenues = new StringBuilder();

        namesAndRevenues.append(getName())
                .append(": ")
                .append(profit())
                .append("\n");

        return namesAndRevenues;
    }



    @Override
    public boolean isPlayer() {
        return true;
    }

    @Override
    public int profit() {
        return (int) (getState().profit(getBetMoney().value()));
    }

}
