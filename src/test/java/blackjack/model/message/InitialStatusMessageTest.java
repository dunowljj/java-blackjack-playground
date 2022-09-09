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


import static org.assertj.core.api.Assertions.*;


class InitialStatusMessageTest {
    Cards cards = new Cards();

    @DisplayName("기본 다이아몬드A, 다이아몬드10 제공")
    @BeforeEach
    void setTwoCards() {
        cards.add(new PlayingCard(Suit.DIAMOND, Denomination.ACE));
        cards.add(new PlayingCard(Suit.DIAMOND, Denomination.TEN));
    }

    @Test
    void 딜러_초기상태메시지_생성() {
        //given
        Participant participant = new Dealer(cards);

        //when
        InitialStatusMessage statusMessage = InitialStatusMessage.from(participant);
        String message = statusMessage.getMessage();

        //then
        assertThat(message).isEqualTo("딜러카드: A다이아몬드");
    }

    @Test
    void 플레이어_초기상태메시지_생성() {
        //given
        Participant participant = new Player(new Name("pobi"), cards);

        //when
        InitialStatusMessage statusMessage = InitialStatusMessage.from(participant);
        String message = statusMessage.getMessage();

        //then
        assertThat(message).isEqualTo("pobi카드: A다이아몬드, 10다이아몬드");
    }


}