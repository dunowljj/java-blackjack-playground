package blackjack.model.person;

import blackjack.model.card.Card;
import blackjack.model.card.Cards;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AbstractPersonTest {

    @Test
    void 카드_한장_배급() {
        //given
        Cards cards = new Cards();
        cards.add(new Card("하트","9"));
        Person person = new Player("pobi");

        //when
        person.receiveCard(cards, 1);
        Cards myCards = person.getMyCards();
        //then
        Assertions.assertThat(myCards.getCards()).containsExactly(new Card("하트", "9"));
    }

    @Test
    void 딜러_16이하인지_체크() {
        //given
        Cards cards = new Cards();
        cards.add(new Card("하트","8"));
        cards.add(new Card("클로버","8"));
        Person dealer = new Dealer();

        //when
        dealer.receiveCard(cards, 2);

        //then
        Assertions.assertThat(dealer.needMoreCard()).isTrue();
    }
    @Test
    void 딜러_16이하인지_확인() {
        //given
        Person person = new Player("pobi");
        Cards myCards = person.getMyCards();
        //when
        myCards.add(new Card("하트","22"));

        //then
        assertThat(person.isOverLimit()).isTrue();
    }
}
