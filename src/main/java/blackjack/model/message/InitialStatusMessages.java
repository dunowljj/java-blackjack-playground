package blackjack.model.message;

import blackjack.model.person.Participants;

import java.util.stream.Collectors;

public class InitialStatusMessages {
    private final String message;

    public InitialStatusMessages(String messages) {
        this.message = messages;
    }

    public static InitialStatusMessages from(Participants participants) {
        String messages = participants.getParticipants().stream()
                .map(InitialStatusMessage::from).map(InitialStatusMessage::getMessage)
                .collect(Collectors.joining("\n"));

        return new InitialStatusMessages(messages.trim());
    }

    public String getMessage() {
        return message;
    }
}
