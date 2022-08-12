package blackjack.model.person;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class ProfitTest {

    @Test
    void 생성() {
        //given
        double money = 11000.0;

        //when
        Profit profit = new Profit(money);

        //then
        assertThat(profit.value()).isEqualTo(money);
    }
}
