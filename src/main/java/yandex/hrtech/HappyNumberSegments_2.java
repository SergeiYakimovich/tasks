package yandex.hrtech;

import java.util.Scanner;

public class HappyNumberSegments_2 { // #7 - 2
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        long k = scanner.nextLong();
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }

        int left = 0;
        int right = 0;
        int sum = 0;
        int count = 0;
        while(right < n) {
            sum += numbers[right];
            if(sum > k) {
                do {
                    sum -= numbers[left];
                    left++;
                } while(sum > k);
            }
            if(sum == k) {
                count++;
            }
            right++;
        }

        System.out.println(count);
    }
}
