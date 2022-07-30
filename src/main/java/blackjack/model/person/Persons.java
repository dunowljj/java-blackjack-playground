package blackjack.model.person;

import blackjack.model.card.Cards;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Persons {
    private static final List<Person> persons;
    public static final int NUM_OF_FIRST_DISTRIBUTION = 2;
    public static final int DEALER_INITIAL_OPENED_NUMS = 1;
    public static final int NUM_OF_DEALER = 1;
    public static final String NAME_DELIMETER = ",";
    public static final String NAME_OUTPUT_DELIMITER = ", ";

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

    public void receiveInitialCards(Cards cards) {
        persons.stream().forEach((p) -> p.receiveCard(cards, NUM_OF_FIRST_DISTRIBUTION));
    }

    public List<Person> getPersons() {
        return persons;
    }


    public String openInitialDealerCards() {
        StringBuilder sb = new StringBuilder();
        persons.stream().limit(NUM_OF_DEALER)
                .forEach((person) -> sb.append(person.getNameAndCards(DEALER_INITIAL_OPENED_NUMS)));
        return sb.toString();
    }
    public String openPlayerCards() {
        StringBuilder sb = new StringBuilder();
        persons.stream()
                .filter((person) -> !person.isDealer())
                .forEach((person) -> sb.append(person.getNameAndCards()));
        return sb.toString();
    }

    public StringBuilder namesExceptDealer() {
        StringBuilder sb = new StringBuilder();
        persons.stream()
                .filter((person) -> !person.isDealer())
                .map(Person::getName)
                .forEach((name) -> sb.append(name).append(NAME_OUTPUT_DELIMITER));
        sb.deleteCharAt(sb.lastIndexOf(NAME_OUTPUT_DELIMITER));
        return sb;
    }

    public void markBlackjack() {
        persons.stream()
                .forEach(person -> person.markIfBlackjack());
    }

    public void markWinner() {
        persons.stream()
                .forEach(person -> person.markIfMax(getMaxSumOfCards()));
    }
    private int getMaxSumOfCards() {
        return persons.stream()
                .map(person -> person.getDeckScore()).filter((num) -> num <= 21)
                .max(Comparator.naturalOrder()).get();
    }

    public boolean blackjackExist() {
        return persons.stream()
                .filter(person -> person.isBlackjack())
                .findAny().isPresent();
    }

    public void totalGame() {
        Dealer dealer = (Dealer) persons.get(0);

        if (dealer.isOverLimitAceConsidered()) {
            setAllPlayerWinner();
        }

        if (dealer.isBlackjack() || dealer.isWinner()) {
            setWinnerToDrawer();
        }

        persons.stream().filter(person -> !person.isDealer())
                .forEach(person -> person.calculateRevenue());

        int total = -1 * (persons.stream()
                .filter(person -> !person.isDealer())
                .map(person -> person.getRevenue())
                .reduce(0,(x,y) -> x + y));

        dealer.earn(total);
    }
    private void setAllPlayerWinner() {
        persons.stream().filter(person -> !person.isDealer()).
        forEach(person -> person.setWinner());
    }
    private void setWinnerToDrawer() {
        persons.stream().filter(person -> !person.isDealer())
            .filter(person -> person.isBlackjack() || person.isWinner())
            .forEach(person -> person.setDrawer());
    }

    public String revenueResult() {
        StringBuilder message = new StringBuilder();
        persons.stream().forEach(person -> message.append(person.info()).append("\n"));
        return message.toString();
    }
}
