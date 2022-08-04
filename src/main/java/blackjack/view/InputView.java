package blackjack.view;

import blackjack.utils.InputUtils;

public class InputView {

    public static final String MESSAGE_INPUT_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";

    public static String inputPlayerNames() {
        System.out.println(MESSAGE_INPUT_NAMES);
        return InputUtils.inputString();
    }

}
