package yandex.context4;

import java.util.*;

public class FourSumNabor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int targetSum = scanner.nextInt();
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }

        Arrays.sort(numbers);
        Set<List<Integer>> uniqueNabors = new HashSet<>();

        for (int i = 0; i < n - 3; i++) {
            for (int j = i + 1; j < n - 2; j++) {
                int left = j + 1;
                int right = n - 1;

                while (left < right) {
                    int sum = numbers[i] + numbers[j] + numbers[left] + numbers[right];
                    if (sum == targetSum) {
                        List<Integer> currentNabor = Arrays.asList(numbers[i], numbers[j], numbers[left], numbers[right]);
                        uniqueNabors.add(currentNabor);
                        left++;
                        right--;
                    } else if (sum < targetSum) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }

        System.out.println(uniqueNabors.size());

        scanner.close();
    }
}

/**
 * Объяснение кода:
 *         1. Ввод данных: Программа читает количество чисел и целевое значение, а затем массив чисел.
 *         2. Использование множества: Для хранения уникальных наборов используется Set, что позволяет избежать дублирования.
 *         3. Сортировка массива: Массив сортируется для удобства поиска.
 *         4. Два вложенных цикла: Первый и второй циклы проходят по элементам массива.
 *         5. Два указателя: Используются указатели left и right для поиска оставшихся двух чисел, чтобы сумма четырех чисел равнялась целевому значению.
 *         6. Добавление уникальных наборов: Если сумма равна целевому значению, набор добавляется в множество.
 *         7. Вывод результата: В конце выводится количество уникальных наборов.
 */
