package blackjack.controller;


import blackjack.model.card.Cards;
import blackjack.model.person.Persons;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackGame {
    //Todo: enum 분해
    static final int NUM_OF_FIRST_DISTRIBUTION = 2;

    public void run() {
        Cards deck = new Cards();
        deck.setUpWholeCard();

        Persons persons = new Persons(InputView.inputName());
        InputView.inputBetMoney(persons);

        persons.receiveCard(deck, NUM_OF_FIRST_DISTRIBUTION);
        OutputView.noticeFirstDistribution(persons);
        OutputView.openInitialCards(persons);

        InputView.askMoreInput(persons, deck);
        OutputView.checkDealerCards(persons, deck);

        OutputView.openAllCards(persons);
    }
}
