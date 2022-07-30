package blackjack.model.person;

import blackjack.model.card.*;
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
        persons.receiveInitialCards(cards);

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

    @Test
    void 가장_큰_숫자_합_가진사람_여러명_찾기() {
        //given
        MyCards myCards1 = new MyCards();
        MyCards myCards2 = new MyCards();
        myCards1.add(new Card("클로버","8"));
        myCards1.add(new Card("하트","7"));
        myCards2.add(new Card("하트","7"));
        myCards2.add(new Card("클로버","8"));

        personList.get(0).receiveCard(myCards1, 2);
        personList.get(1).receiveCard(myCards2,2);

        //when
        persons.markWinner();

        //then
        assertThat(personList.get(1).isWinner());
        assertThat(personList.get(0).isWinner());
    }

    @Test
    void 가장_큰_숫자_합_가진사람_한명_찾기() {
        //given
        MyCards myCards1 = new MyCards();
        MyCards myCards2 = new MyCards();
        myCards1.add(new Card("클로버","2"));
        myCards1.add(new Card("하트","1"));
        myCards2.add(new Card("하트","7"));
        myCards2.add(new Card("클로버","8"));

        personList.get(0).receiveCard(myCards1, 2);
        personList.get(1).receiveCard(myCards2,2);

        //when
        persons.markWinner();

        //then
        assertThat(personList.get(1).isWinner());
    }

    @Test
    void 블랙잭_존재하는지_확인() {
        //given
        MyCards myCards1 = new MyCards();
        MyCards myCards2 = new MyCards();
        myCards1.add(new Card("클로버","1"));
        myCards1.add(new Card("하트","A"));
        myCards2.add(new Card("하트","A"));
        myCards2.add(new Card("클로버","Q"));

        personList.get(0).receiveCard(myCards1, 2);
        personList.get(1).receiveCard(myCards2,2);

        //when
        persons.markBlackjack();

        //then
        assertThat(persons.blackjackExist()).isTrue();
    }
    @Test
    void 블랙잭_없는지_확인() {
        //given
        MyCards myCards1 = new MyCards();
        MyCards myCards2 = new MyCards();
        myCards1.add(new Card("클로버","K"));
        myCards1.add(new Card("하트","5"));
        myCards2.add(new Card("하트","A"));
        myCards2.add(new Card("클로버","1"));

        personList.get(0).receiveCard(myCards1, 2);
        personList.get(1).receiveCard(myCards2,2);

        //when
        persons.markBlackjack();

        //then
        assertThat(persons.blackjackExist()).isFalse();
    }

    @AfterEach
    void clean() {
        personList.clear();
        personList.add(new Dealer());
        cards = new CardPack();
    }
}
