package blackjack.model.person;

import blackjack.model.card.*;
import blackjack.model.state.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
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
    void 카드추가_종료여부_합이_0일때() {
        //given
        Cards cards = new Cards();
        AbstractParticipant participant = new Player(new Name("pobi"), cards);

        //when
        assertThat(participant.isFinished()).isFalse();
    }

    @Test
    void 카드추가_종료여부_합이_22일때() {
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

    @Test
    void 이름과_모든_카드들과_결과_문자열_반환() {
        //given
        String message = "pobi카드: J하트, 9하트, 2하트 - 결과: 21";
        Cards cards = new Cards();
        cards.add(new PlayingCard(Suit.HEART, Denomination.JACK));
        cards.add(new PlayingCard(Suit.HEART, Denomination.NINE));
        cards.add(new PlayingCard(Suit.HEART, Denomination.TWO));
        AbstractParticipant participant = new Player(new Name("pobi"), cards);

        //when
        String info = participant.allNamesAndCards().toString();

        //then
        assertThat(info).isEqualTo(message);
    }


    @Nested
    class 상태 {
        Cards cards;
        AbstractParticipant participant;

        @BeforeEach
        void setUp() {
            cards = new Cards();
            participant = new Player(new Name("pobi"), cards);
        }

        @Test
        void bust인지_확인() {
            //when
            participant.drawCard(new PlayingCard(Suit.CLOVER, Denomination.JACK));
            participant.drawCard(new PlayingCard(Suit.CLOVER, Denomination.JACK));
            participant.drawCard(new PlayingCard(Suit.CLOVER, Denomination.JACK));

            //then
            assertThat(participant.isBust()).isTrue();
        }


        @Test
        void stay인지_확인() {
            //when
            participant.setState(participant.getState().stay());

            //then
            assertThat(participant.isStay()).isTrue();
        }

        @Test
        void tie로_설정() {
            //when
            participant = new Player(new Name("pobi"), cards);
            participant.tie();

            //then
            assertThat(participant.getState().getClass()).isEqualTo(Tie.class);
        }

        @Test
        void bust로_설정() {
            //when
            participant.bust();

            //then
            assertThat(participant.getState().getClass()).isEqualTo(Bust.class);
        }

        @Test
        void win으로_설정() {
            //when
            participant.win();

            //then
            assertThat(participant.getState().getClass()).isEqualTo(Win.class);
        }

    }
}

