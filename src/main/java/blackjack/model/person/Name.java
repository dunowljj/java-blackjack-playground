package blackjack.model.person;

import java.util.Objects;

public class Name {
    public static final String ERROR_TOO_LONG_NAME = "이름은 10자 이내만 입력 가능합니다.";
    public static final int NAME_LENGTH_LIMIT = 10;

    private final String name;

    public Name(String name) {
        if (isTooLong(name)) {
            throw new IllegalArgumentException(ERROR_TOO_LONG_NAME);
        }
        this.name = name;
    }

    private boolean isTooLong(String name) {
        return name.length() > NAME_LENGTH_LIMIT;
    }

    public String value() {
        return name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name1 = (Name) o;
        return Objects.equals(name, name1.name);
    }
}
