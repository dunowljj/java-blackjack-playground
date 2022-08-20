package blackjack.model.card;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class PlayingCardTest {

    @Test
    void 게임카드_생성() {
        //given
        Suit suit = Suit.HEART;
        Denomination denomination = Denomination.ACE;

        //when
        PlayingCard playingCard = new PlayingCard(suit, denomination);

        //then
        assertThat(playingCard.getDenomination()).isEqualTo(denomination);
        assertThat(playingCard.getSuit()).isEqualTo(suit);
    }

    @Test
    void 게임카드_정보() {
        //given
        String given = "10하트";

        //when
        PlayingCard playingCard = new PlayingCard(Suit.HEART, Denomination.TEN);

        //then
        assertThat(playingCard.info()).isEqualTo(given);
    }
}
