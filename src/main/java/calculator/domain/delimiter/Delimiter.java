package calculator.domain.delimiter;

import java.util.List;

public interface Delimiter {
    List<String> split(List<String> numberStrList);
}
