package yandex.context3;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class MusicLabel {
    private static Map<String, Map<String, List<String>>> userMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(Paths.get("src/main/resources/music_label.txt"));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] s = line.split(" ");
            String command = s[0];
            String user = s[1];
            String mode = s[2];
            String path = s[3];

            switch (command) {
                case "grant":
                    grant(user, mode, path);
                    break;
                case "block":
                    block(user, mode, path);
                    break;
                case "check":
                    check(user, mode, path);
                    break;
            }
        }
        scanner.close();
    }

    private static void check(String user, String mode, String path) {
        Map<String, List<String>> modeMap = userMap.get(user);
        if (modeMap == null) {
            System.out.println("forbidden");
        } else {
            List<String> pathes = modeMap.get(mode);
            if (pathes == null) {
                System.out.println("forbidden");
            } else {
                pathes.stream()
                        .filter(path::startsWith)
                        .findAny()
                        .ifPresentOrElse(p -> System.out.println("allowed"), () -> System.out.println("forbidden"));
            }
        }
    }

    private static void block(String user, String mode, String path) {
        Map<String, List<String>> modeMap = userMap.get(user);
        if (modeMap != null) {
            List<String> pathes = modeMap.get(mode);
            if (pathes != null) {
                List<String> blockedPathes = pathes.stream()
                        .filter(p -> p.startsWith(path))
                        .toList();
                pathes.removeAll(blockedPathes);
            }
        }
        System.out.println("done");
    }

    private static void grant(String user, String mode, String path) {
        Map<String, List<String>> modeMap = userMap.get(user);
        if (modeMap == null) {
            modeMap = new HashMap<>();
            modeMap.put(mode, Arrays.asList(path));
            userMap.put(user, modeMap);
        } else {
            List<String> pathes = modeMap.get(mode);
            if (pathes == null) {
                modeMap.put(mode, Arrays.asList(path));
            } else {
                pathes.add(path);
            }
        }
        System.out.println("done");
    }

}
