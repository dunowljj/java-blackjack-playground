package blackjack.controller;


import blackjack.model.card.Cards;
import blackjack.model.person.Persons;
import blackjack.view.InputView;

public class BlackjackGame {
    public void run() {
        Cards deck = new Cards();
        deck.setUpWholeCard();

        Persons persons = new Persons(InputView.inputName());
        InputView.inputBetMoney(persons);

        persons.receiveCard(deck);
        InputView.noticeFirstDitribution(persons);


        InputView.openInitialCards(persons);

    }
}
