package blackjack.model.card;

import java.util.Objects;

public class Card {
    private String shape;
    private String num;

    public Card(String shape, String num) {
        this.shape = shape;
        this.num = num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(shape, card.shape) && Objects.equals(num, card.num);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shape, num);
    }
}
