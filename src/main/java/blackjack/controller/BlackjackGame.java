package blackjack.controller;

import blackjack.model.person.Participant;
import blackjack.model.person.Participants;
import blackjack.view.InputView;

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
        Participants participants = new Participants(InputView.inputPlayerNames());

        /*// 이름 출력 확인
        for (Participant participant : participants.getParticipants()) {
            System.out.println(participant.getName().value());
        }*/
    }
}
