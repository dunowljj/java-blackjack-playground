package blackjack.model.person;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Names {
    public static final String INPUT_NAMES_DELIMITER = ",";
    public static final String ERROR_NULL_INPUT_NAMES = "값이 입력되지 않았습니다.";

    private final List<Name> names;

    public Names(List<Name> names) {
        this.names = names;
    }

    public static Names from(String names) {
        Optional.ofNullable(names).orElseThrow(() -> new IllegalArgumentException(ERROR_NULL_INPUT_NAMES));

        String[] bunchOfName = names.split(INPUT_NAMES_DELIMITER);

        return new Names(Arrays.stream(bunchOfName).map((name) -> new Name(name))
                .collect(Collectors.toList()));
    }

    public List<Name> value() {
        return Collections.unmodifiableList(names);
    }

    @Override
    public String toString() {
        return names.stream().map(Name::toString)
                .collect(Collectors.joining(", "));
    }
}
