package calculator.domain.number;

import java.util.List;

public class Numbers {

    private final List<Number> numbers;

    public Numbers(List<String> numberStrs) {
        numbers = numberStrs.stream().map(Number::new).toList();
        validateIsNotEmpty(numbers);
    }

    private void validateIsNotEmpty(List<Number> numbers) {
        if(numbers.isEmpty())
            throw new IllegalArgumentException("잘못된 입력입니다.");
    }

    public void forEach(forEachMethod<Long> forEachMethod) {
        for(Number number : numbers) {
            forEachMethod.forEachMethod(number.getNumber());
        }
    }

}
