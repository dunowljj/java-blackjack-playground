package blackjack.model.card;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class SuitTest {

    @Test
    void Suit값_주어진_문자와_일치하는지_확인() {
        //given
        String given = "클로버";

        //when
        Suit suit = Suit.CLOVER;

        //then
        assertThat(suit.getName()).isEqualTo(given);
    }
}
