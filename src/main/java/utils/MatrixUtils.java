package utils;

import java.util.Arrays;

public class MatrixUtils {

    public static void sortMatrix() {
        int rows = 3;
        int columns = 3;
        int[][] arr = {{5,1,3}, {2,0,8}, {10,4,7}}; // исходный массив 3 x 3

        int[] counter = {0};
        int[][] sortedArr = Arrays.stream(arr)
                .flatMapToInt(Arrays::stream)
                .sorted()
                .collect(() -> new int[rows][columns],
                        (a, i) -> a[counter[0] / columns][counter[0]++ % columns] = i, (a, i) -> {});

        System.out.println(Arrays.deepToString(sortedArr)); // => [[0, 1, 2], [3, 4, 5], [7, 8, 10]]
    }

    enum RotateType {
        LEFT,
        RIGHT
    }
    public static int[][] rotateMatrix(int[][] matrix, RotateType type) {
        var rows = matrix[0].length;
        var columns = matrix.length;
        var result = new int[rows][columns];
        for (var i = 0; i < rows; i++) {
            for (var j = 0; j < columns; j++) {
                result[i][j] = (type == RotateType.LEFT) ? matrix[j][rows - 1 - i] : matrix[columns - 1 - j][i];
            }
        }
        return result;
    }
}
