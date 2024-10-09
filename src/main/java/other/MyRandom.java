package other;

import java.util.Random;

public class MyRandom {
    public static void main(String[] args) throws Exception {
        new Random().ints(10, 0, 10)
                .forEach(System.out::println);
    }
}
