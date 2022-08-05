package blackjack.model.person;

public abstract class AbstractParticipant implements Participant{
    private final Name name;

    private BetMoney betMoney;

    public AbstractParticipant(String name) {
        this.name = new Name(name);
    }

    @Override
    public void bet(int money) {
        betMoney = new BetMoney(money);
        return;
    }

    @Override
    public Name getName() {
        return name;
    }

    @Override
    public BetMoney getBetMoney() {
        return betMoney;
    }
}
