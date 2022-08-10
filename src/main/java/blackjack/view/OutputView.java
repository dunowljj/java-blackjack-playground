package blackjack.view;

public class OutputView {

    public static final String MESSAGE_NOTICE_DEALER_DRAW = "\n딜러는 16이하라 한장의 카드를 더 받았습니다.\n";
    public static final String MESSAGE_FINAL_REVENUE = "## 최종 수익";

    public static void noticeStartDistribution(String nameAndCards) {
        System.out.println("\n딜러와 "+nameAndCards+"에게 2장의 나누었습니다.");
    }

    public static void printInfo(String nameAndCards) {
        System.out.println(nameAndCards);
    }

    public static void noticeDealerDrawCard() {
        System.out.println(MESSAGE_NOTICE_DEALER_DRAW);
    }

    public static void printDeckAndResult(String nameAndCards) {
        System.out.println(nameAndCards);
    }

    public static void printRevenues() {
        System.out.println(MESSAGE_FINAL_REVENUE);
    }
}
