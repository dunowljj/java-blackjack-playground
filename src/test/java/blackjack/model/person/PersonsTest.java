package blackjack.model.person;

import blackjack.model.card.CardPack;
import blackjack.model.card.Cards;
import blackjack.model.card.MyCards;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

public class PersonsTest {
    Persons persons;
    List<Person> personList;
    Cards cards;

    @BeforeEach
    void setUp() {
        String input = "pobi,jason";

        // 딜러가 스태틱 블록에서 초기화되는데, 스태틱 블록은 최초 클래스 로드에만 호출된다.
        persons = new Persons(input);
        personList = persons.getPersons();
        cards = new CardPack();
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
        final int NUM_OF_FIRST_DISTRIBUTION = 2;


        //when
        persons.receiveCard(cards, NUM_OF_FIRST_DISTRIBUTION);

        //then
        assertThat(personList).map(Person::getMyCards).map(MyCards::getCards)
                .map(List::size)
                .containsExactly(2, 2, 2);
    }

    @Test
    void 카드_1장_배급() {
        //given
        MyCards myCards = new MyCards();

        //when
        persons.receiveCard(cards, 1);

        //then
        assertThat(personList).map(Person::getMyCards).map(MyCards::getCards)
                .map(List::size)
                .containsExactly(1, 1, 1);
    }

    @AfterEach
    void clean() {
        personList.clear();
        personList.add(new Dealer());
        cards = new CardPack();
    }
}
