package blackjack.model.card;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class DenominationTest {
    @Test
    void 숫자값_확인() {
        //given
        int scoreOfFive = 5;

        //when
        Denomination denomination = Denomination.FIVE;

        //then
        assertThat(denomination.getScore()).isEqualTo(scoreOfFive);
    }

    @Test
    void Ace인지_확인() {
        //given
        Denomination denomination = Denomination.ACE;

        //when, then
        assertThat(denomination.isAce()).isTrue();
    }

    @Test
    void Ace_Score값_11로_변경() {
        //given
        int scoreOfBigAce = 11;
        Denomination denomination = Denomination.ACE;

        //when
        denomination.chooseBigAce();

        //then
        assertThat(denomination.getScore()).isEqualTo(scoreOfBigAce);
    }
}
