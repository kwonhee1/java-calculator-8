package calculator.domain.calculator;

import calculator.domain.number.Numbers;

import java.util.concurrent.atomic.AtomicReference;

public class CalculatorService {

    public Long getTotalSum(Numbers numbers) {
        AtomicReference<Long> totalSum = new AtomicReference<>(0L);
        numbers.forEach(number-> totalSum.set(totalSum.get() + number));
        return totalSum.get();
    }

}
