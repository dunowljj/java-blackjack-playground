package blackjack.model.person;

public interface Participant {

    void bet(int money);

    Name getName();
    BetMoney getBetMoney();
}
