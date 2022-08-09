package blackjack.model.person;

import blackjack.model.card.*;
import blackjack.model.state.Hit;
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
        assertThat(state.getClass()).isEqualTo(Hit.class);
    }

    @Test
    void 시작_카드2장_블랙잭인_경우() {
        //given, when
        Cards cards = new Cards();
        cards.add(new PlayingCard(Suit.CLOVER, Denomination.ACE));
        cards.add(new PlayingCard(Suit.CLOVER, Denomination.KING));

        //when
        Participant participant = new Player(new Name("pobi"), cards);

        //then
        assertThat(participant.isFinished()).isTrue();
    }

    @Test
    void isFinished_합0() {
        //given
        Cards cards = new Cards();
        AbstractParticipant participant = new Player(new Name("pobi"), cards);

        //when
        assertThat(participant.isFinished()).isFalse();
    }

    @Test
    void isFinished_합22() {
        //given
        Cards cards = new Cards();
        cards.add(new PlayingCard(Suit.HEART, Denomination.JACK));
        cards.add(new PlayingCard(Suit.HEART, Denomination.NINE));
        cards.add(new PlayingCard(Suit.HEART, Denomination.TWO));

        //when
        AbstractParticipant participant = new Player(new Name("pobi"), cards);

        participant.drawCard(new PlayingCard(Suit.HEART, Denomination.TWO));

        //then
        assertThat(participant.isFinished()).isTrue();

    }
}

