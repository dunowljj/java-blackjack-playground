package blackjack.model.person;

import blackjack.model.card.PlayingCards;
import blackjack.model.state.Running;
import blackjack.model.state.State;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class AbstractParticipantTest {

    @Test
    void 이름_입력() {
        //given
        Name name = new Name("pobi");

        //when
        AbstractParticipant participant = new Player(name);

        //then
        assertThat(participant.getName()).isEqualTo(name);
    }

    @Test
    void 배팅금액_입력() {
        //given
        Name inputName = new Name("pobi");
        BetMoney inputBetMoney = new BetMoney(10000);
        AbstractParticipant participant = new Player(inputName);

        //when
        participant.bet(inputBetMoney);
        BetMoney betMoney = participant.getBetMoney();

        //then
        assertThat(betMoney).isEqualTo(inputBetMoney);
    }

    @Test
    void 초기_상태_확인() {
        //given
        AbstractParticipant participant = new Player(new Name("pobi"), new PlayingCards());

        //when
        State state = participant.getState();

        //then
        assertThat(state.getClass()).isEqualTo(new Running(new PlayingCards()).getClass());
    }
}
