package calculator.controller;

import calculator.domain.CustomDelimiter;
import calculator.domain.ExtractedInput;
import calculator.view.InputView;

public class CalculatorController {

    private final InputView inputView;

    public CalculatorController() {
        this.inputView = new InputView();
    }

    public void run() {
        String inputStr = inputStr();
        ExtractedInput extractedInput = getCustomRegexFromInputStr(inputStr);
    }

    private String inputStr() {
        return inputView.inputStr();
    }

    private ExtractedInput getCustomRegexFromInputStr(String inputStr) {
        return CustomDelimiter.extractCustomDelimiter(inputStr);
    }

}