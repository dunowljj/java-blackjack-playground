package blackjack.view;

import blackjack.model.card.CardPack;
import blackjack.model.person.Name;
import blackjack.model.person.Persons;



public class OutputView {
    private static final String NUM_OF_FIRST_DISTRIBUTION = "2";
    public static final String NAME_OF_DEALER = "딜러 ";

    public static void noticeFirstDistribution(Persons persons) {
        StringBuilder message = new StringBuilder();

        message.append("딜러와 ")
                .append(persons.namesExceptDealer().append("에게 ")
                .append(NUM_OF_FIRST_DISTRIBUTION).append("장을 나누었습니다.")
                .append("\n"));

        System.out.print(message);
    }

    public static void openInitialCards(Persons person) {
        openInitialDealerCards(person);
        openPlayerCards(person);
    }
    private static void openInitialDealerCards(Persons persons) {
        System.out.print(persons.openInitialDealerCards());
    }

    private static void openPlayerCards(Persons persons) {
        System.out.print(persons.openPlayerCards());
    }

    public static void checkDealerCards(Persons persons, CardPack cards) {
        boolean dealerGetMoreCard = persons.getPersons().stream()
                .filter((person) -> person.getName().equals(new Name(NAME_OF_DEALER)))
                .findFirst().get().receiveCardIfNeed(cards);

        if (dealerGetMoreCard) {
            noticeDealerGetMoreCard();
        }
    }
    private static void noticeDealerGetMoreCard() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void openAllCards(Persons persons) {
        persons.getPersons().stream()
                .forEach((p) -> System.out.print(p.getNameAndCards()));
        System.out.println();
    }
}
