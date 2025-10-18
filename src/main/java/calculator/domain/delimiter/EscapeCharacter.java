package calculator.domain.delimiter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum EscapeCharacter {
    ESCAPE("(\\\\)", "\\\\"),
    ;

    private Pattern escapeCharacterPattern;
    private String replacement;

    EscapeCharacter(String escapeCharacter, String replacement) {
        escapeCharacterPattern = Pattern.compile(escapeCharacter);
        this.replacement = replacement;
    }

    public String replaceAllEscape(String input) {
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
