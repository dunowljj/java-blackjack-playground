package blackjack.model.person;

import java.util.Objects;
import java.util.Optional;

public class BetMoney {
    public static final int BETTING_LOWER_LIMIT = 1;
    public static final String ERROR_BETMONEY_UNDER_LOWER_LIMIT = "1원 이상 배팅하세요.";
    public static final String ERROR_NO_INPUT_BETMONEY = "배팅 금액을 입력해주세요.";
    private final int betMoney;

    public BetMoney(int betMoney) {
//        Optional<Integer> opt = Optional.ofNullable(betMoney);

        // todo: 배팅 금액이 입력되지 않았을때랑, 잘못된 숫자가 입력되었을때를 구분한다면 Optional 하나로 해결이 안된다.
        Optional.ofNullable(betMoney).orElseThrow(() -> new IllegalArgumentException(ERROR_NO_INPUT_BETMONEY));

        /*opt.filter((money) -> money >= BETTING_LOWER_LIMIT)
                .orElseThrow(() -> new IllegalArgumentException(ERROR_BETMONEY_UNDER_LOWER_LIMIT));*/

        if (betMoney < BETTING_LOWER_LIMIT) {
            throw new IllegalArgumentException(ERROR_BETMONEY_UNDER_LOWER_LIMIT);
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
