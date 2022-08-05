package blackjack.model.person;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class AbstractParticipantTest {

    @Test
    void 이름_입력() {
        //given
        String input = "pobi";

        //when
        AbstractParticipant participant = new Player(input);

        //then
        assertThat(participant.getName()).isEqualTo(new Name(input));
    }

    @Test
    void 배팅금액_입력() {
        //given
        String inputName = "pobi";
        int inputMoney = 10000;
        AbstractParticipant participant = new Player(inputName);

        //when
        participant.bet(inputMoney);
        BetMoney betMoney = participant.getBetMoney();

        //then
        assertThat(betMoney.value()).isEqualTo(10000);
    }
}
