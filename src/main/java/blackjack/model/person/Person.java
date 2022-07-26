package blackjack.model.person;

import blackjack.model.card.Cards;

public interface Person {
    
    Name getName();

    int getBetMoney();

    void bet(int money);

    Cards getMyCards();

    void askUntilNo(Cards providedCards);

    boolean wantReceive(String askReceiveMore);

    void receiveCard(Cards providedCards, int amout);

    boolean receiveCardIfNeed(Cards cards);

    StringBuilder getNameAndCards();

    StringBuilder getNameAndCards(int num);



    boolean needMoreCard();
    boolean isOverLimit();

}
