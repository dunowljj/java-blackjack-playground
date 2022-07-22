package blackjack.model;

import blackjack.model.person.Name;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class NameTest {

    @Test
    void 입력_이름길이_6이하() {
        //given
        String actual = "pobi12";

        //when
        Name name = new Name(actual);

        //then
        assertThat(actual).isEqualTo(name.getName());
    }

    @Test
    void 입력_이름길이_6초과_예외() {
        //given
        String actual = "pobidon";

        //when, then
        assertThatThrownBy(() -> new Name(actual))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이름은 6자를 초과할 수 없습니다.");

    }
}

