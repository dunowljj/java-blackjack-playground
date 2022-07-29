package blackjack.view;

import blackjack.model.person.Name;
import blackjack.model.person.Person;
import blackjack.model.person.Persons;
import blackjack.model.person.Player;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


public class InputViewTest {

    public static final String NAME_OF_DEALER = "딜러";

    @Test
    void 배팅금액입력_여러개() {
        //given
        String input = "pobi,jason";
        Persons persons = new Persons(input);
        int betMoney = 10_000;

        //when
        InputView.inputBetMoneyTest(persons, betMoney);

        //then
        assertThat(persons.getPersons()).filteredOn((p)->!p.getName().equals(new Name(NAME_OF_DEALER)))
                .map(Person::getBetMoney)
                .containsExactly(10_000, 10_000);

    }

    @Test
    void y_입력_시_추가입력() {
        //given
        String input = "y";
        Person player = new Player("pobi");

        //when, then
        assertThat(player.wantReceive(input)).isTrue();
    }
}
