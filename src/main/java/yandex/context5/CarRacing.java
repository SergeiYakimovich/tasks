package yandex.context5;

import java.util.Scanner;

public class CarRacing { // 1
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Считывание входных данных
        int n = scanner.nextInt(); // количество автомобилей
        int t = scanner.nextInt(); // время
        int s = scanner.nextInt(); // длина трассы

        int v1 = scanner.nextInt();
        long result = 0;

        for (int i = 1; i < n; i++) {
            int vi = scanner.nextInt();
            double x = (double) (v1 - vi) * t / s;
            if(x > 1.0) {
                result += Math.floor(x - 1);
            }
        }

        System.out.println(result);
        scanner.close();
    }
}
