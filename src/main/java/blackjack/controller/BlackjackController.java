package blackjack.controller;

import blackjack.model.card.PlayingCards;
import blackjack.model.message.*;
import blackjack.model.person.*;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.stream.Collectors;

public class BlackjackController {
    private final PlayingCards playingCards = new PlayingCards();

    public void run() {
        Names names = getNames();
        Dealer dealer = new Dealer(playingCards);
        Players players = bettingAndGetPlayer(names);
        Participants participants = new Participants(dealer, players);

        printInitialStatus(participants);

        OutputView.noticeDistribution(names);
        stayOrHit(dealer, players);

        participants.total();
        printStatus(participants);
        printResult(participants);
    }

    /*private void proceed(Dealer dealer, Players players, Participants participants) {
        if (gamers.isDealerBust()) {
            totalGameAndExit(gamers);

        if (participants.dealerNeedDraw()) {
            participants.dealerDrawCard(playingCards.nextCard());
        }

        if (participants.blackjackExist()) {
            totalGameAndExit(participants);
        }
    }*/

    private void totalGameAndExit(Participants participants) {
        participants.total();
        printStatus(participants);
        printResult(participants);
//        System.exit(0);
    }

    private Names getNames() {
        try {
            return Names.from(InputView.inputPlayerNames());
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
            return getNames();
        }
    }
    private Players bettingAndGetPlayer(Names names) {
        return new Players(names.value().stream()
                .map((name) -> new Player(name, getBetMoney(name), playingCards))
                .collect(Collectors.toList()));
    }

    private BetMoney getBetMoney(Name name) {
        try {
            return InputView.inputBetMoney(name);
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
            return getBetMoney(name);
        }
    }

    private void stayOrHit(Dealer dealer, Players players) {
        stayOrHit(dealer);
        stayOrHit(players); //todo: 캡슐화하려고 하면, 메시지객체나 출력객체에 의존해야한다.
    }

    private void stayOrHit(Dealer dealer) {
        if (dealer.needMoreCard()) {
            dealer.drawCard(playingCards.nextCard());
            OutputView.noticeDealerHitCard();
        }

    }

    private void stayOrHit(Players players) {
        for (Player player : players.list()) {
            stayOrHit(player);
        }
    }

    private void stayOrHit(Player player) {
        CardDecision decision = askDrawMore(player);

        if (decision == CardDecision.YES) {
            player.drawCard(playingCards.nextCard());
            printStatus(player);
            stayOrHit(player);
            return;
        }

        if (decision == CardDecision.NO) {
            player.stay();
            printStatus(player);
            return;
        }
    }

    private CardDecision askDrawMore(Player player) {
        try {
            return InputView.askAddCard(player);
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
            return askDrawMore(player);
        }
    }

    private void printInitialStatus(Participants participants) {
        String message = InitialStatusMessages.from(participants).getMessage();
        OutputView.printMessage(message);
    }

    private void printStatus(Participant participant) {
        String message = StatusMessage.from(participant).getMessage();
        OutputView.printMessage(message);
    }

    private void printStatus(Participants participants) {
        String message = StatusMessages.from(participants).getMessage();
        OutputView.printMessage(message);
    }

    private void printResult(Participants gamers) {
        String message = ProfitMessages.from(gamers).getMessage();
        OutputView.printMessage(message);
    }


}
