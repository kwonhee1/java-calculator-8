package calculator.domain.delimiter;

import java.util.Optional;

public class ExtractedInput {
    private final Optional<CustomDelimiter> customDelimiter;
    private final String numbersStr;

    private ExtractedInput(Optional<CustomDelimiter> customDelimiter, String numbersStr) {
        this.customDelimiter = customDelimiter;
        this.numbersStr = numbersStr;
    }

    public static ExtractedInput withCustomDelimiter(CustomDelimiter customDelimiter, String numberStr) {
        return new ExtractedInput(Optional.of(customDelimiter), numberStr);
    }

    public static ExtractedInput withoutCustomDelimiter(String numberStr) {
        return new ExtractedInput(Optional.empty(), numberStr);
    }

    public boolean hasCustomDelimiter() {
        return customDelimiter.isPresent();
    }

    public CustomDelimiter getCustomDelimiter() {
        if(!hasCustomDelimiter())
            throw new IllegalStateException("custom delimiter not found");

        return customDelimiter.get();
    }

    public String getNumbersStr() {
        return numbersStr;
    }
}
