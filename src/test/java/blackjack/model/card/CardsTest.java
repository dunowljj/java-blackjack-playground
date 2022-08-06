package blackjack.model.card;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class CardsTest {

    @Test
    void 개인_카드_초기생성_개수확인() {
        //given
        PlayingCards playingCards = new PlayingCards();

        //when
        Cards cards = new Cards(playingCards);

        //then
        assertThat(cards.getCards().size()).isEqualTo(2);
    }
}
