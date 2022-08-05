package blackjack.model.person;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class BetMoneyTest {

    @Test
    void 배팅금액_입력() {
        //given
        int givenMoney = 1000;

        //when
        BetMoney betMoney = new BetMoney(givenMoney);

        //then
        assertThat(betMoney.value()).isEqualTo(1000);
    }
    @Test
    void 배팅금액_입력값_음수일시_예외() {
        //given
        int givenMoney = -1;

        //when, then
        assertThatThrownBy(() -> new BetMoney(givenMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1원 이상 배팅하세요.");
    }
}
