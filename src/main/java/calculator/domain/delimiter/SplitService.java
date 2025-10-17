package calculator.domain.delimiter;

import java.util.List;

public class SplitService {

    public List<String> splitNumbersStrWithDelimiters(
            ExtractedInput input,
            List<Delimiter> defaultDelimiter
    ) {
        if(!input.hasCustomDelimiter())
            return splitNumbersStrWithDelimiters(input.getNumbersStr(), defaultDelimiter);

        List<Delimiter> delimiters = List.copyOf(defaultDelimiter);
        delimiters.add(input.getCustomDelimiter());

        return splitNumbersStrWithDelimiters(input.getNumbersStr(), delimiters);
    }

    private List<String> splitNumbersStrWithDelimiters(
            String numbersStr,
            List<Delimiter> delimiters
    ) {
        List<String> result = List.of(numbersStr);
        for (Delimiter delimiter : delimiters) {
            result = delimiter.split(result);
        }
        return result;
    }

}
