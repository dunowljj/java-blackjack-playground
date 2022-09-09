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
    void 카드_초기생성_시_2개씩_배분되었는지_확인() {
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
    void Bust_아닌_경우_확인() {
        //given

        //when
        cards.add(new PlayingCard(Suit.HEART, Denomination.TEN));
        cards.add(new PlayingCard(Suit.CLOVER, Denomination.FIVE));
        cards.add(new PlayingCard(Suit.DIAMOND, Denomination.SIX));


        //then
        assertThat(cards.isBust()).isFalse();
    }

    @Test
    void 블랙잭인지_확인() {
        //given
        Cards cards = new Cards();

        //when
        cards.add(new PlayingCard(Suit.HEART, Denomination.ACE));
        cards.add(new PlayingCard(Suit.HEART, Denomination.JACK));

        //then
        assertThat(cards.isBlackjack()).isTrue();

    }

    @Test
    void 블랙잭_아닌_경우_확인() {
        //given
        Cards cards = new Cards();

        //when
        cards.add(new PlayingCard(Suit.HEART, Denomination.ACE));
        cards.add(new PlayingCard(Suit.HEART, Denomination.TEN));

        //then
        assertThat(cards.isBlackjack()).isFalse();

    }

    @Test
    void 카드추가_후_합이_21_이하이면_큰_Ace_Score_선택() {
        //given
        int bigAce = 11;
        int sum = 0;

        cards.add(new PlayingCard(Suit.SPADE, Denomination.SIX));
        sum += 6;

        cards.add(new PlayingCard(Suit.SPADE, Denomination.FOUR));
        sum += 4;

        cards.add(new PlayingCard(Suit.SPADE, Denomination.ACE));
        sum += bigAce;

        //when
        cards.checkChangeAce();

        //then
        assertThat(cards.sumOfScore()).isEqualTo(sum);
    }

    @Test
    void 카드추가_후_합이_21_이상이면_작은_Ace_Score_선택() {
        //given
        int smallAce = 1;
        int sum = 0;
        cards.add(new PlayingCard(Suit.HEART, Denomination.SIX));
        sum += 6;

        cards.add(new PlayingCard(Suit.SPADE, Denomination.FIVE));
        sum += 5;

        cards.add(new PlayingCard(Suit.SPADE, Denomination.ACE));
        sum += smallAce;

        //when
        cards.checkChangeAce();

        //then
        assertThat(cards.sumOfScore()).isEqualTo(sum);
    }

    @Test
    void 첫_카드만_문자열로_반환() {
        //given
        Cards cards = new Cards();
        PlayingCard playingCard = new PlayingCard(Suit.HEART, Denomination.ACE);
        //when
        cards.add(playingCard);
        cards.add(new PlayingCard(Suit.HEART, Denomination.TEN));

        //then
        assertThat(cards.firstCard()).isEqualTo(playingCard.toString());

    }

    @Test
    void 모든_카드들_문자열로_반환() {
        //given
        Cards cards = new Cards();
        PlayingCard playingCard1 = new PlayingCard(Suit.HEART, Denomination.ACE);
        PlayingCard playingCard2 = new PlayingCard(Suit.HEART, Denomination.TEN);
        String message = "A하트, 10하트 ";

        //when
        cards.add(playingCard1);
        cards.add(playingCard2);

        //then
        assertThat(cards.allCards()).isEqualTo(message);
    }

    @AfterEach
    void clean() {
        cards.getCards().clear();
    }
}
