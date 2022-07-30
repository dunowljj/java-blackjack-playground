package blackjack.model.person;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    void 딜러수익_받아오기(){

        //given
        Dealer dealer = new Dealer();
        int dealerIncome = 1000;

        //when
        dealer.earn(dealerIncome);

        //then
        Assertions.assertThat(dealer.getRevenue()).isEqualTo(dealerIncome);
    }
}
