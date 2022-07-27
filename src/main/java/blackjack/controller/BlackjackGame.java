package blackjack.controller;


import blackjack.model.card.Cards;
import blackjack.model.person.Persons;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackGame {

    public void run() {
        Cards deck = new Cards();
        deck.setUpWholeCard();

        Persons persons = new Persons(InputView.inputName());
        InputView.inputBetMoney(persons);

        persons.receiveInitialCards(deck);
        OutputView.noticeFirstDistribution(persons);
        OutputView.openInitialCards(persons);

        InputView.inputWantGetMore(persons, deck);

        OutputView.checkDealerCards(persons, deck);

        OutputView.openAllCards(persons);
    }
}
