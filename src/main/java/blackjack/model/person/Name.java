package blackjack.model.person;

import java.util.Objects;

public class Name {

    public static final String ERROR_NAME_LENGTH = "이름은 6자를 초과할 수 없습니다.";
    public static final int NAME_LENGTH_LIMIT = 6;
    private final String name;

    public Name(String name) {
        if (isOverLengthLimit(name)) {
            throw new IllegalArgumentException(ERROR_NAME_LENGTH);
        }
        this.name = name;
    }
    private boolean isOverLengthLimit(String name) {
        return name.length() > NAME_LENGTH_LIMIT;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name1 = (Name) o;
        return Objects.equals(name, name1.name);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
