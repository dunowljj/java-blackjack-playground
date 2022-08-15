package blackjack.model.card;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PlayingCard{
    private static Map<Denomination, Character> classifer;

    private Suit suit;
    private Denomination denomination;

    static {
        classifer = new HashMap<>();
        classifer.put(Denomination.ACE_BIG_SCORE, 'A');
        classifer.put(Denomination.ACE, 'A');
        classifer.put(Denomination.JACK, 'J');
        classifer.put(Denomination.QUEEN, 'Q');
        classifer.put(Denomination.KING, 'K');

    }

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

    public String info() {
        if (denomination.isAlphabet()) {
            char header = classifer.get(denomination);
            return header + "" + suit.getName();
        }
        return denomination.getScore() + "" + suit.getName();
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

    public boolean isAce() {
        return denomination.isAce();
    }

    public void changeToBigAce() {
        denomination = Denomination.ACE_BIG_SCORE;
    }
}
