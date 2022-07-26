package blackjack.model.person;

import blackjack.model.card.Cards;

public interface Person {
    
    Name getName();

    int getBetMoney();

    void bet(int money);

    Cards getMyCards();

    void receiveCard(Cards providedCards, int amout);
    
    StringBuilder getNameAndCards();

    StringBuilder getNameAndCards(int num);

    void askUntilNo(Cards providedCards);

    boolean wantReceive(String askReceiveMore);

    boolean needMoreCard();
}
