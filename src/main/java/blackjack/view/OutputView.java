package blackjack.view;

import blackjack.model.person.Names;

public class OutputView {
    public static final String MESSAGE_NOTICE_DEALER_HIT = "\n딜러는 16이하라 한장의 카드를 더 받았습니다.\n";

    public static void noticeDistribution(Names names) {
        System.out.println("\n딜러와 "+names.toString()+"에게 2장의 나누었습니다.");
    }
    public static void noticeDealerHitCard() {
        System.out.println(MESSAGE_NOTICE_DEALER_HIT);
    }
    public static void printMessage(String message) {
        System.out.println(message);
    }
}
