package yandex.hrtech;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class MinInPart_1 { // #11 - 1
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] parts = reader.readLine().split(" ");
        int n = Integer.parseInt(parts[0]);
        int k = Integer.parseInt(parts[1]);
        int[] nums = new int[n];
        parts = reader.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(parts[i]);
        }

        Queue<Integer> queue = new PriorityQueue<>();
        for(int i = 0; i < k; i++) {
            queue.add(nums[i]);
        }
        writer.write(String.valueOf(queue.peek()) + "\n");

        for(int i = k; i < n; i++) {
            queue.add(nums[i]);
            queue.remove(nums[i - k]);
            writer.write(String.valueOf(queue.peek()) + "\n");
        }

        reader.close();
        writer.close();
    }

}
