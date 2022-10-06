package blackjack.model.person;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public Players(Player[] players) {
        this.players = Arrays.stream(players)
                .collect(Collectors.toList());
    }

    // todo:불변 반환시 participants와의 참조가 끊긴다.
    public List<Player> list() {
        return players;
    }


}
