package yandex.hrtech;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class MinPath { // #9
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(reader.readLine());
        int[][] graf = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] parts = reader.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                graf[i][j] = Integer.parseInt(parts[j]);
            }
        }
        String[] parts = reader.readLine().split(" ");
        int v1 = Integer.parseInt(parts[0]) - 1;
        int v2 = Integer.parseInt(parts[1]) - 1;

        int result = countPath(graf, n, v1, v2);
        writer.write(String.valueOf(result));

        reader.close();
        writer.close();
    }

    private static int countPath(int[][] graf, int n, int start, int end) {
        int[] distance = new int[n];
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;

        do {
            int current = queue.poll();
            if(current == end) return distance[current];

            for(int node=0; node < n; node++) {
                if(!visited[node] && graf[current][node] == 1) {
                    distance[node] = distance[current] + 1;
                    visited[node] = true;
                    queue.add(node);
                }
            }
        } while(!queue.isEmpty());

        return -1;
    }

}
