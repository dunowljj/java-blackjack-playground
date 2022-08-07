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

    @Test
    void Bust_인지_확인() {
        //given
        Cards cards = new Cards();

        //when
        for (int i = 0; i < 3; i++) {
            cards.add(new PlayingCard(Suit.HEART, Denomination.TEN));
        }

        //then
        assertThat(cards.isBust()).isTrue();
    }
    @Test
    void Bust_아닌지_확인() {
        //given
        Cards cards = new Cards();

        //when
        cards.add(new PlayingCard(Suit.HEART, Denomination.TEN));
        cards.add(new PlayingCard(Suit.CLOVER, Denomination.FIVE));
        cards.add(new PlayingCard(Suit.DIAMOND, Denomination.SIX));


        //then
        assertThat(cards.isBust()).isFalse();
    }
}
