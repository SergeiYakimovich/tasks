package yandex.graf;

import java.util.*;

public class Deikstra2 { // Алгоритм Дейкстры нахождения оптимального пути в графе
    private final int size; // Количество вершин
    private final Map<Integer, List<Edge>> edgeMap; // Список смежности

    // Конструктор графа
    public Deikstra2(int size) {
        this.size = size;
        edgeMap = new HashMap<>();
        for (int i = 0; i < size; i++) {
            edgeMap.put(i, new ArrayList<>());
        }
    }

    // Метод для добавления ребра в граф
    public void addEdge(int source, int destination, int weight) {
        edgeMap.get(source).add(new Edge(destination, weight));
//        edgeMap.get(destination).add(new Edge(source, weight)); // Для неориентированного графа
    }

    // Метод для выполнения алгоритма Дейкстры
    public int[] dijkstra(int start) {
        int[] distances = new int[size];
        Arrays.fill(distances, Integer.MAX_VALUE);

        boolean[] visited = new boolean[size];
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(size, Comparator.comparingInt(edge -> edge.cost));

        distances[start] = 0;
        priorityQueue.add(new Edge(start, 0));

        while (!priorityQueue.isEmpty()) {
            int vertex = priorityQueue.poll().to;
            visited[vertex] = true;

            for (Edge neighbor : edgeMap.get(vertex)) {
                if (!visited[neighbor.to]) {
                    int newDist = distances[vertex] + neighbor.cost;
                    if (newDist < distances[neighbor.to]) {
                        distances[neighbor.to] = newDist;
                        priorityQueue.add(new Edge(neighbor.to, newDist));
                    }
                }
            }
        }

        for(int end = 0; end < size; end++) {
            List<Integer> path = new ArrayList<>(); // Путь от start до end
            int current = end;
            while (current != start) {
                path.add(current);
                for (int i = 0; i < size; i++) {
                    int finalCurrent = current;
                    Optional<Edge> edge = edgeMap.get(i).stream()
                            .filter(x -> x.to == finalCurrent)
                            .findFirst();
                    if (edge.isPresent() && distances[i] + edge.get().cost == distances[current]) {
                        current = i;
                        break;
                    }
                }
            }
            path.add(start);
            Collections.reverse(path);
            System.out.printf("Путь %s - %s = %s%n", start, end, path);
        }


        return distances;
    }

    // Внутренний класс для представления ребра
    private static class Edge {
        int to;
        int cost;
        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    // Пример использования
    public static void main(String[] args) {
        Deikstra2 graph = new Deikstra2(5);
        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 2, 3);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 2);
        graph.addEdge(2, 1, 4);
        graph.addEdge(2, 3, 8);
        graph.addEdge(2, 4, 2);
        graph.addEdge(3, 4, 7);
        graph.addEdge(4, 3, 9);

        int[] distances = graph.dijkstra(0);
        for (int i = 0; i < distances.length; i++) {
            System.out.printf("Длина %s - %s = %s%n", 0, i, distances[i]);
        }
    }
}
