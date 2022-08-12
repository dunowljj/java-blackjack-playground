package blackjack.model.person;

public class Profit {
    private final double profit;

    public Profit(double profit) {
        this.profit = profit;
    }

    public double value() {
        return profit;
    }
}
