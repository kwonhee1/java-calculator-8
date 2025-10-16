package calculator.delimiter;

import calculator.domain.delimiter.CustomDelimiter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class DelimiterGenerateTest {

    @Test
    public void cannotGenerateEmptyDelimiter() throws NoSuchMethodException {
        Constructor<CustomDelimiter> constructor = CustomDelimiter.class.getDeclaredConstructor(String.class);
        constructor.setAccessible(true);

        String nullDelimiter = null;
        String emptyDelimiter = "";

        // reflection.Constructor.newInstance(args...) : if throw Error at constructor -> throw new InvocationTagetException(Error);
        Assertions.assertThrows(InvocationTargetException.class, ()->constructor.newInstance(nullDelimiter));
        Assertions.assertThrows(InvocationTargetException.class, ()->constructor.newInstance(emptyDelimiter));
    }

}
