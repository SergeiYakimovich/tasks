package yandex.hrtech;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HappyNumberSegments_1 { // #7 - 1
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        long k = scanner.nextLong();
        long[] numbers = new long[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextLong();
        }

        Map<Long, Integer> prefixSumCount = new HashMap<>();
        prefixSumCount.put(0L, 1); // Сумма 0 встречается 1 раз (перед началом)

        long currentSum = 0;
        int count = 0;

        for (int i = 0; i < n; i++) {
            currentSum += numbers[i];

            // Проверка, сколько раз встречалась сумма (currentSum - k)
            count += prefixSumCount.getOrDefault(currentSum - k, 0);

            // Обновляем количество текущей префиксной суммы
            prefixSumCount.put(currentSum, prefixSumCount.getOrDefault(currentSum, 0) + 1);
        }

        System.out.println(count);
    }
}
