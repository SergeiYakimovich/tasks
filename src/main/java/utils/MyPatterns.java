package utils;

public class MyPatterns {

    // "12:34:56" -> "12:34"
    public static String trimSeconds(String time) {
        return time.trim().replaceAll("^(\\d{1,2}\\:\\d{1,2})(\\:\\d{1,2})?", "$1");
    }
}
