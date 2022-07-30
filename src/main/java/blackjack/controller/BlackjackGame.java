package blackjack.controller;


import blackjack.model.card.CardPack;
import blackjack.model.person.Persons;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackGame {

    public void run() {
        CardPack deck = new CardPack();

        Persons persons = new Persons(InputView.inputName());
        InputView.inputBetMoney(persons);

        persons.receiveInitialCards(deck);
        OutputView.noticeFirstDistribution(persons);
        OutputView.openInitialCards(persons);

        persons.markBlackjack();

        if (persons.blackjackExist()) {
            persons.totalGame();
            OutputView.printRevenue(persons.revenueResult());
            System.exit(0);
        }

        InputView.askPlayerGetMore(persons, deck);

        OutputView.checkDealerCards(persons, deck);

        OutputView.openAllCards(persons);

        persons.markWinner();

        persons.totalGame();
        OutputView.printRevenue(persons.revenueResult());

    }
}
