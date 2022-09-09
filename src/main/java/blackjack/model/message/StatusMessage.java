package blackjack.model.message;

import blackjack.model.card.PlayingCard;
import blackjack.model.person.Participant;

import java.util.stream.Collectors;

public class StatusMessage {
    private final String message;

    public StatusMessage(String message) {
        this.message = message;
    }

    public static StatusMessage from(Participant participant) {
        String message = participant.getName() + "카드: " + makeCardsMessage(participant);
        return new StatusMessage(message);
    }

    public String getMessage() {
        return message;
    }

    public static String makeCardsMessage(Participant participant) {
        return participant.getCards().stream()
                .map(PlayingCard::toString)
                .collect(Collectors.joining(", " ));
    }
}
