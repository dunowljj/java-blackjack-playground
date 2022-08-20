package blackjack.model.person;

import blackjack.model.card.*;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class DealerTest {


    @Test
    void 딜러가_플레이어가_아님을_확인(){
        //given
        PlayingCards playingCard = new PlayingCards();

        //when
        Dealer dealer = new Dealer(playingCard);

        //then
        assertThat(dealer.isPlayer()).isFalse();
    }

    @Test
    void 카드합_16이하_확인() {
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
    void 카드합_16초과_확인() {
        //given
        Cards cards = new Cards();
        cards.add(new PlayingCard(Suit.HEART, Denomination.TEN));
        cards.add(new PlayingCard(Suit.HEART, Denomination.SEVEN));

        //when
        Dealer dealer = new Dealer(cards);

        //then
        assertThat(dealer.needMoreCard()).isFalse();
    }

    @Test
    void 수익_저장() {
        //given
        double total = -1.0;
        Dealer dealer = new Dealer(new Cards());

        //when
        dealer.earn(1.0);

        //then
        assertThat(dealer.getProfit().value()).isEqualTo((int)total);
    }

    @Test
    void 이름과_카드들_문자열_반환_최초_한장만_공개하는_경우() {
        //given
        String message = "딜러: 10하트";
        Cards cards = new Cards();
        cards.add(new PlayingCard(Suit.HEART, Denomination.TEN));
        cards.add(new PlayingCard(Suit.HEART, Denomination.SEVEN));

        //when
        Dealer dealer = new Dealer(cards);

        //then
        assertThat(dealer.nameAndCards().toString()).isEqualTo(message);
    }

    @Test
    void 이름과_수익_문자열_반환() {
        //given
        String message = "딜러: -1000\n";
        Cards cards = new Cards();
        int playerSum = 1000;

        //when
        Dealer dealer = new Dealer(cards);
        dealer.earn(playerSum);

        //then
        assertThat(dealer.namesAndProfits().toString()).isEqualTo(message);
    }


}
