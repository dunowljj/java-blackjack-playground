package blackjack.model.card;

import blackjack.model.card.Denomination;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class DenominationTest {
    @Test
    void 숫자값_확인() {
        //given
        int given = 5;

        //when
        Denomination denomination = Denomination.FIVE;

        //then
        Assertions.assertThat(denomination.getScore()).isEqualTo(given);
    }
}
