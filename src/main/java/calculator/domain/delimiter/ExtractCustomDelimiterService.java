package calculator.domain.delimiter;

import calculator.constant.DelimiterConstant;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtractCustomDelimiterService {

    private final Pattern pattern = Pattern.compile(DelimiterConstant.CUSTOM_DELIMITER_REGEX);

    public ExtractedInput extractCustomDelimiter(String inputStr) {
        Matcher matcher = pattern.matcher(inputStr);
        if (!matcher.matches())
            return ExtractedInput.withoutCustomDelimiter(inputStr);

        Delimiter customDelimiter = new DelimiterImpl(matcher.group(1));
        String numbersStr = matcher.group(2);

        return ExtractedInput.withCustomDelimiter(customDelimiter, numbersStr);
    }

}
