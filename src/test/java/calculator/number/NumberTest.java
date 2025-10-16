package calculator.number;

import calculator.domain.number.Number;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

public class NumberTest {

    @Test
    public void numberTest() throws NoSuchFieldException, IllegalAccessException {
        Long numberValue = 123L;
        Field numberField = Number.class.getDeclaredField("number");
        numberField.setAccessible(true);

        Number number = new Number(numberValue.toString());

        Assertions.assertEquals(numberValue, numberField.get(number));
    }

    @Test
    public void cannotGenerateEmptyNumber() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Number(""));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Number(null));
    }

    @Test
    public void cannotGenerateWithString() {
        String numberStrWithAfterOtherString = "123abc";
        String numberStrWithBeforeOtherString = "abc123";

        Assertions.assertThrows(IllegalArgumentException.class, () -> new Number(numberStrWithAfterOtherString));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Number(numberStrWithBeforeOtherString));
    }

    @Test
    public void numberMustPositive() {
        String negativeNumberStr = "-123";
        String zeroNumberStr = "0";
        String positiveNumberStr = "123";

        Assertions.assertThrows(IllegalArgumentException.class, () -> new Number(negativeNumberStr));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Number(zeroNumberStr));
        Assertions.assertDoesNotThrow(() -> new Number(positiveNumberStr));
    }

}
