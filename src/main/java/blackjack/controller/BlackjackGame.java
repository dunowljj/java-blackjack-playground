package blackjack.controller;


import blackjack.model.Persons;
import blackjack.view.InputView;

public class BlackjackGame {
    public void run() {
        Persons persons = new Persons(InputView.inputName());
    }
}
