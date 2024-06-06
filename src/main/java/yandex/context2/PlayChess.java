package yandex.context2;

import java.util.Arrays;
import java.util.Scanner;

public class PlayChess {
    public static void main(String[] args) {
        // получаем данные из стандартного ввода
        Scanner scanner = new Scanner(System.in);
        countResult(scanner);
        scanner.close();
    }

    public static long countResult(Scanner scanner) {
        // получаем данные
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int k = scanner.nextInt();
        int[] players = new int[n];
        for (int i = 0; i < n; i++) {
            players[i] = scanner.nextInt();
        }

        // сортируем по возрастанию и убираем игроков с большим рейтингом
        players = Arrays.stream(players)
                .sorted()
                .filter(i -> i < k)
                .toArray();
        n = players.length;

        // считаем количество возможных пар
        long result = 0;
        for (int i = 0; (i < n - 1) && (players[i] + players[i+1] <= k); i++) {
            int l = i;
            int r = n-1;
            boolean condition1, condition2;
            int middle;
            do {
                middle = (r + l) / 2;
                condition1 = canPlay(players[i], players[middle], m, k);
                condition2 = canPlay(players[i], players[middle + 1], m, k);
                if (condition1 && condition2) {
                    l = middle;
                }
                if (!condition1 && !condition2) {
                    r = middle;
                }
            } while ((!condition1 || condition2) && r-l > 1);
            if (condition1) {
                result += middle - i;
            }
            if( condition2 ) {
                result ++;
            }
        }

        return result;
    }
    private static boolean canPlay(int s1, int s2, int m, int k) {
        return (s2 - s1 <= m) && (s1 + s2 <= k);
    }

}
