package blackjack.model;

import blackjack.model.person.Name;
import blackjack.model.person.Person;
import blackjack.model.person.Persons;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

public class PersonsTest {

    @Test
    void 참여자_이름_입력_여러개() {
        //given
        String input = "pobi,jason";

        //when
        Persons persons = new Persons(input);
        List<Person> list = persons.getPersons();

        //then
        Assertions.assertThat(list.stream()
                        .map(Person::getName)
                        .map(Name::getName)
                        .collect(Collectors.toList()))
                .contains("pobi","jason");
    }

    @Test
    void 참여자_이름_입력_여러개_공백제거() {
        //given
        String input = "pobi, jason";

        //when
        Persons persons = new Persons(input);
        List<Person> list = persons.getPersons();

        //then
        Assertions.assertThat(list.stream()
                        .map(Person::getName)
                        .map(Name::getName)
                        .collect(Collectors.toList()))
                .contains("pobi","jason");
    }
}
