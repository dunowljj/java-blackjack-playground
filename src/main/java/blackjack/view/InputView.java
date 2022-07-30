package blackjack.view;

import blackjack.model.card.CardPack;
import blackjack.model.person.Person;
import blackjack.model.person.Persons;
import blackjack.utils.InputUtils;


public class InputView {

    public static final String MESSAGE_INPUT_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    public static final String MESSAGE_REQUIRE_BET = "의 배팅 금액은?";
    public static final String MESSAGE_ASK_MORE_CARD = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";

    public static String inputName() {
        System.out.println(MESSAGE_INPUT_NAME);
        return InputUtils.getString();
    }

    protected static void inputBetMoneyTest(Persons persons, int input) {
        persons.getPersons().stream()
                .filter((p) -> !p.isDealer())
                .forEach((person) -> person.bet(input));
    }
    public static void inputBetMoney(Persons persons) {
        persons.getPersons().stream()
                .filter((p) -> !p.isDealer())
                .forEach((person) -> person.bet(askBetMoney(person)));
    }
    private static int askBetMoney(Person person) {
        System.out.println(person.getName()+ MESSAGE_REQUIRE_BET);
        return InputUtils.getInt();
    }

    public static void openCards(String message) {
        System.out.print(message);
    }

    public static void askPlayerGetMore(Persons persons, CardPack providedCard) {
        persons.getPersons().stream()
                .filter((person) -> !person.isDealer())
                .filter((person) -> !person.isOverLimitAceConsidered())
                .forEach((person) -> person.askUntilNo(providedCard));
    }

    public static String askReceiveMore(Person person) {
        System.out.println(person.getName()+ MESSAGE_ASK_MORE_CARD);
        return InputUtils.getString();
    }
}
