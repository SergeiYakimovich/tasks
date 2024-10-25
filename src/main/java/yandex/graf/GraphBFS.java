package yandex.graf;

import java.util.*;

public class GraphBFS { // Обход в ширину (Breadth-First Search, BFS)
    private Map<Integer, List<Integer>> adjList;

    public GraphBFS() {
        adjList = new HashMap<>();
    }

    // Добавляем ребро в граф
    public void addEdge(int v, int w) {
        adjList.putIfAbsent(v, new ArrayList<>());
        adjList.putIfAbsent(w, new ArrayList<>());
        adjList.get(v).add(w);
    }

    // Метод для запуска BFS
    public void startBFS(int start) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        visited.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            int v = queue.poll();
            System.out.print(v + " ");

            for (int neighbor : adjList.getOrDefault(v, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }

    public static void main(String[] args) {
        GraphBFS graph = new GraphBFS();
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);
        graph.addEdge(2, 6);

        System.out.println("\nBFS начиная с вершины 0:");
        graph.startBFS(0);
    }
}

//    Алгоритм BFS:
//        - Используется очередь для обработки вершин. Сначала добавляется начальная вершина,
//        затем обрабатываются все её соседи. Соседние вершины, которые ещё не были посещены, добавляются в очередь.
