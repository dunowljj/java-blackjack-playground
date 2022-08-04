package blackjack.model.person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Participants {
    private final List<Participant> participants = new ArrayList<>();

    public Participants(String names) {
        String[] bunchOfName = names.split(",");

        Arrays.stream(bunchOfName)
                        .forEach((name) -> participants.add(new Player(name)));
    }

    public List<Participant> getParticipants() {
        return participants;
    }
}
