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

        Participants participants = new Participants(InputView.inputPlayerNames(), playingCards);
        participants.inputBetMoney();

        OutputView.noticeStartDistribution(participants.playerNames());
        OutputView.printNameAndCards(participants.nameAndCards());

        InputView.askHitMore(participants, playingCards);


        /*// 이름, 금액 출력 확인
        for (Participant participant : participants.getParticipants()) {
            System.out.println(participant.getName());
            System.out.println(participant.getBetMoney());
        }*/
    }


    }
