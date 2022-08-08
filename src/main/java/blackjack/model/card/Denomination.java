package blackjack.model.card;

public enum Denomination {
    ACE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10),
    QUEEN(10),
    KING(10);

    private static final int ACE_BIG_SCORE = 11;

    Denomination(int score) {
        this.score = score;

    }
    private int score;

    public int getScore() {
        return score;
    }

    public boolean isAce() {
        return this == ACE;
    }

    public void chooseBigAce() {
        this.score = ACE_BIG_SCORE;
    }

    public boolean isAlphabet() {
        return (this == JACK) || (this == QUEEN) || (this == KING) || (this == ACE);
    }
}
