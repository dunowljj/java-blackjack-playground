package blackjack.model;

import blackjack.model.person.BetMoney;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class BetMoneyTest {

    @Test
    void 배팅금액_확인() {
        //given
        int money = 15000;

        //when
        BetMoney betMoney = new BetMoney(money);

        //then
        assertThat(money).isEqualTo(betMoney.getMoney());
    }

    @Test
    void 배팅금액_0입력() {
        //given
        int money = 0;

        //when, then
        assertThatThrownBy(() -> new BetMoney(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1원 이상 배팅해주세요.");
    }

    @Test
    void 배팅금액_음수입력() {
        //given
        int money = -1000;

        //when, then
        assertThatThrownBy(() -> new BetMoney(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1원 이상 배팅해주세요.");
    }
}
