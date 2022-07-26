package blackjack.model.person;

import blackjack.model.card.Cards;

import java.util.ArrayList;
import java.util.List;

public class Persons {
    private static final List<Person> persons;
    public static final int DEALER_INITIAL_OPENED_NUMS = 1;
    public static final int NUM_OF_DEALER = 1;
    public static final String NAME_DELIMETER = ",";
    public static final String NAME_OF_DEALER = "딜러 ";

    static {
        persons = new ArrayList<>();
        persons.add(new Dealer());
    }

    public Persons(String input) {
        input = input.replace(" ", "");
        String[] inputs = input.split(NAME_DELIMETER);

        for (String name : inputs) {
            persons.add(new Player(name));
        }
    }

    public void receiveCard(Cards cards, int amount) {
        persons.stream().forEach((p) -> p.receiveCard(cards, amount));
    }

    public List<Person> getPersons() {
        return persons;
    }


    public String openInitialDealerCards() {
        StringBuilder sb = new StringBuilder();
        persons.stream().limit(NUM_OF_DEALER)
                .forEach((p) -> sb.append(p.getNameAndCards(DEALER_INITIAL_OPENED_NUMS)));
        return sb.toString();
    }
    public String openPlayerCards() {
        StringBuilder sb = new StringBuilder();
        persons.stream()
                .filter((p) -> !p.getName().toString().equals(NAME_OF_DEALER))
                .forEach((p) -> sb.append(p.getNameAndCards()));
        return sb.toString();
    }

    public StringBuilder namesExceptDealer() {
        StringBuilder sb = new StringBuilder();
        persons.stream()
                .map(Person::getName).filter((name) -> !name.toString().equals(NAME_OF_DEALER))
                .forEach((name) -> sb.append(name).append(", "));
        sb.deleteCharAt(sb.lastIndexOf(", "));
        return sb;
    }
}
