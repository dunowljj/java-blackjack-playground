package blackjack.model.person;

public class BetMoney {
    public static final String ERROR_UNDER_ZERO_BETTING = "1원 이상 배팅해주세요.";
    private int money;

    public BetMoney(int money) {
        if (isZeroOrNegative(money)) {
            throw new IllegalArgumentException(ERROR_UNDER_ZERO_BETTING);
        }
        this.money = money;
    }

    private boolean isZeroOrNegative(int money) {
        return money <= 0;
    }

    public int getMoney() {
        return this.money;
    }
}
