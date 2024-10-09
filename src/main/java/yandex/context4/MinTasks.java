package yandex.context4;

import java.util.Scanner;

public class MinTasks {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int t = scanner.nextInt();
        int[] tasks = new int[n];
        for (int i = 0; i < n; i++) {
            tasks[i] = scanner.nextInt();
        }

        int startIndex = 0;
        int currentLoad = 0;
        for (int i = 0; i < t; i++) {
            currentLoad += tasks[i];
        }
        int minLoad = currentLoad;

        for (int i = t; i < n; i++) {
            currentLoad += tasks[i] - tasks[i - t];
            if (currentLoad < minLoad) {
                minLoad = currentLoad;
                startIndex = i - t + 1;
            }
        }

        System.out.println(startIndex + 1);

        scanner.close();
    }
}

/**
 *
 * 1. Ввод данных: Программа считывает количество моментов времени N и размер интервала T, а затем заполняет массив tasks, в который заносятся количества задач в каждый момент времени.
 * 2. Инициализация: Начальное значение суммы задач в первом интервале фиксированной длины T сохраняется в currentLoad и сравнивается с minLoad.
 * 3. Скользящее окно: Для каждого следующего момента времени программа обновляет currentLoad путем вычитания значения, выходящего из интервала, и добавления нового значения. Если текущая нагрузка меньше минимальной, обновляются minLoad и startIndex.
 * 4. Вывод результата: В конце программа выводит индекс (начиная с 1, поскольку индексация в Java начинается с 0).
 */
