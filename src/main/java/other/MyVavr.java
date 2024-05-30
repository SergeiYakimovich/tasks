package other;

import io.vavr.Function5;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.Tuple3;
import io.vavr.control.Try;

public class MyVavr {
    // https://www.baeldung.com/vavr
    public static void myTuple() {
        Tuple3<String, Integer, Double> java8 = Tuple.of("Java", 8, 1.8);
        String element1 = java8._1;
        int element2 = java8._2();
        double element3 = java8._3();

        System.out.println(element1);
        System.out.println(element2);
    }
    public static void myTry() {
        Try<Integer> result = Try.of(() -> 1 / 0);

        System.out.println(result.isFailure());
    }

    public static void myFunction5() {
        Function5<String, String, String, String, String, String> concat =
                (a, b, c, d, e) -> a + b + c + d + e;
        String finalString = concat.apply(
                "Hello ", "world", "! ", "Learn ", "Vavr");
        System.out.println(finalString);
    }
}
