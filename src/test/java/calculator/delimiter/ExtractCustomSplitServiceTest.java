package calculator.delimiter;

import calculator.domain.delimiter.DelimiterImpl;
import calculator.domain.delimiter.ExtractCustomDelimiterService;
import calculator.domain.delimiter.ExtractedInput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

public class ExtractCustomSplitServiceTest {

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
        String inputStr = "//"+delimiterRegex+"\n123";

        Field delimiterRegexField = DelimiterImpl.class.getDeclaredField("delimiterRegex");
        delimiterRegexField.setAccessible(true);

        ExtractedInput result = extractCustomDelimiterService.extractCustomDelimiter(inputStr);

        if(!result.hasCustomDelimiter())
            throw new IllegalArgumentException("delimiter not found");

        DelimiterImpl delimiter = (DelimiterImpl) result.getCustomDelimiter();
        return (String) delimiterRegexField.get(delimiter);
    }

    @Test
    public void extractNumberStrTest() {
        String delimiterRegex = "ab";
        String numberStr = "1ab2ab3";
        String inputStr = "//"+delimiterRegex+"\n"+numberStr;

        ExtractedInput result = extractCustomDelimiterService.extractCustomDelimiter(inputStr);

        Assertions.assertTrue(result.hasCustomDelimiter());
        org.assertj.core.api.Assertions.assertThat(result.getNumbersStr()).isEqualTo(numberStr);
    }

}
