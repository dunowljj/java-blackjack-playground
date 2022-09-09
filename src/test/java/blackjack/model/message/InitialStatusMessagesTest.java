package blackjack.model.message;

import blackjack.model.card.Cards;
import blackjack.model.card.Denomination;
import blackjack.model.card.PlayingCard;
import blackjack.model.card.Suit;
import blackjack.model.person.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class InitialStatusMessagesTest {

    static Dealer dealer;
    static Player player1;
    static Player player2;

    @DisplayName("딜러, 플레이어 2명 세팅")
    @BeforeAll
    static void setDealerAndTwoPlayer() {
        Cards cards1 = new Cards();
        cards1.add(new PlayingCard(Suit.DIAMOND, Denomination.ACE));
        cards1.add(new PlayingCard(Suit.DIAMOND, Denomination.TEN));

        dealer = new Dealer(cards1);

        Cards cards2 = new Cards();
        cards2.add(new PlayingCard(Suit.DIAMOND, Denomination.TWO));
        cards2.add(new PlayingCard(Suit.DIAMOND, Denomination.THREE));

        player1 = new Player(new Name("player1"), cards2);

        Cards cards3 = new Cards();
        cards3.add(new PlayingCard(Suit.DIAMOND, Denomination.JACK));
        cards3.add(new PlayingCard(Suit.DIAMOND, Denomination.QUEEN));

        player2 = new Player(new Name("player2"), cards3);
    }

    @Test
    void 전체_초기_상태메시지_생성() {
        //given
        Participants participants = new Participants();
        participants.join(dealer);
        participants.join(player1);
        participants.join(player2);

        //when
        String messages = InitialStatusMessages.from(participants).getMessage();

        //then
        assertThat(messages).isEqualTo(
                "딜러카드: A다이아몬드\n" +
                "player1카드: 2다이아몬드, 3다이아몬드\n" +
                "player2카드: J다이아몬드, Q다이아몬드");
    }

}