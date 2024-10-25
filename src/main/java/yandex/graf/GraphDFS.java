package yandex.graf;

import java.util.*;

public class GraphDFS { // Обход в глубину (Depth-First Search, DFS)
    private Map<Integer, List<Integer>> adjList;

    public GraphDFS() {
        adjList = new HashMap<>();
    }

    // Добавляем ребро в граф
    public void addEdge(int v, int w) {
        adjList.putIfAbsent(v, new ArrayList<>());
        adjList.putIfAbsent(w, new ArrayList<>());
        adjList.get(v).add(w);
    }

    // Рекурсивный обход в глубину
    private void dfs(int v, Set<Integer> visited) {
        visited.add(v);
        System.out.print(v + " ");

        for (int neighbor : adjList.getOrDefault(v, new ArrayList<>())) {
            if (!visited.contains(neighbor)) {
                dfs(neighbor, visited);
            }
        }
    }

    // Метод для запуска DFS
    public void startDFS(int start) {
        Set<Integer> visited = new HashSet<>();
        dfs(start, visited);
    }

    public static void main(String[] args) {
        GraphDFS graph = new GraphDFS();
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);
        graph.addEdge(2, 6);

        System.out.println("DFS начиная с вершины 0:");
        graph.startDFS(0);
    }
}

//    Алгоритм DFS:
//        - Сначала вершина посещается, и затем рекурсивно обрабатываются все её соседние вершины,
//        которые ещё не были посещены.
