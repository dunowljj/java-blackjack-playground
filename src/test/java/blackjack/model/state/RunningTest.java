package blackjack.model.state;

import blackjack.model.card.Cards;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class RunningTest {
    @Test
    void 종료여부_확인() {
        //given, when
        Running running = new Running(new Cards());

        //then
        Assertions.assertThat(running.isFinished()).isFalse();
    }
}