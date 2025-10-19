package calculator.domain.delimiter.util;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum SpecialCharacter {
    DOT("."),
    DOLLAR("$"),
    PERCENT("%"),
    LEFT_PARENTHESIS("("),
    RIGHT_PARENTHESIS(")"),
    LEFT_BRACKET("["),
    RIGHT_BRACKET("]"),
    LEFT_BRACE("{"),
    RIGHT_BRACE("}"),
    PULSE("+"),
    MULTIPLY("*"),
    VERTICAL_BAR("|"),
    COMMA(","),
    QUESTION_MARK("?"),
    COLON(":"),
    CARET("^"),
    ESCAPE("\\"),
    ;

    private Pattern escapeCharacterPattern;
    private String replacement;

    private static final String FIND_REGEX_FORMAT="(\\%s)";
    private static final String REPLACE_REGEX_FORMAT="\\%s";

    SpecialCharacter(String specialCharacter) {
        escapeCharacterPattern = Pattern.compile(findRegex(specialCharacter));
        this.replacement = replacement(specialCharacter);
    }

    private String findRegex(String specialCharacter) {
        return String.format(FIND_REGEX_FORMAT, specialCharacter);
    }

    private String replacement(String specialCharacter) {
        return String.format(REPLACE_REGEX_FORMAT, specialCharacter);
    }

    public static String replaceAllEscape(String input) {
        input = SpecialCharacter.ESCAPE.replaceEscapeToReplacement(input); // ESCAPE를 제일 처음에 적용

        for(SpecialCharacter specialCharacter : SpecialCharacter.escapeCharactersExceptEscape())
            input = specialCharacter.replaceEscapeToReplacement(input);

        return input;
    }

    public String replaceEscapeToReplacement(String input) {
        Matcher matcher = escapeCharacterPattern.matcher(input);

        int start = 0;
        StringBuilder output = new StringBuilder();

        while(matcher.find()) {
            output.append(input.substring(start, matcher.start()));
            output.append(replacement);
            start = matcher.end();
        }

        output.append(input.substring(start, input.length()));

        return output.toString();
    }

    private static List<SpecialCharacter> escapeCharactersExceptEscape() {
        return Arrays.stream(SpecialCharacter.values())
                .filter(e->!e.equals(SpecialCharacter.ESCAPE))
                .toList();
    }

}
