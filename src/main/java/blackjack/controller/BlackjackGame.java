package blackjack.controller;


import blackjack.model.Persons;
import blackjack.view.InputView;

public class BlackjackGame {
    public void run() {
        Persons persons = new Persons(InputView.inputName());

        InputView.inputBetMoney(persons);

        System.out.println(persons.getPersons().get(1).getBetMoney());
        System.out.println(persons.getPersons().get(2).getBetMoney());
    }
}
