package blackjack.model.card;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    public static final int CARDS_FIRST_INDEX = 0;
    public static final int SCORE_UPPER_LIMIT = 21;
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

        cards.stream().forEach(playingCard -> allCards.append(playingCard.info()).append(", "));

        allCards.deleteCharAt(allCards.lastIndexOf(", "));

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
        return cards.stream().map(PlayingCard::getDenomination)
                .map(Denomination::getScore)
                .reduce(0, (x,y) -> x + y) > SCORE_UPPER_LIMIT;
    }
}
