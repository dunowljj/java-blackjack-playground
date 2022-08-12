package blackjack.model.person;

import blackjack.model.card.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class ParticipantsTest {
    Participants participants;
    String bunchOfName;
    @BeforeEach
    void setUp() {
        bunchOfName = "pobi,jason,toby";
        participants = new Participants(bunchOfName, new PlayingCards());
    }

    @Test
    void 이름들_입력() {
        //given
        String[] names = bunchOfName.split(",");

        //when
        Participants participants = new Participants(bunchOfName, new PlayingCards());

        //then
        assertThat(participants.getParticipants())
                .map(Participant::getName).contains(new Name(names[0]), new Name(names[1]), new Name(names[2]));
    }

    @Test
    void 딜러_16이하_확인() {
        //given
        Participants noGamer = new Participants();

        Cards cards = new Cards();
        cards.add(new PlayingCard(Suit.DIAMOND, Denomination.NINE));
        cards.add(new PlayingCard(Suit.DIAMOND, Denomination.SEVEN));

        //when
        noGamer.getParticipants().add(new Dealer(cards));

        //then
        assertThat(noGamer.dealerNeedDraw()).isTrue();
    }

    @Test
    void 딜러_16초과_확인() {
        //given
        Participants noGamer = new Participants();

        Cards cards = new Cards();
        cards.add(new PlayingCard(Suit.DIAMOND, Denomination.SEVEN));
        cards.add(new PlayingCard(Suit.DIAMOND, Denomination.TEN));

        //when
        noGamer.getParticipants().add(new Dealer(cards));

        //then
        assertThat(noGamer.dealerNeedDraw()).isFalse();
    }

    @Test
    void 블랙잭_존재_확인() {
        //given
        Cards cards = new Cards();
        cards.add(new PlayingCard(Suit.DIAMOND, Denomination.ACE));
        cards.add(new PlayingCard(Suit.DIAMOND, Denomination.JACK));

        //when
        Participants gamers = new Participants();
        gamers.getParticipants().add(new Dealer(cards));


        //then
        assertThat(gamers.blackjackExist()).isTrue();
    }

   /* @Test
    void 딜러와_플레이어_공동블랙잭_확인() {
        ///given
        Cards cards = new Cards();
        cards.add(new PlayingCard(Suit.DIAMOND, Denomination.ACE));
        cards.add(new PlayingCard(Suit.DIAMOND, Denomination.JACK));

        //when
        Participants gamers = new Participants();
        gamers.getParticipants().add(new Dealer(cards));
        gamers.getParticipants().add(new Player(new Name("pobi"), cards));

        //then
        assertThat(gamers.dealerAndPlayerAreBlackjack()).isTrue();
    }*/
}
