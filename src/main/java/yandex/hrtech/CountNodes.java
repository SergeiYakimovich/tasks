package yandex.hrtech;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;
import java.util.stream.*;

public class CountNodes { // #8
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] parts = reader.readLine().split(" ");
        int n = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);
        List<Integer>[] list = new List[n];
        for (int i = 0; i < n; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            parts = reader.readLine().split(" ");
            int v1 = Integer.parseInt(parts[0]) - 1;
            int v2 = Integer.parseInt(parts[1]) - 1;
            if(v1 == v2 ) continue;
            list[v1].add(v2);
            list[v2].add(v1);
        }

        Set<Integer> resultSet = new HashSet<>();
        resultSet.add(0);
        addNodes(0, list, resultSet);
        writer.write(resultSet.size() + "\n");
        String result = resultSet.stream()
                .sorted()
                .map(x -> x+1)
                .map(String::valueOf)
                .collect(Collectors.joining(" "));
        writer.write(result);

        reader.close();
        writer.close();
    }

    private static void addNodes(int num, List<Integer>[] list, Set<Integer> resultSet) {
        for(int i : list[num]) {
            if(!resultSet.contains(i)) {
                resultSet.add(i);
                addNodes(i, list, resultSet);
            }
        }
    }

}
