package blackjack.model.person;

import java.util.ArrayList;
import java.util.List;

public class Persons {
    private static final List<Person> persons = new ArrayList<>();

    static {
        persons.add(new Dealer());
    }

    public Persons(String input) {
        input = input.replace(" ", "");
        String[] inputs = input.split(",");

        for (String name : inputs) {
            persons.add(new Player(name));
        }
    }


    public List<Person> getPersons() {
        return persons;
    }

}
