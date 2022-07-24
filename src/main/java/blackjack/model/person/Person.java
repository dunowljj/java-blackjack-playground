package blackjack.model.person;

import blackjack.model.card.Cards;

public interface Person {
    
    Name getName();

    int getBetMoney();

    void bet(int money);

    Cards getMyCards();

    void recieveCard(Cards cards);

    StringBuilder getCurrentOwnCards(int num);
}
