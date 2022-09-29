package blackjack.controller;

import blackjack.model.card.PlayingCards;
import blackjack.model.message.*;
import blackjack.model.person.*;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.stream.Collectors;

public class BlackjackController {
    public static void run() {
        try {
            tryToRun();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            run();
        }
    }

    private static void tryToRun() {
        PlayingCards playingCards = new PlayingCards();

        // 초기 배분 2장을 한다는 내용이 드러나도록 메서드를 만드는게 나을까?
        Participants gamers = new Participants(getNames(), playingCards);

        Dealer dealer = new Dealer(playingCards);
        Players players = bettingAndGetPlayer(getNames());

//        gamers.inputBetMoney();


        OutputView.noticeStartDistribution(gamers.playerNames());

        if (gamers.isDealerBust()) {
            totalGameAndExit(gamers);
        }

        printInitialStatus(gamers);

//        stayOrHit(players, playingCards);
//        InputView.askHitMore(gamers, playingCards);

        if (gamers.blackjackExist()) {
            totalGameAndExit(gamers);
        }

        if (gamers.dealerNeedDraw()) {
            gamers.dealerDrawCard(playingCards.nextCard());
            OutputView.noticeDealerDrawCard();
        }

        totalGameAndExit(gamers);
    }

    private static void stayOrHit(Players players, PlayingCards playingCards) {
        for (Player player : players.list()) {
            stayOrHit(player, playingCards);
        }
    }

    private static void stayOrHit(Player player, PlayingCards playingCards) {
        CardDecision decision = askDrawMore(player);

        if (decision == CardDecision.YES) {
            player.drawCard(playingCards.nextCard());
            printStatus(player);
            return;
        }

        if (decision == CardDecision.NO) {
            player.stay();
            printStatus(player);
            return;
        }
    }

    private static CardDecision askDrawMore(Player player) {
        try {
            return InputView.askAddCard(player);
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
            return askDrawMore(player);
        }
    }

    private static Players bettingAndGetPlayer(Names names) {
        try {
            return new Players(names.value().stream()
                    .map((name) -> new Player(name, InputView.inputBetMoney(name)))
                    .collect(Collectors.toList()));
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
            return bettingAndGetPlayer(names);
        }
    }

    private static Names getNames() {
        try {
            return Names.from(InputView.inputPlayerNames());
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
            return getNames();
        }
    }
    // stream으로 Names기반으로 질문을 하고 받을수는 있다. 근데 예외처리는 어떻게 되는가?

    private static void totalGameAndExit(Participants gamers) {
        gamers.total();
        printStatus(gamers);
        printResult(gamers);
        System.exit(0);
    }

    private static void printInitialStatus(Participants participants) {
        String message = InitialStatusMessages.from(participants).getMessage();
        OutputView.printMessage(message);
    }

    private static void printStatus(Participant participant) {
        String message = StatusMessage.from(participant).getMessage();
        OutputView.printMessage(message);
    }
    private static void printStatus(Participants participants) {
        String message = StatusMessages.from(participants).getMessage();
        OutputView.printMessage(message);
    }

    private static void printResult(Participants gamers) {
        String message = ProfitMessages.from(gamers).getMessage();
        OutputView.printMessage(message);
    }


}
