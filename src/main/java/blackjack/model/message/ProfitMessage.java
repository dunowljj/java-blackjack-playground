package blackjack.model.message;

import blackjack.model.person.Participant;

public class ProfitMessage {

    private final String message;

    public ProfitMessage(String message) {
        this.message = message;
    }

    public static ProfitMessage from(Participant participant) {
        String message = participant.getName() + ": " + participant.getProfit();
        return new ProfitMessage(message);
    }


    public String getMessage() {
        return message;
    }
}
