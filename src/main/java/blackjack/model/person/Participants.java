package blackjack.model.person;

import blackjack.model.card.PlayingCards;
import blackjack.view.InputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Participants {
    public static final String INPUT_NAME_DELIMITER = ",";
    public static final String OUTPUT_NAME_DELIMITER = ", ";
    public static final int FIRST_INDEX = 0;
    private final List<Participant> participants = new ArrayList<>();

    public Participants() {
    }
    public Participants(String names, PlayingCards playingCards) {
        String[] bunchOfName = names.split(INPUT_NAME_DELIMITER);

        distributeInitialCards(playingCards, bunchOfName);
    }

    private void distributeInitialCards(PlayingCards playingCards, String[] bunchOfName) {
        participants.add(new Dealer(playingCards));
        Arrays.stream(bunchOfName)
                .forEach((name) -> participants.add(new Player(new Name(name), playingCards)));
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public String playerNames() {
        StringBuilder names = new StringBuilder();
        
        participants.stream().filter(participant -> participant.isPlayer())
                .map(Participant::getName).map(Name::value)
                .forEach((name) -> names.append(name).append(OUTPUT_NAME_DELIMITER));

        names.deleteCharAt(names.lastIndexOf(OUTPUT_NAME_DELIMITER));

        return names.toString();
    }

    public String namesAndCards() {
        StringBuilder namesAndCards = new StringBuilder();

        participants.stream()
                .forEach(participant -> namesAndCards.append(participant.nameAndCards()).append("\n"));

        namesAndCards.deleteCharAt(namesAndCards.lastIndexOf("\n"));

        return namesAndCards.toString();
    }

    public String allNamesAndCards() {
        StringBuilder namesAndCards = new StringBuilder();

        participants.stream()
                .forEach(participant -> namesAndCards.append(participant.allNamesAndCards()).append("\n"));

        namesAndCards.deleteCharAt(namesAndCards.lastIndexOf("\n"));

        return namesAndCards.toString();
    }

    public void inputBetMoney() {
        participants.stream().filter(participant -> participant.isPlayer())
                .forEach(p -> p.bet(InputView.inputBetMoney(p.getName())));
    }

    public boolean dealerNeedDraw() {
        return dealer().needMoreCard();
    }
    public void dealerDrawCard(PlayingCards playingCards) {
        dealer().drawCard(playingCards.nextCard());
    }
    private Dealer dealer() {
        return (Dealer) participants.get(FIRST_INDEX);
    }

}
