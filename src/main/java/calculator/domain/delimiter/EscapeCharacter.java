package calculator.domain.delimiter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum EscapeCharacter {
    DOT("(\\.)", "\\."),
    ESCAPE("(\\\\)", "\\\\"),
    ;

    private Pattern escapeCharacterPattern;
    private String replacement;

    EscapeCharacter(String escapeCharacter, String replacement) {
        escapeCharacterPattern = Pattern.compile(escapeCharacter);
        this.replacement = replacement;
    }

    public static String replaceAllEscape(String input) {
        input = EscapeCharacter.ESCAPE.replaceEscapeToReplacement(input);
        input = EscapeCharacter.DOT.replaceEscapeToReplacement(input);
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

}
