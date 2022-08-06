package blackjack.model.state;

import blackjack.model.card.Cards;
import blackjack.model.card.PlayingCard;


public interface State {

    boolean isFinished();

    State drawCard(PlayingCard playingCard);

    State stay();

    Cards cards();
}
