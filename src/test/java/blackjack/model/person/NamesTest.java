package blackjack.model.person;

import blackjack.model.card.PlayingCards;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NamesTest {

    String bunchOfName;
    @BeforeEach
    void setUp() {
        bunchOfName = "pobi,jason,toby";
    }

    @Test
    void 이름들_입력받아_생성() {
        //given
        String[] names = bunchOfName.split(",");

        //when
        Participants participants = new Participants(Names.from(bunchOfName), new PlayingCards());

        //then
        assertThat(participants.getParticipants())
                .map(Participant::getName).contains(new Name(names[0]), new Name(names[1]), new Name(names[2]));
    }

    @Test
    void 이름들_null입력시_예외() {
        assertThatThrownBy(() -> new Participants(Names.from(null), new PlayingCards()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("값이 입력되지 않았습니다.");
    }

    @Test
    void 이름리스트_반환() {
        //given
        List<Name> nameList = Arrays.stream(bunchOfName.split(","))
                .map((name) -> new Name(name))
                .collect(Collectors.toList());

        //when
        Names names = Names.from(bunchOfName);

        //then
        assertThat(names.value()).containsAll(nameList);
    }
}
