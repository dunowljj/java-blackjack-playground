package blackjack.model.message;

import blackjack.model.person.Participants;

import java.util.stream.Collectors;

public class StatusMessages {
    private final String message;

    public StatusMessages(String message) {
        this.message = message;
    }

    public static StatusMessages from(Participants participants) {
        String message = participants.getParticipants().stream()
                .map((participant) -> StatusMessage.from(participant).getMessage() +" - 결과: " + participant.sumOfScore())
                .collect(Collectors.joining("\n"));

        return new StatusMessages(message);
    }

    public String getMessage() {
        return message;
    }
}
