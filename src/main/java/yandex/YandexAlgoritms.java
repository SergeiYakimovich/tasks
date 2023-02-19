package yandex;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class YandexAlgoritms {

    class App1 { // задача Гистограмма

        public static void main(String[] args) throws IOException {
            String text = Files.readString(Paths.get("input.txt"));

            text = text.replaceAll(" ", "")
                    .replaceAll("\\n", "").replaceAll("\\r", "");

            Map<String, Long> map = Arrays.stream(text.split(""))
                    .collect(Collectors.groupingBy(x -> x, TreeMap::new, Collectors.counting()));

            long max = map.values().stream()
                    .max((x1, x2) -> x1.compareTo(x2))
                    .get();

            StringBuilder builder = new StringBuilder();
            for(long  i = max; i > 0; i--) {
                for(Map.Entry<String, Long> item : map.entrySet()) {
                    builder.append(item.getValue() >= i ? "#" : " ");
                }
                builder.append("\n");
            }

            String str = map.keySet().stream()
                    .collect(Collectors.joining(""));
            builder.append(str);

            System.out.println(builder.toString());
        }

    }

    class App2 { // задача красивая строка
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            int n = scanner.nextInt();
            char[] text = scanner.next().toCharArray();

            if(n >= text.length) {
                System.out.println(text.length);
                return;
            }

            int max = 1;
            for(int i = 0; (i < text.length - n) && (max < text.length - i); i++) {
                int newMax = 1;
                char newChar = text[i];
                int j = 1;
                int zamena = 1;
                while((i+j) < text.length &&
                        (zamena <= n || text[i + j] == newChar)) {
                    if(text[i + j] != newChar) {
                        zamena++;
                    }
                    newMax++;
                    j++;
                }

                max = max > newMax ? max : newMax;
            }
            System.out.println(max);
        }

    }


}
