package calculator.delimiter;

import calculator.domain.delimiter.DelimiterImpl;
import calculator.domain.delimiter.ExtractCustomDelimiterService;
import calculator.domain.delimiter.ExtractedInput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

public class ExtractCustomDelimiterServiceTest {

    private final ExtractCustomDelimiterService extractCustomDelimiterService = new ExtractCustomDelimiterService();

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

        Field delimiterRegexField = DelimiterImpl.class.getDeclaredField("delimiterRegex");
        delimiterRegexField.setAccessible(true);

        ExtractedInput result = extractCustomDelimiterService.extractCustomDelimiter(inputStr);

        if(!result.hasCustomDelimiter())
            throw new IllegalArgumentException("delimiter not found");

        DelimiterImpl delimiter = (DelimiterImpl) result.getCustomDelimiter();
        return (String) delimiterRegexField.get(delimiter);
    }

}
