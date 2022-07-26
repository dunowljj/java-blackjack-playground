package blackjack.model.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardsTest {


    @Test
    void 카드덱_생성_숫자확인() {
        //given
        String input = "pobi,jason";
        Cards cards = new Cards();
        cards.setUpWholeCard();

        //when
        for (int i = 1; i <= 9; i++) {
            Card card1 = new Card("클로버", i+"");
            Card card2 = new Card("하트", i+"");
            Card card3 = new Card("스페이드", i+"");

            //then
            Assertions.assertThat(cards.getCards()).contains(card1, card2, card3);
        }
    }

    @Test
    void 카드덱_생성_알파벳확인() {
        //given
        String input = "pobi,jason";
        Cards cards = new Cards();
        cards.setUpWholeCard();

        //when
        Card card1 = new Card("클로버", "J");
        Card card2 = new Card("하트", "K");
        Card card3 = new Card("스페이드", "A");
        Card card4 = new Card("스페이드", "Q");

        //then
        Assertions.assertThat(cards.getCards()).contains(card1, card2, card3, card4);
    }

    @Test
    void 블랙잭인지_확인() {
        //given
        Cards cards = new Cards();
        cards.add(new Card("클로버","K"));
        cards.add(new Card("하트","A"));

        //when
        assertThat(cards.isBlackjack()).isTrue();
    }

    @Test
    void 최대합_넘었는지_확인() {
        //given
        Cards cards = new Cards();
        cards.add(new Card("클로버","K"));
        cards.add(new Card("하트","Q"));
        cards.add(new Card("하트","J"));

        //when
        assertThat(cards.isOverLimit()).isTrue();
    }

    @Test
    void 딜러_16이하인지_확인() {
        //given
        Cards cards = new Cards();

        //when
        cards.add(new Card("클로버","K"));
        cards.add(new Card("하트","6"));

        //then
        assertThat(cards.needMore()).isTrue();
    }

    @Test
    void Cards_size() {
        //given
        Cards cards = new Cards();

        //when
        cards.add(new Card("클로버","K"));
        cards.add(new Card("하트","6"));

        //then
        assertThat(cards.size()).isEqualTo(cards.getCards().size());
    }
}
