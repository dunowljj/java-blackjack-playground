package blackjack.view;

import blackjack.utils.InputUtils;

public class InputView {

    public static final String INPUT_NAME_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";

    public static String inputName() {
        System.out.println(INPUT_NAME_MESSAGE);
        return InputUtils.getString();
    }
}
