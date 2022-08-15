package blackjack.model.person;

public class Profit {
    private double profit;

    public Profit(double profit) {
        this.profit = profit;
    }

    public int value() {
        return (int)profit;
    }
}
