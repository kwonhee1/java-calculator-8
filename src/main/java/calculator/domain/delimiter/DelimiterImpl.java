package calculator.domain.delimiter;

import java.util.ArrayList;
import java.util.List;

public class DelimiterImpl implements Delimiter {

    private final String delimiterRegex;

    public DelimiterImpl(String delimiterRegex) {
        validateIsNotEmpty(delimiterRegex);
        delimiterRegex = toEscapeRegexIfEscapeDelimiter(delimiterRegex);
        this.delimiterRegex = delimiterRegex;
    }

    private void validateIsNotEmpty(String delimiterRegex) {
        if (delimiterRegex == null || delimiterRegex.isEmpty())
            throw new IllegalArgumentException("delimiter can not be null or empty");
    }

    private String toEscapeRegexIfEscapeDelimiter(String delimiterRegex) {
        return EscapeCharacter.ESCAPE.replaceAllEscape(delimiterRegex);
    }

    @Override
    public List<String> split(List<String> numberStrList) {
        List<String> result = new ArrayList<>();
        for (String number : numberStrList) {
            result.addAll(List.of(number.split(delimiterRegex)));
        }
        return result;
    }
}
