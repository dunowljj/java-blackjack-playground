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
    public StringBuilder nameAndCards() {
        StringBuilder nameAndCard = new StringBuilder();

        nameAndCard.append(getName()).append(": ")
                .append(getState().cards().firstCard());

        return nameAndCard;
    }

    /* todo: 고민해보기
        딜러와 플레이어의 수익을 구하고 출력하는 방식이 다르다.
        딜러는 총합을 수익으로 하기 때문에, 상위 클래스에서 수익값을 구해주면, 그것을 이용해야 한다.
        혹은 출력할 내용을 생성하는 메서드를 상위 메서드로 옮길 수도 있다.
    */
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
        return (getProfit().value());
    }

    public void earn(double playerSum) {
        setProfit((-1) * playerSum);
    }

    public boolean needMoreCard() {
       return getState().cards().sumOfScore() <= DEALER_SCORE_UNDER_BOUND;
    }
}
