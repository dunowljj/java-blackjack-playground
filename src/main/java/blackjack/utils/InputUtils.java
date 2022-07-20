package blackjack.utils;

import java.util.Scanner;

public class InputUtils {
    static final Scanner scanner = new Scanner(System.in);

    public static String getString() {
        return scanner.nextLine();
    }
}
