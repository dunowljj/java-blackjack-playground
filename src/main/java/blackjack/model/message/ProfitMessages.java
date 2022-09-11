package blackjack.model.message;

import blackjack.model.person.Participants;

import java.util.stream.Collectors;

public class ProfitMessages {
    private final String message;

    public ProfitMessages(String message) {
        this.message = message;
    }

    public static ProfitMessages from(Participants participants) {
        String message = "## 최종 수익\n" +
                participants.getParticipants().stream()
                .map(ProfitMessage::from).map(ProfitMessage::getMessage)
                .collect(Collectors.joining("\n"));
        return new ProfitMessages(message.trim());
    }

    public String getMessage() {
        return message;
    }
}
