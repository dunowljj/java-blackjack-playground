package blackjack.model.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayingCards {
    private final List<PlayingCard> playingCards = new ArrayList<>();

    public List<PlayingCard> getPlayingCards() {
        setUpPlayingCards();
        return playingCards;
    }
    private void setUpPlayingCards() {
        addInitialCards();
        Collections.shuffle(playingCards);
    }
    private void addInitialCards() {
        for (Suit suit : Suit.values()) {
            addCardWith(suit);
        }
        /*Arrays.stream(Suit.values())
                .forEach((suit -> addCardWith(suit)));*/
    }
    private void addCardWith(Suit suit) {
        for (Denomination denomination : Denomination.values()) {
            playingCards.add(new PlayingCard(suit, denomination));
        }
       /* Arrays.stream(Denomination.values())
                .forEach((denomination -> playingCards.add(new PlayingCard(suit,denomination))));*/

    }
}