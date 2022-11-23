package utils;

import java.util.Arrays;
import java.util.Random;

public class StreamUtils {

    public static void sortRandomArray() {
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
