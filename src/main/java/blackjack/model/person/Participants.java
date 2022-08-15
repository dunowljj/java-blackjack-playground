package blackjack.model.person;

import blackjack.model.card.Cards;
import blackjack.model.card.PlayingCard;
import blackjack.model.card.PlayingCards;
import blackjack.model.state.State;
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

        participants.stream()
                .filter(participant -> participant.isPlayer())
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

    public String namesAndProfits() {
        StringBuilder namesAndRevenues = new StringBuilder();
        participants.stream().
                forEach(participant -> namesAndRevenues.append(participant.namesAndProfits()));
        return namesAndRevenues.toString().trim();
    }

    public String allNamesAndCards() {
        StringBuilder namesAndCards = new StringBuilder();

        participants.stream()
                .forEach(participant -> namesAndCards.append(participant.allNamesAndCards()).append("\n"));

        namesAndCards.deleteCharAt(namesAndCards.lastIndexOf("\n"));

        return namesAndCards.toString();
    }

    public void inputBetMoney() {
        participants.stream()
                .filter(participant -> participant.isPlayer())
                .forEach(p -> p.bet(InputView.inputBetMoney(p.getName())));
    }
    public void total() {
        if (dealer().isBust()) {
            allWinExceptBust();
            totalProfit();
            return;
        }

        if (dealer().isBlackjack() && somePlayerHasBlackjack()) {
            makeBlackjackTie();
            totalProfit();
            return;
        }

        if (dealer().isBlackjack() && !somePlayerHasBlackjack()) {
            //don't do anything
            totalProfit();
            return;
        }

        if (!dealer().isBlackjack() && somePlayerHasBlackjack()) {
            //don't do anything
            totalProfit();
            return;
        }

        // 조기 종료 아닌 경우
        if (!dealer().isBlackjack() && !somePlayerHasBlackjack()) {
            findWinner();
            totalProfit();
            return;
        }

    }

    private void findWinner() {
        int max = findMax();

        participants.stream()
                .filter(participant -> participant.isWinner(max))
                .forEach(Participant::win);
    }

    private void makeBlackjackTie() {
        participants.stream()
                .filter(Participant::isPlayer)
                .filter(Participant::isBlackjack)
                .forEach(Participant::tie);
    }

    private void allWinExceptBust() {
        participants.stream()
                .filter(Participant::isPlayer)
                .filter(participant -> !participant.isBust())
                .forEach(Participant::win);
    }

    private int findMax() {
        return participants.stream()
                .filter(Participant::isStay)
                .map(Participant::getState).map(State::cards)
                .map(Cards::sumOfScore)
                .mapToInt(x -> x).reduce(Math::max).getAsInt();
    }

    private void totalProfit() {
        double sum = participants.stream()
                .filter(participant -> participant.isPlayer())
                .map(Participant::profit)
                .reduce(0, (x, y) -> x + y);

        dealer().earn(sum);
    }
    public boolean blackjackExist() {
        return participants.stream()
                .filter(Participant::isBlackjack)
                .findAny().isPresent();
    }

    public boolean somePlayerHasBlackjack() {
        return participants.stream()
                .filter(Participant::isPlayer)
                .filter(Participant::isBlackjack)
                .findAny().isPresent();
    }

    private Dealer dealer() {
        return (Dealer) participants.get(FIRST_INDEX);
    }

    public boolean dealerNeedDraw() {
        return dealer().needMoreCard();
    }

    public void dealerDrawCard(PlayingCard playingCard) {
        dealer().drawCard(playingCard);
    }

    public boolean isDealerBust() {
        return dealer().isBust();
    }
}
