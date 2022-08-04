package blackjack.model.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayingCardTest {

    @Test
    void 생성_확인() {
        //given
        Suit suit = Suit.HEART;
        Denomination denomination = Denomination.ACE;

        //when
        PlayingCard playingCard = new PlayingCard(suit, denomination);

        //then
        Assertions.assertThat(playingCard.getDenomination()).isEqualTo(denomination);
        Assertions.assertThat(playingCard.getSuit()).isEqualTo(suit);
    }
}
