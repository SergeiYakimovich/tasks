import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import yandex.context2.PlayChess;

import java.util.Scanner;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayChessTest {

    @ParameterizedTest
    @MethodSource("provide")
    public void test1(String input, long expected) {
        Scanner scanner = new Scanner(input);
        long result = PlayChess.countResult(scanner);
        System.out.println(result);
        assertEquals(expected, result);
    }

    private static Stream<Arguments> provide() {
        return Stream.of(
                Arguments.of("4 1 5\n" +
                        "1 3 2 3", 3),
                Arguments.of("9 1 5\n" +
                        "1 1 2 3 2 2 1 3 3", 24),
                Arguments.of("10 4 15\n" +
                        "1 2 3 4 5 6 7 8 9 10", 24),
                Arguments.of("1 4 15\n" +
                        "1", 0),
                Arguments.of("1 4 15\n" +
                        "16", 0),
                Arguments.of("2 4 15\n" +
                        "1 2", 1),
                Arguments.of("2 4 15\n" +
                        "1 6", 0),
                Arguments.of("2 4 15\n" +
                        "1 16", 0),
                Arguments.of("4 4 15\n" +
                        "3 3 3 3", 6),
                Arguments.of("4 4 5\n" +
                        "3 3 3 3", 0),
                Arguments.of("4 4 15\n" +
                        "3 3 13 3", 3)
        );
    }

}
