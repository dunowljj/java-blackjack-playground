package blackjack.model.person;

import blackjack.model.card.PlayingCards;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    void 플레이어_아닌지_확인(){
        //given
        PlayingCards playingCard = new PlayingCards();

        //when
        Dealer dealer = new Dealer(playingCard);

        //then
        Assertions.assertThat(dealer.isPlayer()).isFalse();
    }
}
