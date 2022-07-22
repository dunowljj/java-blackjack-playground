package blackjack.model;

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
            Card card1 = new Card("Clover", i+"");
            Card card2 = new Card("Heart", i+"");
            Card card3 = new Card("Spade", i+"");


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
        Card card1 = new Card("Clover", "J");
        Card card2 = new Card("Heart", "K");
        Card card3 = new Card("Spade", "A");

        //then
        Assertions.assertThat(cards.getCards()).contains(card1, card2, card3);
    }
}
