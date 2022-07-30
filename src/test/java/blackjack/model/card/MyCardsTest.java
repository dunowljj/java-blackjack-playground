package blackjack.model.card;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MyCardsTest {

    MyCards myCards;

    @BeforeEach
    void setUp() {
        myCards = new MyCards();
    }

    @Test
    void 블랙잭인지_확인() {
        //given
        myCards.add(new Card("클로버","K"));
        myCards.add(new Card("하트","A"));

        //when
        assertThat(myCards.isBlackjack()).isTrue();
    }

    @Test
    void 카드합_제한_초과_확인() {
        //given
        myCards.add(new Card("클로버","K"));
        myCards.add(new Card("하트","Q"));
        myCards.add(new Card("하트","J"));

        //when
        assertThat(myCards.isOverLimitAceConsidered()).isTrue();
    }

    @Test
    void 카드합_제한_이하_확인_A포함() {
        //given
        myCards.add(new Card("클로버","K"));
        myCards.add(new Card("하트","A"));
        myCards.add(new Card("하트","J"));

        //when
        assertThat(myCards.isOverLimitAceConsidered()).isFalse();
    }

    @Test
    void 카드합_제한_초과_확인_A포함() {
        //given
        myCards.add(new Card("클로버","K"));
        myCards.add(new Card("하트","A"));
        myCards.add(new Card("하트","J"));
        myCards.add(new Card("하트","1"));

        //when
        assertThat(myCards.isOverLimitAceConsidered()).isTrue();
    }

    @Test
    void 카드합_딜러_16이하인지_확인() {
        //when
        myCards.add(new Card("클로버","K"));
        myCards.add(new Card("하트","6"));

        //then
        assertThat(myCards.needMore()).isTrue();
    }

    @Test
    void 카드_수량() {
        //when
        myCards.add(new Card("클로버","K"));
        myCards.add(new Card("하트","6"));

        //then
        assertThat(myCards.size()).isEqualTo(myCards.getCards().size());
    }

    @Test
    void ACE_포함_확인() {
        //given
        myCards.add(new Card("클로버","K"));
        myCards.add(new Card("하트","A"));

        //when ,then
        assertThat(myCards.includeAce()).isTrue();
    }

    @Test
    void ACE_불포함_확인() {
        //given
        myCards.add(new Card("클로버","K"));
        myCards.add(new Card("하트","6"));

        //when ,then
        assertThat(myCards.includeAce()).isFalse();
    }

    @Test
    void ACE_개수_확인() {
        //given
        myCards.add(new Card("클로버","A"));
        myCards.add(new Card("하트","A"));

        //when, then
        assertThat(myCards.getNumOfAce()).isEqualTo(2);
    }

    @AfterEach
    void clean() {
        myCards.getCards().clear();
    }
}
