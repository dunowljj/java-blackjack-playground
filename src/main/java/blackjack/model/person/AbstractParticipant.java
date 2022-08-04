package blackjack.model.person;

public class AbstractParticipant implements Participant{
    private final Name name;

    public AbstractParticipant(String name) {
        this.name = new Name(name);
    }

    public Name getName() {
        return name;
    }
}
