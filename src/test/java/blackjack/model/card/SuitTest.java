package blackjack.model.card;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class SuitTest {

    @Test
    void suit값_확인() {
        //given
        String given = "클로버";

        //when
        Suit suit = Suit.CLOVER;

        //then
        assertThat(suit.getName()).isEqualTo(given);
    }
}
