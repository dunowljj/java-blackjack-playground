package blackjack.model.card;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    private final List<PlayingCard> cards;

    public Cards(PlayingCards playingCards) {
        this.cards = new ArrayList<>();
        cards.add(playingCards.drawNext());
        cards.add(playingCards.drawNext());
    }

    public List<PlayingCard> getCards() {
        return cards;
    }

    public String allCards() {
        StringBuilder allCards = new StringBuilder();

        cards.stream().forEach(playingCard -> allCards.append(playingCard.info()).append(", "));

        allCards.deleteCharAt(allCards.lastIndexOf(", "));

        return allCards.toString();
    }

    public String firstCard() {
        return cards.get(0).info();
    }
}
