package blackjack.controller;

import blackjack.model.card.PlayingCards;
import blackjack.model.message.InitialStatusMessages;
import blackjack.model.message.StatusMessage;
import blackjack.model.message.StatusMessages;
import blackjack.model.person.Participant;
import blackjack.model.person.Participants;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackGame {
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
        Participants gamers = new Participants(InputView.inputPlayerNames(), playingCards);
        gamers.inputBetMoney();

        OutputView.noticeStartDistribution(gamers.playerNames());

        if (gamers.isDealerBust()) {
            totalGameAndExit(gamers);
        }

        printInitialStatus(gamers);

        InputView.askHitMore(gamers, playingCards);

        if (gamers.blackjackExist()) {
            totalGameAndExit(gamers);
        }

        if (gamers.dealerNeedDraw()) {
            gamers.dealerDrawCard(playingCards.nextCard());
            OutputView.noticeDealerDrawCard();
        }

        totalGameAndExit(gamers);
    }

    private static void totalGameAndExit(Participants gamers) {
        gamers.total();
        printStatus(gamers);
        OutputView.printProfits(gamers.namesAndProfits());
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


}
