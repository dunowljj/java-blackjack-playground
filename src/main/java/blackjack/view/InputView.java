package blackjack.view;

import blackjack.model.person.Name;
import blackjack.model.person.Person;
import blackjack.model.person.Persons;
import blackjack.utils.InputUtils;



public class InputView {

    public static final String MESSAGE_INPUT_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    public static final String MESSAGE_REQUIRE_BET = "의 배팅 금액은?";
    private static final String NUM_OF_FIRST_DISTRIBUTION = "2";

    public static String inputName() {
        System.out.println(MESSAGE_INPUT_NAME);
        return InputUtils.getString();
    }

    protected static void inputBetMoneyTest(Persons persons, int input) {
        persons.getPersons().stream()
                .filter((p) -> !p.getName().equals(new Name("Dealer")))
                .forEach((person) -> person.bet(input));
    }
    public static void inputBetMoney(Persons persons) {
        persons.getPersons().stream()
                .filter((p) -> !p.getName().equals(new Name("Dealer")))
                .forEach((person) -> person.bet(askBetMoney(person)));
    }
    private static int askBetMoney(Person person) {
        System.out.println(person.getName()+ MESSAGE_REQUIRE_BET);
        return InputUtils.getInt();
    }

    public static void noticeFirstDitribution(Persons persons) {
        StringBuilder sb = new StringBuilder();
        sb.append("딜러와 ");

        sb = sb.append(persons.namesExceptDealer());

        sb.append("에게 ").append(NUM_OF_FIRST_DISTRIBUTION).append("장을 나누었습니다.").append("\n");

        System.out.print(sb);
    }
    public static void openInitialCards(Persons person) {
        openDealerCards(person);
        openPlayerCards(person);
    }
    public static void openDealerCards(Persons persons) {
        System.out.print(persons.initialDealerCards());
    }
    public static void openPlayerCards(Persons persons) {
        System.out.print(persons.initialPlayerCards());
    }
}
