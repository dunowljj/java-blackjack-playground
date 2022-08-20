package blackjack.model.state;

import blackjack.model.card.*;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class HitTest {

    @Test
    void 생성() {
        //given
        Cards cards = new Cards(new PlayingCards());

        //when
        State hit = new Hit(cards);

        //then
        assertThat(hit.cards()).isEqualTo(cards);

    }

    @Test
    void 초기_생성_시_지급카드_2장() {
        //given, when
        State state = new Hit(new PlayingCards());
        Cards cards = state.cards();

        //then
        assertThat(cards.getCards().size()).isEqualTo(2);
    }

    @Test
    void 카드추가_거부_시_Stay() {
        //given
        Cards cards = new Cards();

        //when
        State state = new Hit(cards);
        state = state.stay();
        //then
        assertThat(state.getClass()).isEqualTo(Stay.class);
    }

    @Test
    void 카드추가해서_초과_시_Bust() {
        //given
        Cards cards = new Cards();
        cards.add(new PlayingCard(Suit.HEART, Denomination.TEN));
        cards.add(new PlayingCard(Suit.HEART, Denomination.TEN));

        //when
        State state = new Hit(cards);
        state = state.drawCard(new PlayingCard(Suit.DIAMOND, Denomination.TWO));

        //then
        assertThat(state.getClass()).isEqualTo(Bust.class);
    }
}
