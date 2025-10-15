package calculator.controller;

import calculator.view.InputView;

public class CalculatorController {

    private final InputView inputView;

    public CalculatorController() {
        this.inputView = new InputView();
    }

    public void run() {
        String inputStr = inputStr();
    }

    private String inputStr() {
        return inputView.inputStr();
    }

}