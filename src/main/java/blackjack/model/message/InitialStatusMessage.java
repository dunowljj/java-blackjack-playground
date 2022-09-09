package blackjack.model.message;

import blackjack.model.card.PlayingCard;
import blackjack.model.person.Dealer;
import blackjack.model.person.Participant;
import blackjack.model.person.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InitialStatusMessage {
    private static final Map<Class<? extends Participant>, Function<Participant, String>> messageMap = initMessageMap();
    private final String message;

    public InitialStatusMessage(String message) {
        this.message = message;
    }

    public static InitialStatusMessage from(Participant participant) {
        String message = messageMap.get(participant.getClass()).apply(participant);
        return new InitialStatusMessage(message);
    }

    private static Map initMessageMap() {
        Map<Class<? extends Participant>, Function<Participant, String>> messageMap = new HashMap<>();

        messageMap.put(Dealer.class, (dealer) -> dealer.getName() + "카드: " + makeOnlyOneCardMessage(dealer));
        messageMap.put(Player.class, (player) -> player.getName() + "카드: " + makeCardsMessage(player));

        return messageMap;
    }

    private static String makeOnlyOneCardMessage(Participant participant) {
        return participant.getCards().stream()
                .map(PlayingCard::toString).limit(1)
                .collect(Collectors.joining());
    }

    private static String makeCardsMessage(Participant participant) {
        return participant.getCards().stream()
                .map(PlayingCard::toString)
                .collect(Collectors.joining(", "));
    }

    public String getMessage() {
        return message;
    }
}
