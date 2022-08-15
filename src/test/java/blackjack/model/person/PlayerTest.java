package blackjack.model.person;

import blackjack.model.card.*;
import blackjack.model.state.Blackjack;
import blackjack.model.state.Bust;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class PlayerTest {

    @Test
    void 이름_입력() {
        //given
        Name name = new Name("pobi");

        //when
        Player person = new Player(name);

        //then
        assertThat(person.getName()).isEqualTo(name);
    }

    @Test
    void 플레이어인지_확인(){
        //given
        Name name = new Name("pobi");
        PlayingCards playingCard = new PlayingCards();

        //when
        Player dealer = new Player(name, playingCard);

        //then
        assertThat(dealer.isPlayer()).isTrue();
    }

    @Test
    void 이름과_카드들_문자열_반환() {
        //given
        String message = "pobi카드: 10하트, 7하트 ";
        Cards cards = new Cards();
        cards.add(new PlayingCard(Suit.HEART, Denomination.TEN));
        cards.add(new PlayingCard(Suit.HEART, Denomination.SEVEN));

        //when
        Player player = new Player(new Name("pobi"), cards);

        //then
        assertThat(player.nameAndCards().toString()).isEqualTo(message);
    }

    @Test
    void 이름과_수익() {
        //given
        String message = "pobi: -1000\n";
        Cards cards = new Cards();

        //when
        Player player = new Player(new Name("pobi"), new Bust(cards));
        player.bet(new BetMoney(1000));

        //then
        assertThat(player.namesAndProfits().toString()).isEqualTo(message);
    }

}
