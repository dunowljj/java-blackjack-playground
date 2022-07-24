package blackjack.model.person;

import blackjack.model.card.Cards;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

public class PersonsTest {
    Persons persons;
    List<Person> personList;

    @BeforeEach
    void setUp() {
        String input = "pobi,jason";

        persons = new Persons(input);
        personList = persons.getPersons();
    }

    @Test
    void 참여자_이름_입력_여러개() {
        //then
        assertThat(personList.stream()
                .map(Person::getName)
                .map(Name::getName)
                .collect(Collectors.toList()))
                .contains("pobi", "jason");
    }

    @Test
    void 참여자_이름_입력_여러개_공백제거() {
        //given
        String input = "pobi, jason";

        //when
        Persons persons = new Persons(input);
        List<Person> list = persons.getPersons();

        //then
        assertThat(list.stream()
                .map(Person::getName)
                .map(Name::getName)
                .collect(Collectors.toList()))
                .contains("pobi", "jason");
    }

    @Test
    void 카드_첫_2장_배급() {
        //given
        int amount = 2;

        Cards cards = new Cards();
        cards.setUpWholeCard();

        //when
        personList.stream().forEach((p) -> p.recieveCard(cards));
        //then
        assertThat(personList).map(Person::getMyCards).map(Cards::getCards)
                .map(List::size)
                .containsExactly(2, 2, 2);
    }

    @AfterEach
    void clean() {
        personList.clear();
    }
}
