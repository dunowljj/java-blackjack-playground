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

        //Todo : 게임시스템? 팩토리 사용해서 의도 나타내기?
        Participants gamers = new Participants(InputView.inputPlayerNames(), playingCards);
        gamers.inputBetMoney();

        OutputView.noticeStartDistribution(gamers.playerNames());
        OutputView.printNameAndCards(gamers.nameAndCards());

        InputView.askHitMore(gamers, playingCards);

        if (gamers.dealerNeedDraw()) {
            gamers.dealerDrawCard(playingCards);
            OutputView.noticeDealerDrawCard();
        }

        /*// 이름, 금액 출력 확인
        for (Participant participant : participants.getParticipants()) {
            System.out.println(participant.getName());
            System.out.println(participant.getBetMoney());
        }*/
    }


    }
