package calculator.domain;

import java.util.Optional;

public class ExtractedInput {
    private final Optional<CustomDelimiter> customDelimiter;
    private final String numberStr;

    private ExtractedInput(Optional<CustomDelimiter> customDelimiter, String numberStr) {
        this.customDelimiter = customDelimiter;
        this.numberStr = numberStr;
    }

    public static ExtractedInput withCustomDelimiter(CustomDelimiter customDelimiter, String numberStr) {
        return new ExtractedInput(Optional.of(customDelimiter), numberStr);
    }

    public static ExtractedInput withoutCustomDelimiter(String numberStr) {
        return new ExtractedInput(Optional.empty(), numberStr);
    }

    public Optional<CustomDelimiter> getCustomDelimiter() {
        return customDelimiter;
    }

    public String getNumberStr() {
        return numberStr;
    }
}
