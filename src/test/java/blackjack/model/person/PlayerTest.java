package blackjack.model.person;

import blackjack.model.card.*;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class PlayerTest {

    @Test
    void 이름_입력() {
        //given
        Name name = new Name("pobi");

        //when
        Player person = new Player(name, new BetMoney(100), new PlayingCards()); // todo: 생성자를 어떻게 정리할 것인가?

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
}
