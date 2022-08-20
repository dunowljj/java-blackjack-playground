package blackjack.model.person;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class NameTest {

    @Test
    void 이름입력() {
        //given
        String input = "pobi";

        //when
        Name name = new Name(input);

        //then
        assertThat(name.value()).isEqualTo(input);
    }



    @Test
    void 이름길이_제한() {
        //given
        String input = "pobiIsHappy";

        //when, then
        assertThatThrownBy(() -> new Name(input)).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이름은 10자 이내만 입력 가능합니다.");
    }

    @Test
    void toString_테스트() {
        //given
        String given = "pobi";

        //when
        Name name = new Name(given);

        //then
        assertThat(name.toString()).isEqualTo(given);
    }
}
