package calculator.domain;

import calculator.constant.DelimiterConstant;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomDelimiter {

    private static final Pattern pattern = Pattern.compile(DelimiterConstant.CUSTOM_DELIMITER_REGEX);

    private final String customDelimiter;

    private CustomDelimiter(String customDelimiter) {
        validateIsNotEmpty(customDelimiter);
        this.customDelimiter = customDelimiter;
    }

    public static Optional<CustomDelimiter> extractCustomDelimiter(String inputStr) {
        String customDelimiter;

        Matcher matcher = pattern.matcher(inputStr);
        if (!matcher.matches())
            return Optional.empty();

        customDelimiter = matcher.group(1);

        return Optional.of(new CustomDelimiter(customDelimiter));
    }

    private void validateIsNotEmpty(String customDelimiter) {
        if(customDelimiter == null || customDelimiter.isEmpty())
            throw new IllegalArgumentException("custom regex는 비어있을 수 없습니다");
    }
}
