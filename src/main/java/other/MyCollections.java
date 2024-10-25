package other;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyCollections {
    public static void main(String[] args) throws Exception {
        Deque<Integer> deque = new ArrayDeque<>();
        deque.addFirst(1);
        deque.addFirst(2);
        System.out.println(deque);

        deque.addLast(3);
        System.out.println(deque);

        deque.pollFirst();
        System.out.println(deque);

        deque.pollLast();
        System.out.println(deque);

        deque.push(4);
        System.out.println(deque);

        deque.pop();
        System.out.println(deque);

        Map<String, List<String>> map = new HashMap<>();
        map.merge("key1", new  ArrayList<>(List.of("value1")), (x1,x2) -> {x1.addAll(x2); return x1;});
        map.merge("key2", new  ArrayList<>(List.of("value2")), (x1, x2) -> Stream.concat(x1.stream(), x2.stream()).collect(Collectors.toList()));
        map.merge("key1", new  ArrayList<>(List.of("value3")), MyCollections::add);
        map.merge("key2", new  ArrayList<>(List.of("value4")), (x1, x2) -> Stream.concat(x1.stream(), x2.stream()).collect(Collectors.toList()));
        System.out.println(map);

    }

    public static List<String> add(List<String> list1, List<String> list2) {
        List<String> result = new ArrayList<>();
        result.addAll(list1);
        result.addAll(list2);
        return result;
    }
}
