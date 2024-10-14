package yandex.hrtech;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class MaxMultiplication { // #4
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] parts = reader.readLine().split(" ");
        long[] numbers = new long[parts.length];
        for (int i = 0; i < parts.length; i++) {
            numbers[i] = Integer.parseInt(parts[i]);
        }

        Arrays.sort(numbers);
        if(numbers[0] * numbers[1] > numbers[numbers.length - 1] * numbers[parts.length - 2]) {
            System.out.println(numbers[0] + " " + numbers[1]);
        } else {
            System.out.println(numbers[numbers.length-2] + " " + numbers[numbers.length-1]);
        }

        reader.close();
        writer.close();
    }

}
