package blackjack.model.person;

import blackjack.model.card.*;
import blackjack.model.state.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ParticipantsTest {
    Participants participants;
    String bunchOfName;
    @BeforeEach
    void setUp() {
        bunchOfName = "pobi,jason,toby";
        participants = new Participants(bunchOfName, new PlayingCards());
    }

    @Nested
    class 이름들_입력받기 {

        @Test
        void 이름뭉치_입력받아_생성() {
            //given
            String[] names = bunchOfName.split(",");

            //when
            Participants participants = new Participants(bunchOfName, new PlayingCards());

            //then
            assertThat(participants.getParticipants())
                    .map(Participant::getName).contains(new Name(names[0]), new Name(names[1]), new Name(names[2]));
        }

        @Test
        void 이름뭉치_null입력시_예외() {
            assertThatThrownBy(() -> new Participants(null, new PlayingCards()))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("값이 입력되지 않았습니다.");
        }
    }

    @Nested
    class 딜러상태 {

        @Test
        void 딜러_16이하인지_확인() {
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
        void 딜러_16초과인지_확인() {
            //given
            Participants empty = new Participants();

            Cards cards = new Cards();
            cards.add(new PlayingCard(Suit.DIAMOND, Denomination.SEVEN));
            cards.add(new PlayingCard(Suit.DIAMOND, Denomination.TEN));

            //when
            empty.getParticipants().add(new Dealer(cards));

            //then
            assertThat(empty.dealerNeedDraw()).isFalse();
        }

        @Test
        void 딜러_Bust인지_확인() {
            //given
            Participants empty = new Participants();
            Dealer dealer = new Dealer(new Bust(new Cards()));

            //when
            empty.getParticipants().add(dealer);

            //then
            assertThat(empty.isDealerBust()).isTrue();
        }
    }

    @Nested
    class 참가자들_블랙잭의_존재 {
        @Test
        void 전체_블랙잭_존재하는지_확인() {
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

        @Test
        void 플레이어중에_블랙잭_존재하는지_확인() {
            ///given
            Cards cards = new Cards();
            cards.add(new PlayingCard(Suit.DIAMOND, Denomination.ACE));
            cards.add(new PlayingCard(Suit.DIAMOND, Denomination.JACK));

            //when
            Participants gamers = new Participants();
            gamers.getParticipants().add(new Dealer(new Cards()));
            gamers.getParticipants().add(new Player(new Name("pobi"), cards));


            //then
            assertThat(gamers.somePlayerHasBlackjack()).isTrue();
        }
    }

    @Nested
    class total {

    Participants participants;
    List<Participant> list;
    Dealer dealer;
    Player pobi;
    Player jason;
    Player toby;

    Name pobiName = new Name("pobi");
    Name jasonName = new Name("jason");
    Name tobyName = new Name("toby");

    int pobiMoney;
    int jasonMoney;
    int tobyMoney;

    /*
    total()메서드
     호출 시점 : 블랙잭 게임이 진행되고, 추가로 카드를 뽑을지 모두 결정한 상태에서 사용된다.
     -> 호출 시점을 고려해서 모든 참여자들의 상태를 total에 사용할 수 있도록 Stay로 초기화하고 시작한다. 각 테스트 메서드에서 필요에 따라
     Bust, Blackjack을 설정한다.

     기능 : 1) 사용 시점에 참여자들의 상태객체가 무엇인지에 따라 참여자들의 상태를 조정한다. 2) 그에 따라 수익을 계산한다.
     -> 두 가지 기능을 가지고 있는데, 확실한 테스트를 위해 상태가 잘 변경되었는지, 수익이 잘 계산되는지 두 가지 테스트를 했다.


     */
    @BeforeEach
    void setUp_전부_Stay로_설정해놓기() {
        participants = new Participants();
        list = participants.getParticipants();
        dealer = new Dealer(new Stay(new Cards()));
        pobi = new Player(pobiName, new Stay(new Cards()));
        jason = new Player(jasonName, new Stay(new Cards()));
        toby = new Player(tobyName, new Stay(new Cards()));

        pobiMoney = 10000;
        jasonMoney = 7000;
        tobyMoney = 12000;
    }

    private void 기본배팅_및_participants에_모두_넣기() {
        pobi.bet(new BetMoney(pobiMoney));
        jason.bet(new BetMoney(jasonMoney));
        toby.bet(new BetMoney(tobyMoney));

        list.add(dealer);
        list.add(pobi);
        list.add(jason);
        list.add(toby);
    }

    @Test
    void 딜러와_플레이어_둘다_Bust일때_집계된_상태확인() {
        //given
        dealer = new Dealer(new Bust(new Cards()));
        pobi = new Player(new Name("pobi"), new Bust(new Cards()));


        //when
        기본배팅_및_participants에_모두_넣기();
        participants.total();

        //then
        assertThat(dealer.getState().getClass()).isEqualTo(Bust.class);
        assertThat(pobi.getState().getClass()).isEqualTo(Bust.class);
        assertThat(jason.getState().getClass()).isEqualTo(Win.class);
        assertThat(toby.getState().getClass()).isEqualTo(Win.class);
    }

    @Test
    void 딜러와_플레이어_둘다_Bust일때_수익확인() {
        //given
        dealer = new Dealer(new Bust(new Cards()));
        pobi = new Player(new Name("pobi"), new Bust(new Cards()));
        기본배팅_및_participants에_모두_넣기();

        //when
        participants.total();

        pobiMoney *= -1.0;
        jasonMoney *= 1.0;
        tobyMoney *= 1.0;
        int sum = (int) -1 * (pobiMoney + jasonMoney + tobyMoney);

        //then
        assertThat(dealer.profit()).isEqualTo(sum);
        assertThat(pobi.profit()).isEqualTo(pobiMoney);
        assertThat(jason.profit()).isEqualTo(jasonMoney);
        assertThat(toby.profit()).isEqualTo(tobyMoney);
    }
    @Test
    void 딜러_플레이어_같이_블랙잭일때_상태확인() {
        //given
        dealer = new Dealer(new Blackjack(new Cards()));
        toby = new Player(tobyName, new Blackjack(new Cards()));
        기본배팅_및_participants에_모두_넣기();

        //when
        participants.total();

        //then
        assertThat(dealer.getState().getClass()).isEqualTo(Blackjack.class);
        assertThat(pobi.getState().getClass()).isEqualTo(Stay.class);
        assertThat(jason.getState().getClass()).isEqualTo(Stay.class);
        assertThat(toby.getState().getClass()).isEqualTo(Tie.class);
    }

    @Test
    void 딜러_플레이어_같이_블랙잭일때_수익확인() {
        //given
        dealer = new Dealer(new Blackjack(new Cards()));
        toby = new Player(new Name("toby"), new Blackjack(new Cards()));
        기본배팅_및_participants에_모두_넣기();

        //when
        participants.total();

        pobiMoney *= -1.0;
        jasonMoney *= -1.0;
        tobyMoney *= 0;
        int sum = (int) -1 * (pobiMoney + jasonMoney + tobyMoney);

        //then
        assertThat(dealer.profit()).isEqualTo(sum);
        assertThat(pobi.profit()).isEqualTo(pobiMoney);
        assertThat(jason.profit()).isEqualTo(jasonMoney);
        assertThat(toby.profit()).isEqualTo(tobyMoney);
    }

        @Test
        void 딜러_단독_블랙잭일때_상태확인() {
            //given
            dealer = new Dealer(new Blackjack(new Cards()));
            기본배팅_및_participants에_모두_넣기();

            //when
            participants.total();

            //then
            assertThat(dealer.getState().getClass()).isEqualTo(Blackjack.class);
            assertThat(pobi.getState().getClass()).isEqualTo(Stay.class);
            assertThat(jason.getState().getClass()).isEqualTo(Stay.class);
            assertThat(toby.getState().getClass()).isEqualTo(Stay.class);
        }

        @Test
        void 딜러_단독_블랙잭일때_수익확인() {
            //given
            dealer = new Dealer(new Blackjack(new Cards()));
            기본배팅_및_participants에_모두_넣기();

            //when
            participants.total();

            pobiMoney *= -1.0;
            jasonMoney *= -1.0;
            tobyMoney *= -1.0;
            int sum = (int) -1 * (pobiMoney + jasonMoney + tobyMoney);

            //then
            assertThat(dealer.profit()).isEqualTo(sum);
            assertThat(pobi.profit()).isEqualTo(pobiMoney);
            assertThat(jason.profit()).isEqualTo(jasonMoney);
            assertThat(toby.profit()).isEqualTo(tobyMoney);
        }

        @Test
        void 플레이어중에만_블랙잭_존재할_때_상태확인() {
            //given
            pobi = new Player(tobyName, new Blackjack(new Cards()));
            jason = new Player(jasonName, new Blackjack(new Cards()));
            기본배팅_및_participants에_모두_넣기();

            //when
            participants.total();

            //then
            assertThat(dealer.getState().getClass()).isEqualTo(Stay.class);
            assertThat(pobi.getState().getClass()).isEqualTo(Blackjack.class);
            assertThat(jason.getState().getClass()).isEqualTo(Blackjack.class);
            assertThat(toby.getState().getClass()).isEqualTo(Stay.class);
        }

        @Test
        void 플레이어중에만_블랙잭_존재할_때_수익확인() {
            //given
            pobi = new Player(tobyName, new Blackjack(new Cards()));
            jason = new Player(jasonName, new Blackjack(new Cards()));
            기본배팅_및_participants에_모두_넣기();

            //when
            participants.total();

            pobiMoney *= 1.5;
            jasonMoney *= 1.5;
            tobyMoney *= -1;
            int sum = (int) -1 * (pobiMoney + jasonMoney + tobyMoney);

            //then
            assertThat(dealer.profit()).isEqualTo(sum);
            assertThat(pobi.profit()).isEqualTo(pobiMoney);
            assertThat(jason.profit()).isEqualTo(jasonMoney);
            assertThat(toby.profit()).isEqualTo(tobyMoney);
        }

        @Test
        void 승자계산해서_딜러와_플레이어_공동우승인_경우_상태확인() {
            //given
            dealer = new Dealer(new Hit(new Cards()));
            pobi = new Player(pobiName, new Hit(new Cards()));
            jason = new Player(jasonName, new Hit(new Cards()));
            toby = new Player(tobyName, new Hit(new Cards()));

            dealer.drawCard(new PlayingCard(Suit.CLOVER, Denomination.TEN));
            pobi.drawCard(new PlayingCard(Suit.HEART, Denomination.NINE));
            jason.drawCard(new PlayingCard(Suit.DIAMOND, Denomination.JACK));
            toby.drawCard(new PlayingCard(Suit.DIAMOND, Denomination.NINE));

            dealer.stay();
            pobi.stay();
            jason.stay();
            toby.stay();

            기본배팅_및_participants에_모두_넣기();

            //when
            participants.total();

            //then
            assertThat(dealer.getState().getClass()).isEqualTo(Win.class);
            assertThat(pobi.getState().getClass()).isEqualTo(Stay.class);
            assertThat(jason.getState().getClass()).isEqualTo(Win.class);
            assertThat(toby.getState().getClass()).isEqualTo(Stay.class);
        }

        @Test
        void 승자계산해서_딜러와_플레이어_공동우승인_경우_수익확인() {
            //given
            dealer = new Dealer(new Hit(new Cards()));
            pobi = new Player(pobiName, new Hit(new Cards()));
            jason = new Player(jasonName, new Hit(new Cards()));
            toby = new Player(tobyName, new Hit(new Cards()));

            dealer.drawCard(new PlayingCard(Suit.CLOVER, Denomination.TEN));
            pobi.drawCard(new PlayingCard(Suit.HEART, Denomination.NINE));
            jason.drawCard(new PlayingCard(Suit.DIAMOND, Denomination.JACK));
            toby.drawCard(new PlayingCard(Suit.DIAMOND, Denomination.NINE));

            dealer.stay();
            pobi.stay();
            jason.stay();
            toby.stay();


            기본배팅_및_participants에_모두_넣기();

            //when
            participants.total();

            pobiMoney *= -1.0;
            jasonMoney *= 1.0;
            tobyMoney *= -1.0;
            int sum = (int) -1 * (pobiMoney + jasonMoney + tobyMoney);

            //then
            assertThat(dealer.profit()).isEqualTo(sum);
            assertThat(pobi.profit()).isEqualTo(pobiMoney);
            assertThat(jason.profit()).isEqualTo(jasonMoney);
            assertThat(toby.profit()).isEqualTo(tobyMoney);
        }
    }

    @Nested
    class 결과_메시지_생성 {
        Dealer dealer;
        Player pobi;
        Player jason;

        @BeforeEach
        void setUp() {
            dealer = new Dealer(new Cards());
            dealer.drawCard(new PlayingCard(Suit.DIAMOND, Denomination.THREE));
            dealer.drawCard(new PlayingCard(Suit.CLOVER, Denomination.NINE));
            dealer.drawCard(new PlayingCard(Suit.DIAMOND, Denomination.EIGHT));
            dealer.stay();

            pobi = new Player(new Name("pobi"), new Cards());
            pobi.drawCard(new PlayingCard(Suit.HEART, Denomination.TWO));
            pobi.drawCard(new PlayingCard(Suit.SPADE, Denomination.EIGHT));
            pobi.drawCard(new PlayingCard(Suit.CLOVER, Denomination.ACE));
            pobi.getState().cards().checkChangeAce();
            pobi.bet(new BetMoney(10000));
            pobi.stay();

            jason = new Player(new Name("jason"), new Cards());
            jason.drawCard(new PlayingCard(Suit.CLOVER, Denomination.SEVEN));
            jason.drawCard(new PlayingCard(Suit.SPADE, Denomination.KING));
            jason.stay();
            jason.bet(new BetMoney(20000));
        }
        private void addPlayers(Participants participants, Dealer dealer, Player pobi, Player jason) {
            participants.getParticipants().add(dealer);
            participants.getParticipants().add(pobi);
            participants.getParticipants().add(jason);
        }

        @Test
        void 플레이어_이름들_문자열_반환() {
            //given
            String message = "pobi, jason, toby ";

            //when
            Participants participants = new Participants("pobi,jason,toby", new PlayingCards());

            //then
            assertThat(participants.playerNames()).isEqualTo(message);

        }

        @Test
        void 모든_이름들과_카드들_초기_공개내용_문자열_반환() {
            //given
            String message = "딜러: 3다이아몬드\n" +
                    "pobi카드: 2하트, 8스페이드 \n" +
                    "jason카드: 7클로버, K스페이드 ";
            //when
            Participants participants = new Participants();
            addPlayers(participants, dealer, pobi, jason);
            pobi.getState().cards().getCards().remove(2);

            //then
            assertThat(participants.namesAndCards()).isEqualTo(message);
        }

        @Test
        void 모든_이름들과_카드들과_결과들_문자열_반환() {
            //given
            String message = "딜러카드: 3다이아몬드, 9클로버, 8다이아몬드 - 결과: 20\n" +
                    "pobi카드: 2하트, 8스페이드, A클로버 - 결과: 21\n" +
                    "jason카드: 7클로버, K스페이드 - 결과: 17";

            //when
            Participants participants = new Participants();
            addPlayers(participants, dealer, pobi, jason);

            //then
            assertThat(participants.allNamesAndCards()).isEqualTo(message);
        }

        @Test
        void 모든_이름들과_수익들_문자열_반환() {
            //given
            String message = "딜러: 10000\n" +
                    "pobi: 10000\n" +
                    "jason: -20000";

            //when
            Participants participants = new Participants();
            addPlayers(participants, dealer, pobi, jason);
            participants.total();

            //then
            assertThat(participants.namesAndProfits().toString()).isEqualTo(message);

        }

    }
}
