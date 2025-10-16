package calculator.number;

import calculator.domain.number.Numbers;
import calculator.domain.number.Number;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class NumbersTest {

    @Test
    public void numbersTest() throws NoSuchFieldException, IllegalAccessException {
        List<String> numberStrList = List.of("1", "2", "12");
        Field numbersField = Numbers.class.getDeclaredField("numbers");
        numbersField.setAccessible(true);

        Numbers numbers = new Numbers(numberStrList);
        List<Number> numberList = (List<Number>) numbersField.get(numbers);

        Assertions.assertTrue(numberList.containsAll(List.of(
                new Number("1"),
                new Number("2"),
                new Number("12")
        )));
    }

    @Test
    public void cannotGenerateEmptyNumbers() {
        List<String> emptyNumberStrList = new ArrayList<>();
        List<String> nullNumberStrList = null;

        Assertions.assertThrows(IllegalArgumentException.class, () -> new Numbers(emptyNumberStrList));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Numbers(nullNumberStrList));
    }

    @Test
    public void getTotalSumTest() {
        Numbers numbers = new Numbers(List.of("1", "2", "12"));

        long totalSum = numbers.getTotalSum();

        Assertions.assertEquals(15L, totalSum);
    }
}
