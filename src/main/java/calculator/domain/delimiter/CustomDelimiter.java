package calculator.domain.delimiter;

import calculator.constant.DelimiterConstant;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomDelimiter implements Delimiter {

    private final Delimiter customDelimiter;

    private CustomDelimiter(String customDelimiterRegex) {
        this.customDelimiter = new DelimiterImpl(customDelimiterRegex);
    }

    public static ExtractedInput extractCustomDelimiter(String inputStr) {
        final Pattern pattern = Pattern.compile(DelimiterConstant.CUSTOM_DELIMITER_REGEX);

        Matcher matcher = pattern.matcher(inputStr);
        if (!matcher.matches())
            return ExtractedInput.withoutCustomDelimiter(inputStr);

        String customDelimiter = matcher.group(1);
        String numbersStr = inputStr.substring(matcher.end());

        return ExtractedInput.withCustomDelimiter(new CustomDelimiter(customDelimiter), numbersStr);
    }

    @Override
    public List<String> split(List<String> numberStrList) {
        return customDelimiter.split(numberStrList);
    }
}
