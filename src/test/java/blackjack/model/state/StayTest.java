package blackjack.model.state;

import blackjack.model.card.Cards;
import blackjack.model.card.Denomination;
import blackjack.model.card.PlayingCard;
import blackjack.model.card.Suit;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class StayTest {

    @Test
    void 생성() {
        //given
        Cards cards = new Cards();
        cards.add(new PlayingCard(Suit.SPADE, Denomination.SIX));

        //when
        Stay stay = new Stay(cards);

        //then
        assertThat(stay.cards).isEqualTo(cards);
    }

    @Test
    void 생성_및_Ace선택() {
        //given
        int bigAce = 11;
        int sum = 6 + 4 + bigAce;
        Cards cards = new Cards();
        cards.add(new PlayingCard(Suit.SPADE, Denomination.SIX));
        cards.add(new PlayingCard(Suit.SPADE, Denomination.FOUR));
        cards.add(new PlayingCard(Suit.SPADE, Denomination.ACE));

        //when
        Stay stay = new Stay(cards);

        //then
        assertThat(stay.cards.sumOfScore()).isEqualTo(sum);
    }
}
