package calculator.domain.delimiter.service;

import calculator.domain.delimiter.domain.Delimiter;
import calculator.domain.delimiter.vo.ExtractedInput;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SplitService {

    public List<String> splitNumbersStrWithDelimiters(
            ExtractedInput extractedInput,
            List<Delimiter> defaultDelimiters
    ) {
        List<String> result = List.of(extractedInput.getNumbersStr());

        if(extractedInput.hasCustomDelimiter())
            result = extractedInput.getCustomDelimiter().split(result);

        for (Delimiter delimiter : defaultDelimiters) {
            result = delimiter.split(result);
        }

        return result;
    }

}
