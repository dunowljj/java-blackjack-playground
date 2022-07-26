package blackjack.utils;

import java.util.Scanner;

public class InputUtils {
    static final Scanner scanner = new Scanner(System.in);
    public static final int JKQ_VALUE = 10;
    public static final int A_VALUE = 11;

    public static String getString() {
        return scanner.nextLine();
    }

    public static int getInt() {
        return Integer.parseInt(scanner.nextLine());
    }

    public static int getValue(String num) {
        char first = num.charAt(0);
        if ('0' <= first && first <= '9') {
            return Integer.parseInt(num);
        }
        if (first == 'J' || first == 'K' || first == 'Q') {
            return JKQ_VALUE;
        }

        if(first =='A') {
            return A_VALUE;
        }

        return 0;
    }
}
