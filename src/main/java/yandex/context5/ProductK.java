package yandex.context5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductK { // 4 Произведение К чисел из списка = M
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int k = scanner.nextInt();
        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        List<Integer> selectedIndices = findIndices(a, m, k);

        for (int index : selectedIndices) {
            System.out.print(index + 1 + " ");
        }
    }

    private static List<Integer> findIndices(int[] a, int m, int k) {
        List<Integer> indices = new ArrayList<>();
        boolean[] used = new boolean[a.length];

        // Проверка, если k = 1
        if (k == 1) {
            for (int i = 0; i < a.length; i++) {
                if (a[i] == m) {
                    indices.add(i);
                    return indices; // Найдено решение, выходим из функции
                }
            }
        } else {
            backtrack(a, m, k, 1, used, indices);
        }

        return indices;
    }

    private static boolean backtrack(int[] a, int m, int k, int currentProduct, boolean[] used, List<Integer> indices) {
        if (indices.size() == k && currentProduct == m) {
            return true;
        }

        if (indices.size() == k) {
            return false;
        }

        for (int i = 0; i < a.length; i++) {
            if (!used[i]) {
                used[i] = true;
                indices.add(i);
                if (backtrack(a, m, k, currentProduct * a[i], used, indices)) {
                    return true;
                }
                used[i] = false;
                indices.remove(indices.size() - 1);
            }
        }

        return false;
    }
}
