package yandex.context2;

import java.util.Scanner;

public class SubMatrix1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        scanner.nextLine();
        int[][] matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            for (int j = 0; j < m; j++) {
                matrix[i][j] = line.charAt(j) == '0' ? -1 : 1;
            }
        }

        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + matrix[i-1][j-1];
            }
        }

        int result = 0;

        for (int up = 1; up <=n; up++) {
            for (int down = up; down <= n; down++) {
                for (int left = 1; left <= m; left++) {
                    for (int right = left; right <= m; right++) {
                        if(idIdealSubMatrix(dp, up, left, down,right)) {
                            result++;
                        }
                    }
                }
            }
        }

        System.out.println(result);
    }

    private static boolean idIdealSubMatrix(int[][] dp, int up, int left, int down, int right) {
        return (dp[down][right] - dp[up - 1][right] - dp[down][left - 1] + dp[up - 1][left - 1]) == 0;
    }
}
