package blackjack.model.person;

import blackjack.model.card.CardPack;
import blackjack.model.card.Cards;
import blackjack.model.card.MyCards;

public interface Person {
    
    Name getName();

    int getBetMoney();

    void bet(int money);

    MyCards getMyCards();

    void askUntilNo(CardPack providedCards);

    boolean wantReceive(String askReceiveMore);

    void receiveCard(Cards providedCards, int amout);

    boolean receiveCardIfNeed(Cards cards);

    StringBuilder getNameAndCards();

    StringBuilder getNameAndCards(int num);



    boolean needMoreCard();
    boolean isOverLimit();

}
