package blackjack.model.person;

import blackjack.model.card.Cards;

import java.util.ArrayList;
import java.util.List;

public class Persons {
    private static final List<Person> persons;
    public static final int DEALER_INITIAL_OPENED_NUMS = 1;
    public static final int PLAYER_INITIAL_OPENED_NUMS = 2;
    public static final int NUM_OF_DEALER = 1;
    public static final String NAME_DELIMETER = ",";

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

    public void receiveCard(Cards cards) {
        persons.stream().forEach((p) -> p.recieveCard(cards));
    }

    public List<Person> getPersons() {
        return persons;
    }


    public String initialDealerCards() {
        StringBuilder sb = new StringBuilder();
        persons.stream().limit(NUM_OF_DEALER)
                .forEach((p) -> sb.append(p.getCurrentOwnCards(DEALER_INITIAL_OPENED_NUMS)));
        return sb.toString();
    }
    public String initialPlayerCards() {
        StringBuilder sb = new StringBuilder();
        persons.stream()
                .filter((p) -> !p.getName().toString().equals("Dealer"))
                .forEach((p) -> sb.append(p.getCurrentOwnCards(PLAYER_INITIAL_OPENED_NUMS)));
        return sb.toString();
    }

    public StringBuilder namesExceptDealer() {
        StringBuilder sb = new StringBuilder();
        persons.stream()
                .map(Person::getName).filter((name) -> name.toString().equals("Dealer"))
                .forEach((name) -> sb.append(name).append(", "));
        sb.deleteCharAt(sb.lastIndexOf(", "));
        return sb;
    }
}
