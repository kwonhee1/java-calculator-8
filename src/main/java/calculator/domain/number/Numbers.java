package calculator.domain.number;

import java.util.List;

public class Numbers {

    private final List<Number> numbers;

    public Numbers(List<String> numberStrs) {
        validateIsNotNull(numberStrs);
        numbers = numberStrs.stream().map(Number::new).toList();
        validateIsNotEmpty(numbers);
    }

    public void validateIsNotNull(List<String> numberStrs) {
        if (numberStrs == null || numberStrs.isEmpty())
            throw new IllegalArgumentException("number must not be empty");
    }

    private void validateIsNotEmpty(List<Number> numbers) {
        if(numbers.isEmpty())
            throw new IllegalArgumentException("number must not be empty");
    }

    public Long getTotalSum() {
        Long sum = 0L;
        for (Number number : numbers)
            sum += number.getNumber();

        return sum;
    }

}
