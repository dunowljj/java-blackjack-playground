package blackjack.model.person;

import blackjack.model.person.Name;
import blackjack.model.person.Player;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class PlayerTest {

    @Test
    void 이름_입력() {
        //given
        String given = "pobi";

        //when
        Player person = new Player(given);

        //then
        assertThat(person.getName()).isEqualTo(new Name(given));
    }
}
