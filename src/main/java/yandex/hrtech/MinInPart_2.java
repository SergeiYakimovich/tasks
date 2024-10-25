package yandex.hrtech;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class MinInPart_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        // Считываем n и k
        String[] input = reader.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);

        // Считываем последовательность чисел
        String[] sequenceInput = reader.readLine().split(" ");
        int[] sequence = new int[n];
        for (int i = 0; i < n; i++) {
            sequence[i] = Integer.parseInt(sequenceInput[i]);
        }

        Deque<Integer> deque = new ArrayDeque<>();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < n; i++) {
            // Удаляем элементы, которые вышли за пределы текущего окна
            if (!deque.isEmpty() && deque.peek() <= i - k) {
                deque.poll();
            }

            // Удаляем все элементы, которые больше текущего элемента
            // Так как они не могут быть минимальными в текущем окне
            while (!deque.isEmpty() && sequence[deque.peekLast()] > sequence[i]) {
                deque.pollLast();
            }

            // Добавляем текущий элемент в очередь
            deque.offer(i);

            // Если мы уже достигли размера окна, добавляем ответ
            if (i >= k - 1) {
                result.append(sequence[deque.peek()]).append("\n");
            }
        }

        // Вывод результата
        writer.write(result.toString());

        reader.close();
        writer.close();
    }
}

//    Мы используем ArrayDeque для хранения индексов элементов последовательности.
//        - В цикле проходим по каждому элементу и выполняем следующие действия:
//        - Удаляем индексы из очереди, которые выходят за пределы текущего окна.
//        - Удаляем из очереди индексы элементов, которые больше текущего элемента, так как они не могут быть минимумами.
//        - Добавляем индекс текущего элемента в очередь.
//        - Если достигли окна размером k, добавляем минимум (элемент по индексу, находящемуся в начале очереди) в результат.