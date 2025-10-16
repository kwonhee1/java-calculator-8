package calculator.controller;

import calculator.constant.DelimiterConstant;
import calculator.domain.delimiter.*;
import calculator.view.InputView;

import java.util.ArrayList;
import java.util.List;

public class CalculatorController {

    private final InputView inputView;
    private final DelimiterService delimiterService;

    public CalculatorController() {
        this.inputView = new InputView();
        this.delimiterService = new DelimiterService();
    }

    public void run() {
        String inputStr = inputStr();

        ExtractedInput extractedInput = getCustomRegexFromInputStr(inputStr);
        List<Delimiter> defaultDelimiters = makeDefaultDelimiters();

        List<String> numberStrList = delimiterService.splitNumbersStrWithDelimiters(extractedInput, defaultDelimiters);
    }

    private String inputStr() {
        return inputView.inputStr();
    }

    private ExtractedInput getCustomRegexFromInputStr(String inputStr) {
        return CustomDelimiter.extractCustomDelimiter(inputStr);
    }

    private List<Delimiter> makeDefaultDelimiters() {
        List<Delimiter> delimiters = new ArrayList<>();
        delimiters.add(new DelimiterImpl(DelimiterConstant.DEFAULT_DELIMITER_COLON));
        delimiters.add(new DelimiterImpl(DelimiterConstant.DEFAULT_DELIMITER_COMMA));
        return delimiters;
    }

}