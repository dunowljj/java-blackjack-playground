package blackjack.model.person;

import blackjack.model.card.CardPack;
import blackjack.model.card.Cards;
import blackjack.model.card.MyCards;

public interface Person {
    
    int getBetMoney();

    void bet(int money);

    void askUntilNo(CardPack providedCards);
    boolean wantReceive(String askReceiveMore);
    void receiveCard(Cards providedCards, int amout);

    void markIfMax(int maxSumOfCards);
    void setWinner();

    void markIfBlackjack();
    boolean isBlackjack();

    int calculateDeckScore();

    boolean isDealer();

    boolean isOverLimit();

    boolean isOverLimitAceConsidered();
    void calculateRevenue();

    boolean isWinner();

    void setDrawer();

    String info();

    StringBuilder getNameAndCards();

    StringBuilder getNameAndCards(int num);

    Name getName();

    MyCards getMyCards();

    int getRevenue();

}
