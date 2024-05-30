package other;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class MyQueue {
    public static void main(String[] args) {
        Deque<Integer> deque = new ArrayDeque<>();
//        Deque<Integer> queue = new LinkedList<>();

        System.out.println("!!! 1");
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        System.out.println(deque);

        while (!deque.isEmpty()) {
            System.out.print(deque.pollFirst() + " ");
        }

        System.out.println("\n\n!!! 2");
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        System.out.println(deque);

        while (!deque.isEmpty()) {
            System.out.print(deque.pollLast() + " ");
        }

        System.out.println("\n\n!!! 3 быстрее");
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        System.out.println(deque);

        while (!deque.isEmpty()) {
            System.out.print(deque.pollLast() + " ");
        }

        System.out.println("\n\n!!! 4");
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        System.out.println(deque);

        while (!deque.isEmpty()) {
            System.out.print(deque.pollFirst() + " ");
        }
        System.out.println();
    }
}
