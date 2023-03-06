package HexletQA;

import java.io.IOException;

public class RegExpressions {
    public static void main(String[] args) {
        String str = "This is a string\n\nwith empty lines";
        System.out.println(str);
        // => This is a string
        // =>
        // => with empty lines

        str = str.replaceAll("(?m)^\\s*$[\r\n]+", "");
        System.out.println(str);
        // => This is a string
        // => with empty lines

        String TEMPLATE = "Hello, %s!";
        String res = String.format(TEMPLATE, "Ivan");
        System.out.println(res);
    }
}
