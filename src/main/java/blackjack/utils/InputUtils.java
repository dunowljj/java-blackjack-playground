package blackjack.utils;

import java.util.Optional;
import java.util.Scanner;

public class InputUtils {
    final static Scanner scanner = new Scanner(System.in);
    public static final String ERROR_INPUT_FORMAT_YES_OR_NO = "y 또는 n으로 입력해주세요. (예는 y, 아니오는 n)";
    public static final String ERROR_INPUT_NULL_YES_OR_NO = "값이 입력되지 않았습니다.";

    public static String inputString() {
        return scanner.nextLine();
    }

    public static int inputInt() {
        return Integer.parseInt(scanner.nextLine());
    }

    public static boolean inputYesOrNo() {
        String input = scanner.nextLine();

        Optional.ofNullable(input).orElseThrow(() -> new IllegalArgumentException(ERROR_INPUT_NULL_YES_OR_NO));

        if (input.equals("y")) {
            return true;
        }

        if (input.equals("n")) {
            return false;
        }

        throw new IllegalArgumentException(ERROR_INPUT_FORMAT_YES_OR_NO);
    }
}
