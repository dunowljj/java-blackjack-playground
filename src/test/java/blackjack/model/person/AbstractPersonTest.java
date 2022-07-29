package blackjack.model.person;

import blackjack.model.card.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AbstractPersonTest {
    public static final double WIN_REWARD_PERCENTAGE = 1.0;
    public static final double BLACKJACK_REWARD_PERCENTAGE = 1.5;
    public static final double LOSE_REWARD_PERCENTAGE = -1.0;
    public static final int DRAW_REWARD = 0;
    Person pobi;
    MyCards pobiCards;
    Dealer dealer;

    @BeforeEach
    void setUp() {
        pobi = new Player("pobi");
        pobiCards = pobi.getMyCards();
        dealer = new Dealer();
    }

    @Nested
    class 카드배급받기 {

        @Test
        void 카드_한장_배급() {
            //given
            MyCards givenCards = new MyCards();
            givenCards.add(new Card("하트","9"));

            //when
            pobi.receiveCard(givenCards, 1);

            //then
            Assertions.assertThat(pobiCards.getCards()).containsExactly(new Card("하트", "9"));
        }

        @Test
        void 카드_두장_배급() {
            //given
            MyCards givenCards = new MyCards();
            givenCards.add(new Card("하트","9"));
            givenCards.add(new Card("하트","Q"));

            //when
            pobi.receiveCard(givenCards, 2);

            //then
            Assertions.assertThat(pobiCards.getCards())
                    .containsExactly(new Card("하트", "9"),new Card("하트", "Q"));
        }

    }

    @Nested
    class 카드합_확인 {

        @Test
        void 딜러_카드합_16이하인지_확인() {
            //given
            MyCards givenCards = new MyCards();
            givenCards.add(new Card("하트", "8"));
            givenCards.add(new Card("클로버", "8"));

            //when
            dealer.receiveCard(givenCards, 2);

            //then
            Assertions.assertThat(dealer.needMoreCard()).isTrue();
        }

        @Test
        void 카드합_21넘는지_확인() {
            //when
            pobiCards.add(new Card("하트", "22"));

            //then
            assertThat(pobi.isOverLimit()).isTrue();
        }
    }

    @Nested
    class 승자판정 {

        @Test
        void 플레이어_블랙잭_판정() {
            //given
            MyCards givenCards = new MyCards();
            givenCards.add(new Card("클로버", "K"));
            givenCards.add(new Card("클로버", "A"));

            //when
            pobi.receiveCard(givenCards, 2);

            pobi.markIfBlackjack();


            //then
            assertThat(pobi.getStatus()).isEqualTo(DeckStatus.BLACKJACK);
        }

        @Test
        void 플레이어_승자_판정() {
            //given
            MyCards givenCards = new MyCards();
            givenCards.add(new Card("클로버", "K"));
            givenCards.add(new Card("클로버", "9"));

            //when
            pobi.receiveCard(givenCards, 2);
            pobi.markIfMax(19);


            //then
            assertThat(pobi.getStatus()).isEqualTo(DeckStatus.WIN);
        }
    }

    @Nested
    class 수익계산 {

        @Test
        void 수익_게산하기_LOSE() {
            //given
            int money = 10000;
            int actual = (int) (LOSE_REWARD_PERCENTAGE * money);

            //when
            pobi.bet(money);
            pobi.calculateRevenue();

            //then
            assertThat(pobi.getRevenue()).isEqualTo(actual);
        }

        @Test
        void 수익_게산하기_DRAW() {
            //given
            int money = 10000;
            int actual = DRAW_REWARD;

            //when
            pobi.bet(money);
            pobi.setDrawer();
            pobi.calculateRevenue();

            //then
            assertThat(pobi.getRevenue()).isEqualTo(actual);
        }

        @Test
        void 수익_게산하기_BLCKJACK() {
            //given
            int money = 10000;
            int actual = (int) (BLACKJACK_REWARD_PERCENTAGE * money);

            //when
            pobi.bet(money);
            pobiCards.add(new Card("클로버", "21"));
            pobi.markIfBlackjack();
            pobi.calculateRevenue();

            //then
            assertThat(pobi.getRevenue()).isEqualTo(actual);
        }

        @Test
        void 수익_게산하기_WIN() {
            //given
            int money = 10000;
            int actual = (int) (WIN_REWARD_PERCENTAGE * money);

            //when
            pobi.bet(money);
            pobi.setWinner();
            pobi.calculateRevenue();

            //then
            assertThat(pobi.getRevenue()).isEqualTo(actual);
        }

    }

    @AfterEach
    void clean() {
        pobiCards.getCards().clear();
        dealer.getMyCards().getCards().clear();
    }
}
