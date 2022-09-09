package blackjack.model.card;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PlayingCard{
    private static Map<Denomination, Character> convertor;

    static {
        convertor = new HashMap<>();
        convertor.put(Denomination.ACE_BIG_SCORE, 'A');
        convertor.put(Denomination.ACE, 'A');
        convertor.put(Denomination.JACK, 'J');
        convertor.put(Denomination.QUEEN, 'Q');
        convertor.put(Denomination.KING, 'K');

    }
    private Suit suit;
    private Denomination denomination;


    public PlayingCard(Suit suit, Denomination denomination) {
        this.suit = suit;
        this.denomination = denomination;
    }

    public Suit getSuit() {
        return suit;
    }

    public Denomination getDenomination() {
        return denomination;
    }
    public boolean isAce() {
        return denomination.isAce();
    }

    public void changeToBigAce() {
        denomination = Denomination.ACE_BIG_SCORE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayingCard that = (PlayingCard) o;
        return suit == that.suit && denomination == that.denomination;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, denomination);
    }

    @Override
    public String toString() {
        if (denomination.isAlphabet()) {
            char header = convertor.get(denomination);
            return header + suit.getName();
        }

        return denomination.getScore() + suit.getName();
    }
}
