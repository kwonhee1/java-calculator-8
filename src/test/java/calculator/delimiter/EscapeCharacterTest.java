package calculator.delimiter;

import calculator.domain.delimiter.EscapeCharacter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class EscapeCharacterTest {

    @Test
    public void replaceAllTest() {
        String escapeCharacter = "\\123\\t12\\12";

        String result = EscapeCharacter.ESCAPE.replaceAllEscape(escapeCharacter);

        Assertions.assertThat(result).isEqualTo("\\\\123\\\\t12\\\\12");
    }

}
