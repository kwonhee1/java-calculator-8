package calculator.delimiter;

import calculator.domain.delimiter.domain.DelimiterImpl;
import calculator.domain.delimiter.EscapeCharacter;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class EscapeCharacterTest {

    @Test
    public void replaceTest() {
        String escapeCharacter = "\\123\\t12\\12";

        String result = EscapeCharacter.ESCAPE.replaceEscapeToReplacement(escapeCharacter);

        Assertions.assertThat(result).isEqualTo("\\\\123\\\\t12\\\\12");
    }

    @Test
    public void replaceDotTest() {
        String dot = ".";
        String numberStrWithDot = "123.12";

        DelimiterImpl delimiter = new DelimiterImpl(dot);

        List result = delimiter.split(List.of(numberStrWithDot));

        Assertions.assertThat(result).contains("123", "12");
    }

    @Test
    public void replaceAllTest() {
        String inputCustomDelimiterRegex = "\t.\\.";
        String inputNumberStrs = "12\t.\\.12";

        DelimiterImpl delimiter = new DelimiterImpl(inputCustomDelimiterRegex);

        List<String> result = delimiter.split(List.of(inputNumberStrs));

        Assertions.assertThat(result).contains("12", "12");
    }

}
