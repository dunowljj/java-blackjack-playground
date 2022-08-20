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
    void 초기카드_모두_있는지_확인() {
        // given -> enum values
        for (Denomination denomination : Denomination.values()) {
            for (Suit suit : Suit.values()) {
                //when, then
                PlayingCard playingCard = new PlayingCard(suit, denomination);

                Assertions.assertThat(playingCards.getPlayingCards()).contains(playingCard);
            }
        }
    }
}
