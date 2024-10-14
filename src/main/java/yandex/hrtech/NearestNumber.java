package yandex.hrtech;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class NearestNumber { // #2
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int len = Integer.parseInt(reader.readLine());
        int[] array = new int[len];
        String[] parts = reader.readLine().split(" ");
        for (int i = 0; i < len; i++) {
            array[i] = Integer.parseInt(parts[i]);
        }
        int x = Integer.parseInt(reader.readLine());

        Arrays.sort(array);
        int closest = array[0];
        int left = 0;
        int right = len - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            closest = array[mid];

            if (closest == x) {
                break;
            }
            if (closest < x) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }

        System.out.println(closest);

        reader.close();
        writer.close();
    }

}
