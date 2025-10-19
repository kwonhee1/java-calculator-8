package calculator;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    @Test
    void 커스텀_구분자_사용() {
        assertSimpleTest(() -> {
            run("//;\\n1");
            assertThat(output()).contains("결과 : 1");
        });
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("-1,2,3"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("\\n을 제외한 특수문자 처리 test")
    public void all_special_character() {
        String totalSpecialCharacter = "~!@#$%^&*(){}[]':;<>//`/-+*/|\\?_-";
        assertSimpleTest(() -> {
            run(String.format("//%s\\n11%s22",  totalSpecialCharacter, totalSpecialCharacter).toString());
            assertThat(output()).contains("결과 : 33");
        });
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
