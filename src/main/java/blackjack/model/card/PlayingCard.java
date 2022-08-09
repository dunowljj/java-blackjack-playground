package blackjack.model.card;

import java.util.Objects;

public class PlayingCard{
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

    public String info() {
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
