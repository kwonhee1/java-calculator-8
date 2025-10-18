package calculator.domain.delimiter.service;

import calculator.domain.delimiter.domain.Delimiter;
import calculator.domain.delimiter.domain.DelimiterImpl;
import calculator.domain.delimiter.vo.ExtractedInput;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtractCustomDelimiterService {

    private final Pattern pattern;

    public ExtractCustomDelimiterService(String customDelimiterRegex) {
        this.pattern = Pattern.compile(customDelimiterRegex);
    }

    public ExtractedInput extractCustomDelimiter(String inputStr) {
        Matcher matcher = pattern.matcher(inputStr);
        if (!matcher.matches())
            return ExtractedInput.withoutCustomDelimiter(inputStr);

        Delimiter customDelimiter = new DelimiterImpl(matcher.group(1));
        String numbersStr = matcher.group(2);

        return ExtractedInput.withCustomDelimiter(customDelimiter, numbersStr);
    }

}
