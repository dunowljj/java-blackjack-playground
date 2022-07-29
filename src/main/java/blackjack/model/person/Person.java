package blackjack.model.person;

import blackjack.model.card.CardPack;
import blackjack.model.card.Cards;
import blackjack.model.card.DeckStatus;
import blackjack.model.card.MyCards;

public interface Person {
    
    int getBetMoney();

    void bet(int money);

    void askUntilNo(CardPack providedCards);

    boolean wantReceive(String askReceiveMore);

    void receiveCard(Cards providedCards, int amout);

    boolean isOverLimit();

    int getSumOfCardNum();

    void markIfMax(int maxSumOfCards);

    void markIfBlackjack();


    StringBuilder getNameAndCards();

    StringBuilder getNameAndCards(int num);

    Name getName();
    MyCards getMyCards();

    DeckStatus getStatus();

    int getRevenue();

    boolean isBlackjack();

    void calculateRevenue();

    void setWinner();

    boolean isWinner();

    void setDrawer();


    String info();

    boolean isDealer();
}
