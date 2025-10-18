package calculator.delimiter;

import calculator.domain.delimiter.domain.DelimiterImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DelimiterGenerateTest {

    @Test
    public void cannotGenerateEmptyDelimiter() throws NoSuchMethodException {
        String nullDelimiter = null;
        String emptyDelimiter = "";

        // reflection.Constructor.newInstance(args...) : if throw Error at constructor -> throw new InvocationTagetException(Error);
        Assertions.assertThrows(IllegalArgumentException.class, ()-> new DelimiterImpl(nullDelimiter));
        Assertions.assertThrows(IllegalArgumentException.class, ()-> new DelimiterImpl(emptyDelimiter));
    }

}
