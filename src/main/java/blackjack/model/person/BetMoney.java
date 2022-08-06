package blackjack.model.person;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BetMoney betMoney1 = (BetMoney) o;
        return betMoney == betMoney1.betMoney;
    }

    @Override
    public int hashCode() {
        return Objects.hash(betMoney);
    }

    @Override
    public String toString() {
        return betMoney+"";
    }
}
