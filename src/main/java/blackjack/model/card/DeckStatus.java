package blackjack.model.card;

import java.util.function.Function;

public enum DeckStatus {
    BLACKJACK((x) -> (int)(1.5 * x)),
    WIN((x) -> (int)(1.0 * x)),
    LOSE((x) -> (int)(-1.0 * x)),
    DRAW((x) -> 0);

    private Function<Integer, Integer> expression;
    DeckStatus(Function<Integer, Integer> expression) {
        this.expression = expression;
    }
    
    public int calculate(int betMoney) {
        return expression.apply(betMoney);
    }

}