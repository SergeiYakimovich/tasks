package utils;

import java.util.Arrays;

/**
 * задачки с массивами
 */
public class MyMatrixUtils {

    /**
     * сортировка матрицы
     */
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

    /**
     * поворот матрицы на 90 градусов в заданном направлении
     */
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

    /**
     * обход матрицы змейкой
     */
    public static void snakeMatrix() {
        int rows = 4;
        int columns = 5;
        int[][] arr = new int[rows][columns];

        for(int n = 0; n < rows*columns; n++) {
            int i = n / columns;
            int j = i % 2 == 0 ? n % columns : columns - 1 - n % columns;
            arr[i][j] = n;
        }
        System.out.println(Arrays.deepToString(arr));
        // => [[0, 1, 2, 3, 4], [9, 8, 7, 6, 5], [10, 11, 12, 13, 14], [19, 18, 17, 16, 15]]
    }

    /**
     * сортировка массива одним циклом
     */
    public static void bubbleSort(int[] arr) { // сортировка одним циклом
        boolean wasSwap = false;
        for(int i = 0; i < arr.length - 1; i++) {
            if(arr[i] > arr[i + 1]) { // меняем местами элементы
                int temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
                wasSwap = true;
            }
            if(i == arr.length - 2) { // если дошли до конца массива
                if(wasSwap) { // и были изменения данных
                    i = -1; // то меняем счетчик для возврата в начало цикла
                    wasSwap = false;
                } else { // если изменений не было
                    return; // то сортировка закончена
                }
            }
        }
    }

    /**
     * переместить нули в конец массива
     */
    public static void zeroToEnd() {
        int[] nums = {1,0,0,2,0,3,0,-4};
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if(i != j) {
                    int temp = nums[j];
                    nums[j] = nums[i];
                    nums[i] = temp;
                }
                j++;
            }
        }
        System.out.println(Arrays.toString(nums)); // => [1, 2, 3, -4, 0, 0, 0, 0]
    }

}
