package yandex;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class YandexAlgoritms {

    class App { // задача Гистограмма

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


}
