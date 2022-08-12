package blackjack.controller;

import blackjack.model.card.PlayingCards;
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

        //Todo : 게임시스템? 팩토리 사용해서 의도 나타내기? 애초에 배열에 담아서 보내면 로직이 이쁘게 정리되지 않나?
        Participants gamers = new Participants(InputView.inputPlayerNames(), playingCards);
        gamers.inputBetMoney();

        OutputView.noticeStartDistribution(gamers.playerNames());
        OutputView.printInfo(gamers.namesAndCards());

        InputView.askHitMore(gamers, playingCards);

        if (gamers.blackjackExist()) {
            gamers.total();
            OutputView.printProfits(gamers.namesAndProfits());
            System.exit(0);
        }

        if (gamers.dealerNeedDraw()) {
            gamers.dealerDrawCard(playingCards.nextCard());
            OutputView.noticeDealerDrawCard();
        }

        OutputView.printDeckAndResult(gamers.allNamesAndCards());

        // 결과 도출
        gamers.total();

        OutputView.printProfits(gamers.namesAndProfits());



        /*// 이름, 금액 출력 확인
        for (Participant participant : participants.getParticipants()) {
            System.out.println(participant.getName());
            System.out.println(participant.getBetMoney());
        }*/
    }


    }
