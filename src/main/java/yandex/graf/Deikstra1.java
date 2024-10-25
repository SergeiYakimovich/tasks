package yandex.graf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deikstra1 {
    public static List<Integer> dijkstra(int[][] graph, int start, int end) {
        int n = graph.length;
        int[] distance = new int[n]; // Расстояния от стартовой вершины
        boolean[] visited = new boolean[n]; // Вершины, посещенные алгоритмом
        Arrays.fill(distance, Integer.MAX_VALUE);

        distance[start] = 0;

        for (int i = 0; i < n; i++) {
            int nextMin = findMinDistance(distance, visited);
            visited[nextMin] = true;

            for (int v = 0; v < n; v++) {
                if (graph[nextMin][v] != 0 && !visited[v] && distance[nextMin] + graph[nextMin][v] < distance[v]) {
                    distance[v] = distance[nextMin] + graph[nextMin][v];
                }
            }
        }

        System.out.printf("Длина %s - %s = %s%n", start, end, distance[end]);

        // Реконструкция пути
        List<Integer> path = new ArrayList<>(); // Путь от start до end
        int current = end;
        while (current != start) {
            path.add(current);
            for (int i = 0; i < n; i++) {
                if (graph[i][current] != 0 && distance[i] + graph[i][current] == distance[current]) {
                    current = i;
                    break;
                }
            }
        }
        path.add(start);
        Collections.reverse(path);

        return path;
    }
    private static int findMinDistance(int[] distance, boolean[] visited) {
        int minDistance = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int i = 0; i < distance.length; i++) {
            if (!visited[i] && distance[i] < minDistance) {
                minDistance = distance[i];
                minIndex = i;
            }
        }

        return minIndex;
    }

    public static void main(String[] args) {
        int[][] graph = new int[5][5];
        graph[0][1] = 10;
        graph[0][2] = 3;
        graph[1][2] = 1;
        graph[1][3] = 2;
        graph[2][1] = 4;
        graph[2][3] = 8;
        graph[2][4] = 2;
        graph[3][4] = 7;
        graph[4][3] = 9;

        for (int i = 0; i < 5; i++) {
            System.out.printf("Путь %s - %s = %s%n", 0, i, dijkstra(graph, 0, i));
        }

    }
}
