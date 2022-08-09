package blackjack.model.state;

import blackjack.model.card.*;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class HitTest {

    @Test
    void 생성_카드일치_확인() {
        //given
        Cards cards = new Cards(new PlayingCards());

        //when
        State hit = new Hit(cards);

        //then
        assertThat(hit.cards()).isEqualTo(cards);

    }

    @Test
    void 시작_카드2장_받기() {
        //given, when
        State state = new Hit(new PlayingCards());
        Cards cards = state.cards();

        //then
        assertThat(cards.getCards().size()).isEqualTo(2);
    }



    @Test
    void 카드추가_안함_Stay() {
        //given
        Cards cards = new Cards();

        //when
        State state = new Hit(cards);
        state = state.stay();
        //then
        assertThat(state.getClass()).isEqualTo(Stay.class);
    }

    @Test
    void 카드추가_Bust() {
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



    @Test
    void 큰_Ace점수_고르기() {
        //given
        Cards cards = new Cards();
        cards.add(new PlayingCard(Suit.HEART, Denomination.ACE));
        cards.add(new PlayingCard(Suit.HEART, Denomination.JACK));

        {
            //when
            State hit = new Hit(cards);

            //then
//        assertThat(hit.cards().).isEqualTo(Hit.class);
        }
    }


}
