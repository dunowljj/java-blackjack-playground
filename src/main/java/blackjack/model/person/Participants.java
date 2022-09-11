package blackjack.model.person;

import blackjack.model.card.Cards;
import blackjack.model.card.PlayingCard;
import blackjack.model.card.PlayingCards;
import blackjack.model.state.State;
import blackjack.view.InputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Participants {
    public static final String ERROR_NULL_INPUT_NAMES = "값이 입력되지 않았습니다.";
    public static final String INPUT_NAME_DELIMITER = ",";
    public static final String OUTPUT_NAME_DELIMITER = ", ";
    public static final int FIRST_INDEX = 0;
    private final List<Participant> participants = new ArrayList<>();

    public Participants() {
    }
    public Participants(String names, PlayingCards playingCards) {
        Optional.ofNullable(names).orElseThrow(() -> new IllegalArgumentException(ERROR_NULL_INPUT_NAMES));

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

    public void inputBetMoney() {
        participants.stream()
                .filter(participant -> participant.isPlayer())
                .forEach(p -> p.bet(InputView.inputBetMoney(p.getName())));
    }

    // todo :집계 메서드 더 정리해보기
    public void total() {
        if (dealer().isBust()) {
            setAllWinExceptBust();
            totalProfit();
            return;
        }

        if (dealer().isBlackjack() && somePlayerHasBlackjack()) {
            makeBlackjackPeopleTie();
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
            setWinner();
            totalProfit();
            return;
        }

    }

    private void setWinner() {
        int max = findMax();

        participants.stream()
                .filter(participant -> participant.isWinner(max))
                .forEach(Participant::win);
    }

    private void makeBlackjackPeopleTie() {
        participants.stream()
                .filter(Participant::isPlayer)
                .filter(Participant::isBlackjack)
                .forEach(Participant::tie);
    }

    private void setAllWinExceptBust() {
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
        setAllPlayerProfit();

        double sum = participants.stream()
                .filter(participant -> participant.isPlayer())
                .map(Participant::getProfit).map(Profit::value)
                .reduce(0, (x, y) -> x + y);

        dealer().earn(sum);
    }

    private void setAllPlayerProfit() {
        participants.stream()
                .filter(participant -> participant.isPlayer())
                .forEach(participant -> participant.setProfit(participant.profit()));
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

    public void join(Participant participant) {
        getParticipants().add(participant);
    }
}
