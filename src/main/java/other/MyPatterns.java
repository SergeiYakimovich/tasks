package other;


import jakarta.validation.constraints.Pattern;

import java.util.regex.Matcher;

//    https://regex101.com/
public class MyPatterns {
    private static final java.util.regex.Pattern CYRILLIC_PATTERN = java.util.regex.Pattern.compile("[А-Я]{1}[а-я]+");

    // "12:34:56" -> "12:34"
    public static String trimSeconds(String time) {
        return time.trim().replaceAll("^(\\d{1,2}\\:\\d{1,2})(\\:\\d{1,2})?", "$1");
    }

    @Pattern(regexp = "^(?:VISIBLE|TRANSPARENT|NONE)$", message = "Переменная SECONDSTEPCLOSEBUTTONSTATE должна иметь одно из следующих значений: VISIBLE, TRANSPARENT, NONE.")
    private String secondStepCloseButtonState = "VISIBLE";

    public static void htmlMatcher(String html) {
        String regex = "<.*?>(.*?)<.*?>";
        Matcher matcher = java.util.regex.Pattern.compile(regex).matcher(html);
        while (matcher.find()) {
            System.out.println(matcher.group(1));
        }
    }
}
