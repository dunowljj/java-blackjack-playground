package blackjack.model.person;

import blackjack.model.card.Cards;
import blackjack.model.state.State;

import java.util.ArrayList;
import java.util.List;

public class Participants {
    private final List<Participant> participants = new ArrayList<>();
    private Dealer dealer;
    private Players players;

    public Participants() {
    }

    public Participants(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;

        participants.addAll(players.list());
        participants.add(dealer);
    }

    public Participants(Dealer dealer, Player... player) {
        this.dealer = dealer;
        this.players = new Players(player);

        participants.add(dealer);
        participants.addAll(players.list());
    }

    public List<Participant> getParticipants() {
        return participants;
    }
    public void total() {
        if (dealer.isBust()) {
            setAllWinExceptBust();
            totalProfit();
            return;
        }

        if (dealer.isBlackjack() && somePlayerHasBlackjack()) {
            makeBlackjackPeopleTie();
            totalProfit();
            return;
        }

        if (dealer.isBlackjack() && !somePlayerHasBlackjack()) {
            //don't do anything
            totalProfit();
            return;
        }

        if (!dealer.isBlackjack() && somePlayerHasBlackjack()) {
            //don't do anything
            totalProfit();
            return;
        }

        // 조기 종료 아닌 경우
        if (!dealer.isBlackjack() && !somePlayerHasBlackjack()) {
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
        //todo : Stay가 아무도 없을떄 NoSuchElementException 발생.
    }

    private void totalProfit() {
        setAllPlayerProfit();

        double sum = participants.stream()
                .filter(participant -> participant.isPlayer())
                .map(Participant::getProfit).map(Profit::value)
                .reduce(0, (x, y) -> x + y);

        dealer.earn(sum);
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

}
