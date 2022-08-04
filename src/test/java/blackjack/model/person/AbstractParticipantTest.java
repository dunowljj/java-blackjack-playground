package blackjack.model.person;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class AbstractParticipantTest {

    @Test
    void 이름_입력() {
        //given
        String input = "pobi";

        //when
        AbstractParticipant participant = new AbstractParticipant(input);

        //then
        Assertions.assertThat(participant.getName()).isEqualTo(new Name(input));
    }
}
