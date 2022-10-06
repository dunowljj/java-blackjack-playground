package blackjack.model.card;

import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

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
            addCardsWith(suit);
        }
    }
    private void addCardsWith(Suit suit) {
        for (Denomination denomination : Denomination.values()) {
            playingCards.add(new PlayingCard(suit, denomination));
        }
    }
    public PlayingCard nextCard() {
        try {
            return tryToDraw();
        } catch (NoSuchElementException e) {
            OutputView.printMessage(e.getMessage());
            setUpPlayingCards();
            return tryToDraw();
        }
    }

    private PlayingCard tryToDraw() {
        if (playingCards.isEmpty()) {
            throw new NoSuchElementException("카드가 모두 소진되었습니다. 새로운 카드뭉치를 가져옵니다.");
        }

        return playingCards.remove(playingCards.size() - 1);
    }

    public List<PlayingCard> getPlayingCards() {
        return playingCards;
    }
}