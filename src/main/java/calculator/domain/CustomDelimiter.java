package calculator.domain;

import calculator.constant.DelimiterConstant;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomDelimiter {

    private final String customDelimiter;

    private CustomDelimiter(String customDelimiter) {
        validateIsNotEmpty(customDelimiter);
        this.customDelimiter = customDelimiter;
    }

    public static ExtractedInput extractCustomDelimiter(String inputStr) {
        final Pattern pattern = Pattern.compile(DelimiterConstant.CUSTOM_DELIMITER_REGEX);

        Matcher matcher = pattern.matcher(inputStr);
        if (!matcher.matches())
            return ExtractedInput.withoutCustomDelimiter(inputStr);

        String customDelimiter = matcher.group(1);
        String numberStr = inputStr.substring(matcher.end());

        return ExtractedInput.withCustomDelimiter(new CustomDelimiter(customDelimiter), numberStr);
    }

    private void validateIsNotEmpty(String customDelimiter) {
        if(customDelimiter == null || customDelimiter.isEmpty())
            throw new IllegalArgumentException("custom regex는 비어있을 수 없습니다");
    }
}
