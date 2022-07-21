package blackjack.view;

import blackjack.model.Name;
import blackjack.model.Person;
import blackjack.model.Persons;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


public class InputViewTest {
    @Test
    void 배팅금액입력_여러개() {
        //given
        String input = "pobi,jason";
        Persons persons = new Persons(input);
        int betMoney = 10_000;

        //when
        InputView.inputBetMoney(persons, betMoney);

        //then
        assertThat(persons.getPersons()).filteredOn((p)->!p.getName().equals(new Name("Dealer")))
                .map(Person::getBetMoney)
                .containsExactly(10_000, 10_000);

    }
}
