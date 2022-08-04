package blackjack.model.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class PlayingCardsTest {
    static PlayingCards playingCards;

    @BeforeAll
    static void setUp() {
        playingCards = new PlayingCards();
    }

    @Test
    void 카드_구성결과_확인() {
        for (Denomination denomination : Denomination.values()) {
            for (Suit suit : Suit.values()) {
                PlayingCard playingCard = new PlayingCard(suit, denomination);
                Assertions.assertThat(playingCards.getPlayingCards()).contains(playingCard);
            }
        }
    }
}
