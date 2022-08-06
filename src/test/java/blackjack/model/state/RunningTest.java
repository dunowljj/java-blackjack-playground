package blackjack.model.state;

import blackjack.model.card.Cards;
import blackjack.model.card.PlayingCards;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class RunningTest {

    @Test
    void 시작_카드2장() {
        //given

        //when
        State state = new Running(new PlayingCards());
        Cards cards = state.cards();

        //then
        Assertions.assertThat(cards.getCards().size()).isEqualTo(2);
    }
}
