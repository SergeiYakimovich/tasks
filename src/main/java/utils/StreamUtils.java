package utils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * задачки со стримами
 */
public class StreamUtils {

    /**
     * убрать повторы из тескта - 4 решения
     */
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

    /**
     * создание случайного массива и его сортировка
     */
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

    public static List<String> findTheMostTalkingAuthor(List<String> records, Integer numToView) {
//        List<String> list = List.of("B:1 2 3 4 5", "B:1 2 3", "C: 1  2 3", "A :1 2 3", "A:1");
        if (records == null && records.isEmpty()) {
            throw new IllegalArgumentException("Records null or empty");
        }
        return records.stream()
                .map(record -> record.split(":"))
                .collect(Collectors.toMap(key -> key[0].trim(),
//                        value -> new StringTokenizer(value[1]).countTokens(),
                        value -> Arrays.stream(value[1].split(" "))
                                .map(String::trim)
                                .filter(x -> x.length() > 0)
                                .count(),
                        (a, b) -> a + b))
                .entrySet()
                .stream()
                .sorted((x1,x2) -> Long.compare(x2.getValue(),x1.getValue()))
                .limit(numToView)
                .peek(System.out::println)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
    public static List<String> findTheMostTalking2(List<String> records, Integer numToView) {
        return records.stream()
                .map(record -> record.split(":"))
                .collect(Collectors.toMap(
                        key -> key[0].trim(),
                        value -> value[1].trim().split(" ").length,
                        Integer::sum))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(numToView)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public static List<String> findTheMostTalking1(List<String> records, Integer numToView) {
        Map<String, Integer> map = new HashMap<>();
        records.forEach(record -> {
            String[] arr = record.split(":");
            String name = arr[0].trim();
            int wordsInRecord = arr[1].trim().split(" ").length;
            map.merge(name, wordsInRecord, Integer::sum);
        });
        return map.entrySet().stream()
                .sorted((x1,x2) -> x2.getValue().compareTo(x1.getValue()))
                .limit(numToView)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    /**
     * вычисление выражения (только + и *)
     */
    public static int calculate(String expression) {
        return Arrays.stream(expression.split("\\+"))
                .map(str -> Arrays.stream(str.split("\\*"))
                        .mapToInt(Integer::parseInt)
                        .reduce(1, (a, b) -> a * b)
                )
                .mapToInt(i -> i)
                .sum();
    }
}
