package calculator.controller;

import calculator.constant.DelimiterConstant;
import calculator.domain.calculator.CalculatorService;
import calculator.domain.delimiter.domain.Delimiter;
import calculator.domain.delimiter.domain.DelimiterImpl;
import calculator.domain.delimiter.service.ExtractCustomDelimiterService;
import calculator.domain.delimiter.service.SplitService;
import calculator.domain.delimiter.vo.ExtractedInput;
import calculator.domain.number.Numbers;
import calculator.view.InputView;
import calculator.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class CalculatorController {

    private final InputView inputView;
    private final OutputView outputView;
    private final SplitService splitService;
    private final CalculatorService calculatorService;
    private final ExtractCustomDelimiterService extractCustomDelimiterService;

    public CalculatorController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.splitService = new SplitService();
        this.calculatorService = new CalculatorService();
        this.extractCustomDelimiterService = new ExtractCustomDelimiterService(DelimiterConstant.CUSTOM_DELIMITER_REGEX);
    }

    public void run() {
        String inputStr = inputStr();

        ExtractedInput extractedInputStr = extractCustomRegexFromInputStr(inputStr);

        List<String> numberStrList = splitInputStrByDelimiters(extractedInputStr);

        Numbers numbers = convertToNumbers(numberStrList);

        getTotalSumAndPrintResult(numbers);
    }

    private String inputStr() {
        return inputView.inputStr();
    }

    private ExtractedInput extractCustomRegexFromInputStr(String inputStr) {
        return extractCustomDelimiterService.extractCustomDelimiter(inputStr);
    }

    private List<String> splitInputStrByDelimiters(ExtractedInput extractedInput) {
        List<Delimiter> defaultDelimiters = makeDefaultDelimiters();
        return splitService.splitNumbersStrWithDelimiters(extractedInput, defaultDelimiters);
    }

    private List<Delimiter> makeDefaultDelimiters() {
        List<Delimiter> delimiters = new ArrayList<>();
        delimiters.add(new DelimiterImpl(DelimiterConstant.DEFAULT_DELIMITER_COLON));
        delimiters.add(new DelimiterImpl(DelimiterConstant.DEFAULT_DELIMITER_COMMA));
        return delimiters;
    }

    private Numbers convertToNumbers(List<String> numberStrList) {
        return new Numbers(numberStrList);
    }

    private void getTotalSumAndPrintResult(Numbers numbers) {
        Long totalSum = calculatorService.getTotalSum(numbers);
        outputView.printResult(totalSum);
    }

}