package blackjack.view;

public class OutputView {

    public static final String MESSAGE_NOTICE_DEALER_DRAW = "딜러는 16이하라 한장의 카드를 더 받았습니다.";

    public static void noticeStartDistribution(String nameAndCards) {
        System.out.println("딜러와 "+nameAndCards+"에게 2장의 나누었습니다.");
    }

    public static void printNameAndCards(String nameAndCards) {
        System.out.println(nameAndCards);
    }

    public static void noticeDealerDrawCard() {
        System.out.println(MESSAGE_NOTICE_DEALER_DRAW);
    }
}
