package calculator.delimiter;

import calculator.constant.DelimiterConstant;
import calculator.domain.delimiter.domain.DelimiterImpl;
import calculator.domain.delimiter.domain.Delimiter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DelimiterSplitTest {

    @Test
    public void defaultDelimiterTest(){
        List<String> numberStr = List.of("1", "2", "3", "4");

        Assertions.assertTrue(doSplit(numberStr, DelimiterConstant.DEFAULT_DELIMITER_COLON).containsAll(numberStr));
        Assertions.assertTrue(doSplit(numberStr, DelimiterConstant.DEFAULT_DELIMITER_COMMA).containsAll(numberStr));
    }

    @Test
    public void customDelimiterWithNumberTest(){
        String numberRegex1 = "9";
        String numberRegex2 = "89";

        List<String> numberStr = List.of("1", "2", "3", "4");

        Assertions.assertTrue(doSplit(numberStr, numberRegex1).containsAll(numberStr));
        Assertions.assertTrue(doSplit(numberStr, numberRegex2).containsAll(numberStr));
    }

    @Test
    public void customDelimiterWithVariableCaseTest(){
        String onlySpace = "  ";
        String onlySlash1 = "/";
        String onlySlash2 = "//";
        String onlyNewLine = "\n";

        List<String> numberStr = List.of("1", "2", "3", "4");

        Assertions.assertTrue(doSplit(numberStr, onlySpace).containsAll(numberStr));
        Assertions.assertTrue(doSplit(numberStr, onlySlash1).containsAll(numberStr));
        Assertions.assertTrue(doSplit(numberStr, onlySlash2).containsAll(numberStr));
        Assertions.assertTrue(doSplit(numberStr, onlyNewLine).containsAll(numberStr));
    }

    private List<String> doSplit(List<String> numberStr, String regex) {
        Delimiter delimiter = new DelimiterImpl(regex);

        StringBuilder builder = new StringBuilder(regex);
        for (String number : numberStr) {
            builder.append(number + regex);
        }

        return delimiter.split(List.of(builder.toString()));
    }

    @Test
    public void borderRegexTest() {
        String regex = ",";
        List<String> numberStr = List.of(",12,34,");

        List<String> splitNumberStrList = new DelimiterImpl(regex).split(numberStr);

        Assertions.assertTrue(splitNumberStrList.containsAll(List.of("12", "34")));
    }

}
