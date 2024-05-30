package yandex.context2;

import java.util.Scanner;

public class SubMatrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        scanner.nextLine();
        char[][] matrix = new char[n][m];
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            matrix[i] = line.toCharArray();
        }

        int count = 0;

        for (int up = 0; up < n; up++) {
            for (int down = up; down < n; down++) {
                for (int left = 0; left < m; left++) {
                    for (int right = left; right < m; right++) {
                        if (isIdealSubMatrix(matrix, up, down, left, right)) {
                            count++;
                        }
                    }
                }
            }
        }

        System.out.println(count);
    }

    private static boolean isIdealSubMatrix(char[][] matrix, int up, int down, int left, int right) {
        int size = (down - up + 1) * (right - left + 1);
        if (size % 2 != 0) {
            return false;
        }
        int halfSize = size / 2;
        int numZeros = 0;
        int numOnes = 0;

        for (int i = up; i <= down; i++) {
            for (int j = left; j <= right; j++) {
                if (matrix[i][j] == '0') {
                    if (numZeros == halfSize) {
                        return false;
                    }
                    numZeros++;
                } else {
                    if (numOnes == halfSize) {
                        return false;
                    }
                    numOnes++;
                }
            }
        }

        return numZeros == numOnes;
    }
}
