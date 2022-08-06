package blackjack.view;

public class OutputView {

    public static void noticeStartDistribution(String nameAndCards) {
        System.out.println("딜러와 "+nameAndCards+"에게 2장의 나누었습니다.");
    }

    public static void printNameAndCards(String nameAndCards) {
        System.out.println(nameAndCards);
    }
}
