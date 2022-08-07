package blackjack.model.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayingCards {
    private final List<PlayingCard> playingCards = new ArrayList<>();

    public PlayingCards() {
        setUpPlayingCards();
    }
    private void setUpPlayingCards() {
        addInitialCards();
        Collections.shuffle(playingCards);
    }
    private void addInitialCards() {
        for (Suit suit : Suit.values()) {
            addCardWith(suit);
        }
    }
    private void addCardWith(Suit suit) {
        for (Denomination denomination : Denomination.values()) {
            playingCards.add(new PlayingCard(suit, denomination));
        }
    }

    public PlayingCard nextCard() {
        return playingCards.remove(playingCards.size() - 1);
    }

    public List<PlayingCard> getPlayingCards() {
        return playingCards;
    }
}