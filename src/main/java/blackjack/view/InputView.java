package blackjack.view;

import blackjack.model.card.PlayingCards;
import blackjack.model.person.BetMoney;
import blackjack.model.person.Name;
import blackjack.model.person.Participants;
import blackjack.utils.InputUtils;

public class InputView {

    public static final String MESSAGE_INPUT_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    public static final String MESSAGE_INPUT_BETMONEY = "의 배팅 금액은?";
    public static final String MESSAGE_INPUT_MORE_CARD = "는 한장의 카드를 더 받겠습니까?";

    public static String inputPlayerNames() {
        System.out.println(MESSAGE_INPUT_NAMES);
        return InputUtils.inputString();
    }

    public static BetMoney inputBetMoney(Name name) {
        System.out.println(name + MESSAGE_INPUT_BETMONEY);
        return new BetMoney(InputUtils.inputInt());
    }

    public static void askHitMore(Participants participants, PlayingCards playingCards) {
       participants.getParticipants().stream()
               .filter(p -> p.isPlayer())
               .filter(p -> !p.isFinished())
               .forEach(p -> p.askHitUntilNo(playingCards));
    }

    public static boolean askWantHitMore(Name name) {
        System.out.println(name + MESSAGE_INPUT_MORE_CARD);
        return InputUtils.inputYesOrNo();
    }
}
