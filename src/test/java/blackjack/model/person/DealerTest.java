package blackjack.model.person;

import blackjack.model.card.*;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class DealerTest {


    @Test
    void 플레이어_아닌지_확인(){
        //given
        PlayingCards playingCard = new PlayingCards();

        //when
        Dealer dealer = new Dealer(playingCard);

        //then
        assertThat(dealer.isPlayer()).isFalse();
    }

    @Test
    void 딜러_16이하_확인() {
        //given
        Cards cards = new Cards();
        cards.add(new PlayingCard(Suit.HEART, Denomination.TEN));
        cards.add(new PlayingCard(Suit.HEART, Denomination.SIX));

        //when
        Dealer dealer = new Dealer(cards);

        //then
        assertThat(dealer.needMoreCard()).isTrue();
    }

    @Test
    void 딜러_16초과_확인() {
        //given
        Cards cards = new Cards();
        cards.add(new PlayingCard(Suit.HEART, Denomination.TEN));
        cards.add(new PlayingCard(Suit.HEART, Denomination.SEVEN));

        //when
        Dealer dealer = new Dealer(cards);

        //then
        assertThat(dealer.needMoreCard()).isFalse();
    }
}
