package calculator.controller;

import calculator.domain.CustomDelimiter;
import calculator.view.InputView;

import java.util.Optional;

public class CalculatorController {

    private final InputView inputView;

    public CalculatorController() {
        this.inputView = new InputView();
    }

    public void run() {
        String inputStr = inputStr();
        Optional<CustomDelimiter> customDelimiter = getCustomRegexFromInputStr(inputStr);
    }

    private String inputStr() {
        return inputView.inputStr();
    }

    private Optional<CustomDelimiter> getCustomRegexFromInputStr(String inputStr) {
        return CustomDelimiter.extractCustomDelimiter(inputStr);
    }

}