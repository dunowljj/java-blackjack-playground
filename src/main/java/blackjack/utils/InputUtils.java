package blackjack.utils;

import java.util.Scanner;

public class InputUtils {
    final static Scanner scnner = new Scanner(System.in);

    public static String inputString() {
        return scnner.nextLine();
    }

    public static int inputInt() {
        return Integer.parseInt(scnner.nextLine());
    }
}
