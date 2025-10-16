package calculator.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

public class CustomDelimiterTest {

    @Test
    void extractCustomDelimiterTest() throws NoSuchFieldException, IllegalAccessException {
        String delimiter = "123123";

        String customDelimiter = getExtractCustomDelimiter(delimiter);

        Assertions.assertEquals(delimiter, customDelimiter);
    }

    @Test
    void extractWithVariableCaseTest() throws NoSuchFieldException, IllegalAccessException {
        String onlySpace = "  ";
        String onlySlash1 = "/";
        String onlySlash2 = "//";
        String onlyNewLine = "\n";
        String nothing = "";

        Assertions.assertEquals(onlySpace, getExtractCustomDelimiter(onlySpace));
        Assertions.assertEquals(onlySlash1, getExtractCustomDelimiter(onlySlash1));
        Assertions.assertEquals(onlySlash2, getExtractCustomDelimiter(onlySlash2));
        Assertions.assertEquals(onlyNewLine, getExtractCustomDelimiter(onlyNewLine));
        Assertions.assertThrows(IllegalArgumentException.class, ()->getExtractCustomDelimiter(nothing));
    }

    private String getExtractCustomDelimiter(String delimiter) throws NoSuchFieldException, IllegalAccessException {
        String inputStr = "//"+delimiter+"\n";
        Field delimiterField = CustomDelimiter.class.getDeclaredField("customDelimiter");
        delimiterField.setAccessible(true);

        Optional<CustomDelimiter> customDelimiter = CustomDelimiter.extractCustomDelimiter(inputStr);

        if(customDelimiter.isEmpty())
            throw new IllegalArgumentException("delimiter not found");

        return (String) delimiterField.get(customDelimiter.get());
    }

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
