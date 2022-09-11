package blackjack.model.message;

import blackjack.model.card.Cards;
import blackjack.model.person.Dealer;
import blackjack.model.person.Name;
import blackjack.model.person.Participants;
import blackjack.model.person.Player;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class ProfitMessagesTest {
    private static Participants participants = new Participants();

    @BeforeAll
    static void 수익_임의_설정() {
        Dealer dealer = new Dealer(new Cards());
        dealer.earn(-15000); // 플레이어 금액의 함계를 넣으면 음수로 전환해서 값을 저장

        Player player1 = new Player(new Name("player1"), new Cards());
        player1.setProfit(-10000);

        Player player2 = new Player(new Name("player2"), new Cards());
        player2.setProfit(-5000);

        participants.join(dealer);
        participants.join(player1);
        participants.join(player2);
    }

    @Test
    void 전체_수익메시지_생성() {
        //when
        String message = ProfitMessages.from(participants).getMessage();

        //then
        assertThat(message).isEqualTo("## 최종 수익\n" +
                "딜러: 15000\n" +
                "player1: -10000\n" +
                "player2: -5000");
    }
}
