package blackjack.utils;

import blackjack.model.card.Card;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class InputUtilsTest {

    @ParameterizedTest
    @CsvSource(value = {"Q:10", "K:10", "J:10", "A:11", "10:10"},
            delimiter = ':')
    void 카드_숫자값_변환(String input, String expected) {

        int value = InputUtils.getValue(input);
        // when
        int result = Integer.parseInt(expected);
        // then
        Assertions.assertThat(value).isEqualTo(result);
    }
}
