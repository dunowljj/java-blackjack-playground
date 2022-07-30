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

    boolean isOverLimitAceConsidered();

    int getDeckScore();

    void markIfMax(int maxSumOfCards);
    boolean isWinner();

    void markIfBlackjack();
    boolean isBlackjack();

    void calculateRevenue();

    boolean isDealer();
    int getRevenue();

    void setWinner();


    void setDrawer();

    String info();

    StringBuilder getNameAndCards();

    StringBuilder getNameAndCards(int num);

    Name getName();

    MyCards getMyCards();

    DeckStatus getStatus();
}
