package utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class StreamUtils {

    // убрать повторы из тескта
    public static void DeleteRepeatedWords() {
        String text = "Ivan Petr Ivan Egor Egor Fedor";

        String modifiedText = Arrays.stream(text.split(" "))
                .collect(Collectors.toMap(i -> i, i -> 1, (a, b) -> a+b, LinkedHashMap::new))
//                .collect(LinkedHashMap<String, Integer>::new, (m, c) ->
//                {m.put(c, m.containsKey(c) ? (1 + m.get(c)) : 1);}, HashMap::putAll)
//                .collect(Collectors.groupingBy(x -> x, LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() == 1)
                .map(e -> e.getKey())
                .collect(Collectors.joining(" "));

        System.out.println(modifiedText); // => Petr Fedor

        String modifiedText1 = Arrays.stream(text.split(" "))
                .collect(LinkedHashSet<String>::new, (m, c) -> m.add(c), Set::addAll)
                // если порядок слов не важен, то можно просто .collect(Collectors.toSet())
                .stream().collect(Collectors.joining(" "));

        String modifiedText2 = Arrays.stream(text.split(" "))
                .reduce("", (result, word) -> result.contains(word) ?  result : result + " " + word);

        String modifiedText3 = Arrays.stream(text.split(" "))
                .distinct()
                .collect(Collectors.joining(" "));

        System.out.println(modifiedText1); // => Ivan Petr Egor Fedor
    }

    public static void makeAndSortRandomArray() {
        int rows = 3;
        int columns = 3;
        int[][] arr = new int[rows][columns];
        int min = 0;
        int max = 100;
        Random random = new Random();
        arr = Arrays.stream(arr)
                .map(row -> Arrays.stream(row)
                        .map(item -> random.nextInt(min, max))
                        .toArray())
                .toArray(int[][]::new);


        int[] counter = {0};
        int[][] sortedArr = Arrays.stream(arr)
                .flatMapToInt(Arrays::stream)
                .sorted()
                .collect(() -> new int[rows][columns],
                        (a, i) -> a[counter[0] / columns][counter[0]++ % columns] = i, (a, i) -> {});

        System.out.println(Arrays.deepToString(sortedArr));
        System.out.println(Arrays.deepToString(arr));
    }

}
