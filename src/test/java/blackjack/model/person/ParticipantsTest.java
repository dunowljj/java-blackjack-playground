package blackjack.model.person;

import blackjack.model.card.PlayingCards;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class ParticipantsTest {

    @Test
    void 이름들_입력() {
        //given
        String input = "pobi,jason,toby";
        String[] names = input.split(",");

        //when
        Participants participants = new Participants(input, new PlayingCards());

        //then
        assertThat(participants.getParticipants())
                .map(Participant::getName).contains(new Name(names[0]), new Name(names[1]), new Name(names[2]));
    }

}
