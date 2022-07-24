package blackjack.model.card;

import blackjack.model.card.Card;
import blackjack.model.card.Cards;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

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
}
