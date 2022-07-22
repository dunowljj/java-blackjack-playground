package blackjack.model;

import blackjack.model.person.Name;
import blackjack.model.person.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class PlayerTest {

    @Test
    void 입력_이름길이_6이하() {
        //given
        String input = "pobi1";

        //when
        Player player = new Player(input);

        //then
        assertThat(player.getName()).isEqualTo(new Name(input));


    }

    @Test
    void 입력_이름길이_6초과_예외() {
        //given
        String input = "pobidon";

        //when, then
        assertThatThrownBy(() -> new Player(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이름은 6자를 초과할 수 없습니다.");

    }

    @Test
    void 배팅금액_확인() {
        //given
        Player person = new Player("pobi");
        int actual = 10000;

        //when
        person.bet(actual);

        //then
        Assertions.assertThat(actual).isEqualTo(person.getBetMoney());
    }
}
