package blackjack.model.person;

public class BetMoney {
    public static final int BETTING_LOWER_LIMIT = 1;
    public static final String BETMONEY_UNDER_LOWER_LIMIT = "1원 이상 배팅하세요.";
    private final int betMoney;

    public BetMoney(int betMoney) {
        if (betMoney < BETTING_LOWER_LIMIT) {
            throw new IllegalArgumentException(BETMONEY_UNDER_LOWER_LIMIT);
        }
        this.betMoney = betMoney;
    }

    public int value() {
        return betMoney;
    }

    @Override
    public String toString() {
        return betMoney+"";
    }
}
