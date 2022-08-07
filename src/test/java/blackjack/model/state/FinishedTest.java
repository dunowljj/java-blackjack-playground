package blackjack.model.state;

import blackjack.model.card.Cards;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class FinishedTest {

    @Test
    void 종료여부_확인() {
        //given, when
        Finished finished = new Finished(new Cards());

        //then
        Assertions.assertThat(finished.isFinished()).isTrue();
    }
}
