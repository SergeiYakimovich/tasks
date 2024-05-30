package yandex.context2;

import java.util.Arrays;
import java.util.Scanner;

public class PlayChess1 {

    public static void main(String[] args) {

        // получаем данные из стандартного ввода
        Scanner scanner = new Scanner(System.in);
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
        int result = 0;
        int prevElement = 0;
        int prevCount = 0;
        for (int i = 0; (i < n - 1) && (players[i] <= k/2); i++) {
            if (players[i] == prevElement) {
                if (prevCount > 0) {
                    prevCount--;
                    result += prevCount;
                }
            } else {
                int currCount = 0;
                for (int j = i + 1; j < n; j++) {
                    if (canPlay(players[i], players[j], m, k)) {
                        currCount++; // игра возможна
                    } else {
                        break; // игра невозможна для всех >=j
                    }
                }
                prevElement = players[i];
                prevCount = currCount;
                result += currCount;
            }
        }

        System.out.println(result);

        scanner.close();
    }

    private static boolean canPlay(int i, int j, int m, int k) {

        return (j - i <= m) && (i + j <= k);
    }
}
