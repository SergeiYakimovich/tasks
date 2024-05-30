package yandex.context2;

import java.util.Scanner;

public class ParseUrl {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputUrl = scanner.nextLine();

        // Extract protocol
        String[] urlParts = inputUrl.split("://|/|\\?");
        String protocol = urlParts[0];
        System.out.println("Proto: " + protocol);

        if (urlParts.length < 2) {
            return;
        }

        // Extract hostname
        String hostname = urlParts[1];
        System.out.println("Host: " + hostname);

        if (urlParts.length < 3) {
            return;
        }

        int lastPartBeginIndex = inputUrl.indexOf(hostname) + hostname.length() ;
        String lastPart = inputUrl.substring(lastPartBeginIndex);
        if (lastPart.charAt(0) != '?' && lastPart.charAt(1) != '?') {
            // Extract handle
            String handle;
            int handleBeginIndex = lastPartBeginIndex + 1;
            int questionBeginIndex = inputUrl.indexOf("?");

            if (questionBeginIndex > 0) {
                handle = inputUrl.substring(handleBeginIndex, questionBeginIndex);
            } else {
                handle = inputUrl.substring(handleBeginIndex);
            }
            if (handle.charAt(handle.length() - 1) == '/') {
                handle = handle.substring(0, handle.length() - 1);
            }
            System.out.println("Handle: " + handle);


            if (questionBeginIndex < 0) {
                return;
            }
        }

        // Extract parameters
        String[] params = inputUrl.split("\\?")[1].split("&");
        for (String param : params) {
            String[] keyValue = param.split("=");
            System.out.println(keyValue[0] + " -> " + keyValue[1]);
        }

        scanner.close();
    }
}
