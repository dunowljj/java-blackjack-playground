package blackjack.model.card;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    public static final int CARDS_FIRST_INDEX = 0;
    public static final int SCORE_UPPER_LIMIT = 21;
    public static final int GAP_BETWEEN_ACE_SCORES = 10;
    public static final String CARD_INFO_DELIMITER = ", ";
    private final List<PlayingCard> cards;

    public Cards() {
        cards = new ArrayList<>();
    }

    public Cards(PlayingCards playingCards) {
        this.cards = new ArrayList<>();
        cards.add(playingCards.nextCard());
        cards.add(playingCards.nextCard());
    }

    public String allCards() {
        StringBuilder allCards = new StringBuilder();

        cards.stream().forEach(playingCard -> allCards.append(playingCard.info()).append(CARD_INFO_DELIMITER));

        allCards.deleteCharAt(allCards.lastIndexOf(CARD_INFO_DELIMITER));

        return allCards.toString();
    }

    public String firstCard() {
        return cards.get(CARDS_FIRST_INDEX).info();
    }

    public void add(PlayingCard playingCard) {
        cards.add(playingCard);
    }

    public List<PlayingCard> getCards() {
        return cards;
    }

    public boolean isBust() {
        return sumOfScore() > SCORE_UPPER_LIMIT;
    }

    public Integer sumOfScore() {
        return cards.stream().map(PlayingCard::getDenomination)
                .map(Denomination::getScore)
                .reduce(0, (x, y) -> x + y);
    }

    public boolean isBlackjack() {
        if (aceExist()) {
            return cards.stream().map(PlayingCard::getDenomination)
                    .filter(denomination -> !denomination.isAce())
                    .anyMatch(denomination -> denomination.isAlphabet());
        }
        return false;
    }

    public void checkChangeAce() {
        if (sizeUpAvailable() && aceExist()) {
            cards.stream().filter(PlayingCard::isAce)
                    .findFirst().get()
                    .changeToBigAce();
        }
    }
    //todo: Optinal 공식문서 다시 읽고 고치기.
    private boolean aceExist() {
        return cards.stream().filter(PlayingCard::isAce).findFirst().isPresent();
    }

    private boolean sizeUpAvailable() {
        return sumOfScore() + GAP_BETWEEN_ACE_SCORES <= SCORE_UPPER_LIMIT;
    }
}
