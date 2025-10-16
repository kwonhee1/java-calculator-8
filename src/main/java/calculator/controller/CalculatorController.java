package calculator.controller;

import calculator.constant.DelimiterConstant;
import calculator.domain.calculator.CalculatorService;
import calculator.domain.delimiter.*;
import calculator.domain.number.Numbers;
import calculator.view.InputView;
import calculator.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class CalculatorController {

    private final InputView inputView;
    private final OutputView outputView;
    private final DelimiterService delimiterService;
    private final CalculatorService calculatorService;
    private final ExtractCustomDelimiterService extractCustomDelimiterService;

    public CalculatorController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.delimiterService = new DelimiterService();
        this.calculatorService = new CalculatorService();
        this.extractCustomDelimiterService = new ExtractCustomDelimiterService();
    }

    public void run() {
        String inputStr = inputStr();

        ExtractedInput extractedInput = getCustomRegexFromInputStr(inputStr);
        List<Delimiter> defaultDelimiters = makeDefaultDelimiters();

        List<String> numberStrList = delimiterService.splitNumbersStrWithDelimiters(extractedInput, defaultDelimiters);

        Numbers numbers = new Numbers(numberStrList);

        Long sum = getTotalSum(numbers);

        printResult(sum);
    }

    private String inputStr() {
        return inputView.inputStr();
    }

    private ExtractedInput getCustomRegexFromInputStr(String inputStr) {
        return extractCustomDelimiterService.extractCustomDelimiter(inputStr);
    }

    private List<Delimiter> makeDefaultDelimiters() {
        List<Delimiter> delimiters = new ArrayList<>();
        delimiters.add(new DelimiterImpl(DelimiterConstant.DEFAULT_DELIMITER_COLON));
        delimiters.add(new DelimiterImpl(DelimiterConstant.DEFAULT_DELIMITER_COMMA));
        return delimiters;
    }

    private Long getTotalSum(Numbers numbers) {
        return calculatorService.getTotalSum(numbers);
    }

    private void printResult(Long sum) {
        outputView.printResult(sum);
    }

}