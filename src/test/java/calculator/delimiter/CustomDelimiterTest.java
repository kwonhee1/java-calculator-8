package calculator.delimiter;

import calculator.domain.delimiter.CustomDelimiter;
import calculator.domain.delimiter.DelimiterImpl;
import calculator.domain.delimiter.ExtractedInput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

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

    private String getExtractCustomDelimiter(String delimiterRegex) throws NoSuchFieldException, IllegalAccessException {
        String inputStr = "//"+delimiterRegex+"\n";
        Field customDelimiterField = CustomDelimiter.class.getDeclaredField("customDelimiter");
        Field delimiterRegexField = DelimiterImpl.class.getDeclaredField("delimiterRegex");
        customDelimiterField.setAccessible(true);
        delimiterRegexField.setAccessible(true);

        ExtractedInput customDelimiter = CustomDelimiter.extractCustomDelimiter(inputStr);

        if(!customDelimiter.hasCustomDelimiter())
            throw new IllegalArgumentException("delimiter not found");

        DelimiterImpl delimiter = (DelimiterImpl) customDelimiterField.get(customDelimiter.getCustomDelimiter());
        return (String) delimiterRegexField.get(delimiter);
    }

}
