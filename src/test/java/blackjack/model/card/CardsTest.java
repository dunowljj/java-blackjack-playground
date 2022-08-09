package blackjack.model.card;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class CardsTest {

    Cards cards;

    @BeforeEach
    void setUp() {
        cards = new Cards();
    }


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
    void Bust인지_확인() {
        //given

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

        //when
        cards.add(new PlayingCard(Suit.HEART, Denomination.TEN));
        cards.add(new PlayingCard(Suit.CLOVER, Denomination.FIVE));
        cards.add(new PlayingCard(Suit.DIAMOND, Denomination.SIX));


        //then
        assertThat(cards.isBust()).isFalse();
    }

    @Test
    void Blackjack인지_확인() {
        //given

        //when
        cards.add(new PlayingCard(Suit.HEART, Denomination.ACE));
        cards.add(new PlayingCard(Suit.SPADE, Denomination.JACK));

        //then
        assertThat(cards.isBlackjack()).isTrue();
    }

    @Test
    void 큰_Ace_Score_선택() {
        //given
        int bigAce = 11;
        int sum = 6 + 4 + bigAce;
        cards.add(new PlayingCard(Suit.SPADE, Denomination.SIX));
        cards.add(new PlayingCard(Suit.SPADE, Denomination.FOUR));
        cards.add(new PlayingCard(Suit.SPADE, Denomination.ACE));

        //when
        cards.checkChangeAce();

        //then
        assertThat(cards.sumOfScore()).isEqualTo(sum);
    }

    @Test
    void 작은_Ace_Score_선택() {
        //given
        int smallAce = 1;
        int sum = 6 + 5 + smallAce;
        cards.add(new PlayingCard(Suit.HEART, Denomination.SIX));
        cards.add(new PlayingCard(Suit.SPADE, Denomination.FIVE));
        cards.add(new PlayingCard(Suit.SPADE, Denomination.ACE));
        for (PlayingCard card : cards.getCards()) {
            System.out.println(card);
        }
        //when
        cards.checkChangeAce();

        //then
        assertThat(cards.sumOfScore()).isEqualTo(sum);
    }

    @Test
    void 블랙잭인_경우() {
        //given
        Cards cards = new Cards();

        //when
        cards.add(new PlayingCard(Suit.HEART, Denomination.ACE));
        cards.add(new PlayingCard(Suit.HEART, Denomination.JACK));

        //then
        assertThat(cards.isBlackjack()).isTrue();

    }

    @Test
    void 블랙잭_아닌_경우() {
        //given
        Cards cards = new Cards();

        //when
        cards.add(new PlayingCard(Suit.HEART, Denomination.ACE));
        cards.add(new PlayingCard(Suit.HEART, Denomination.TEN));

        //then
        assertThat(cards.isBlackjack()).isFalse();

    }

    @AfterEach
    void clean() {
        cards.getCards().clear();
    }
}
