package blackjack.model.message;

import blackjack.model.card.Cards;
import blackjack.model.card.Denomination;
import blackjack.model.card.PlayingCard;
import blackjack.model.card.Suit;
import blackjack.model.person.Dealer;
import blackjack.model.person.Name;
import blackjack.model.person.Participant;
import blackjack.model.person.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StatusMessageTest {
    Cards cards = new Cards();

    @DisplayName("기본 다이아몬드A, 다이아몬드10 제공")
    @BeforeEach
    void setTwoCards() {
        cards.add(new PlayingCard(Suit.DIAMOND, Denomination.ACE));
        cards.add(new PlayingCard(Suit.DIAMOND, Denomination.TEN));
    }

    @Test
    void 딜러_상태메시지_생성() {
        //given
        Participant dealer = new Dealer(cards);

        //when
        StatusMessage dealerMessage = StatusMessage.from(dealer);
        String message = dealerMessage.getMessage();

        //then
        assertThat(message).isEqualTo("딜러카드: A다이아몬드, 10다이아몬드");
    }

    @Test
    void 참가자_상태메시지_생성() {
        //given
        Participant player = new Player(new Name("pobi"), cards);

        //when
        StatusMessage playerMessage = StatusMessage.from(player);
        String message = playerMessage.getMessage();

        //then
        assertThat(message).isEqualTo("pobi카드: A다이아몬드, 10다이아몬드");
    }
}
