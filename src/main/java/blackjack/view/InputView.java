package blackjack.view;

import blackjack.model.card.Cards;
import blackjack.model.person.Name;
import blackjack.model.person.Person;
import blackjack.model.person.Persons;
import blackjack.utils.InputUtils;


//Todo : 테스트로직 점검, 커버리지 사용해보기
public class InputView {

    public static final String MESSAGE_INPUT_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    public static final String MESSAGE_REQUIRE_BET = "의 배팅 금액은?";
    public static final String MESSAGE_ASK_MORECARD = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    //Todo:enum
    public static final String NAME_OF_DEALER = "딜러 ";

    public static String inputName() {
        System.out.println(MESSAGE_INPUT_NAME);
        return InputUtils.getString();
    }

    //Todo : 비즈니스 로직 이동
    protected static void inputBetMoneyTest(Persons persons, int input) {
        persons.getPersons().stream()
                .filter((p) -> !p.getName().equals(new Name(NAME_OF_DEALER)))
                .forEach((person) -> person.bet(input));
    }
    public static void inputBetMoney(Persons persons) {
        persons.getPersons().stream()
                .filter((p) -> !p.getName().equals(new Name(NAME_OF_DEALER)))
                .forEach((person) -> person.bet(askBetMoney(person)));
    }
    private static int askBetMoney(Person person) {
        System.out.println(person.getName()+ MESSAGE_REQUIRE_BET);
        return InputUtils.getInt();
    }

    public static void openCards(String message) {
        System.out.print(message);
    }

    public static void askMoreInput(Persons persons, Cards providedCard) {
        persons.getPersons().stream()
                .filter((person) -> !person.getName().equals(new Name(NAME_OF_DEALER)))
                .filter((person) -> !person.getMyCards().isOverLimit())
                .forEach((person) -> person.askUntilNo(providedCard));
    }

    public static String askReceiveMore(Person person) {
        System.out.println(person.getName()+ MESSAGE_ASK_MORECARD);
        return InputUtils.getString();
    }


}
