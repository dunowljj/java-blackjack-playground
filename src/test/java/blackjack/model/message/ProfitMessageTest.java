package blackjack.model.message;

import blackjack.model.card.Cards;
import blackjack.model.person.Dealer;
import blackjack.model.person.Name;
import blackjack.model.person.Player;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class ProfitMessageTest {

    private static Dealer dealer;
    private static Player player1;

    @BeforeAll
    static void 수익_임의_설정() {
        dealer = new Dealer(new Cards());
        dealer.earn(-10000); // 플레이어 금액의 함계를 넣으면 음수로 전환해서 값을 저장

        player1 = new Player(new Name("player1"), new Cards());
        player1.setProfit(-10000);
    }

    @Test
    void 딜러_수익메시지_생성() {
        //when
        String message = ProfitMessage.from(dealer).getMessage();

        //then
        assertThat(message).isEqualTo("딜러: 10000");
    }

    @Test
    void 플레이어_수익메시지_생성() {
        //when
        String message = ProfitMessage.from(player1).getMessage();

        //then
        assertThat(message).isEqualTo("player1: -10000");
    }
}
