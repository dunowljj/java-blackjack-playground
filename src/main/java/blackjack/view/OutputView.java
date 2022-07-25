package blackjack.view;

import blackjack.model.card.Cards;
import blackjack.model.person.Name;
import blackjack.model.person.Person;
import blackjack.model.person.Persons;

public class OutputView {
    private static final String NUM_OF_FIRST_DISTRIBUTION = "2";
    public static final String NAME_OF_DEALER = "딜러 ";

    public static void noticeFirstDistribution(Persons persons) {
        StringBuilder sb = new StringBuilder();
        sb.append("딜러와 ");

        sb = sb.append(persons.namesExceptDealer());

        sb.append("에게 ").append(NUM_OF_FIRST_DISTRIBUTION).append("장을 나누었습니다.").append("\n");

        System.out.print(sb);
    }

    public static void openInitialCards(Persons person) {
        openInitialDealerCards(person);
        openPlayerCards(person);
    }
    private static void openInitialDealerCards(Persons persons) {
        System.out.print(persons.openInitialDealerCards());
    }
    //Todo : 플레이어 카드 가져오는 로직에 매개변수 num 필히 수정
    private static void openPlayerCards(Persons persons) {
        System.out.print(persons.openPlayerCards());
    }

    //Todo : 비즈니스 로직 이동
    public static void checkDealerCards(Persons persons, Cards cards) {
        Person dealer = persons.getPersons().stream()
                .filter((p) -> p.getName().equals(new Name(NAME_OF_DEALER)))
                .findFirst().get();

        if (dealer.needMoreCard()) {
            dealer.receiveCard(cards,1);
            System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.\n");
        }

    }

    public static void openAllCards(Persons persons) {
        persons.getPersons().stream()
                .forEach((p) -> System.out.print(p.getNameAndCards()));
        System.out.println();
    }
}
