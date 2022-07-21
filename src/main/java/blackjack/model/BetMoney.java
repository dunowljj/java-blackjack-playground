package blackjack.model;

public class BetMoney {
    public static final String ERROR_BAD_BETTING = "1원 이상 배팅해주세요.";
    private int money;

    public BetMoney(int money) {
        if (isZeroOrNagative(money)) {
            throw new IllegalArgumentException(ERROR_BAD_BETTING);
        }
        this.money = money;
    }

    private boolean isZeroOrNagative(int money) {
        return money <= 0;
    }

    public int getMoney() {
        return this.money;
    }
}
