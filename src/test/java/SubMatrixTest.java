import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import yandex.context2.SubMatrix;

import java.util.Scanner;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubMatrixTest {

    @ParameterizedTest
    @MethodSource("provide")
    public void test1(String input, long expected) {
        Scanner scanner = new Scanner(input);
        long result = SubMatrix.countResult(scanner);
        System.out.println(result);
        assertEquals(expected, result);
    }

    private static Stream<Arguments> provide() {
        return Stream.of(
                Arguments.of("1  1\n" +
                        "0", 0),
                Arguments.of("1  2\n" +
                        "00", 0),
                Arguments.of("1  2\n" +
                        "11", 0),
                Arguments.of("1  2\n" +
                        "01", 1),
                Arguments.of("1  2\n" +
                        "10", 1),
                Arguments.of("2 1\n" +
                        "0\n" +
                        "0", 0),
                Arguments.of("2 1\n" +
                        "1\n" +
                        "1", 0),
                Arguments.of("2 1\n" +
                        "1\n" +
                        "0", 1),
                Arguments.of("2 1\n" +
                        "0\n" +
                        "1", 1),
                Arguments.of("2  2\n" +
                        "00\n" +
                        "00", 0),
                Arguments.of("2  2\n" +
                        "11\n" +
                        "11", 0),
                Arguments.of("2  2\n" +
                        "01\n" +
                        "00", 2),
                Arguments.of("2  2\n" +
                        "10\n" +
                        "00", 2),
                Arguments.of("2  2\n" +
                        "00\n" +
                        "10", 2),
                Arguments.of("2  2\n" +
                        "00\n" +
                        "01", 2),
                Arguments.of("2  2\n" +
                        "01\n" +
                        "10", 5),
                Arguments.of("2  2\n" +
                        "10\n" +
                        "01", 5),
                Arguments.of("1 3\n" +
                        "000", 0),
                Arguments.of("1 3\n" +
                        "111", 0),
                Arguments.of("1 3\n" +
                        "001", 1),
                Arguments.of("1 3\n" +
                        "100", 1),
                Arguments.of("1 3\n" +
                        "010", 2),
                Arguments.of("2 3\n" +
                        "000\n" +
                        "000", 0),
                Arguments.of("2 3\n" +
                        "100\n" +
                        "000", 2),
                Arguments.of("2 3\n" +
                        "010\n" +
                        "000", 3),
                Arguments.of("2 3\n" +
                        "001\n" +
                        "000", 2),
                Arguments.of("2 3\n" +
                        "000\n" +
                        "100", 2),
                Arguments.of("2 3\n" +
                        "000\n" +
                        "010", 3),
                Arguments.of("2 3\n" +
                        "000\n" +
                        "001", 2),

                Arguments.of("2 3\n" +
                        "110\n" +
                        "000", 4),
                Arguments.of("2 3\n" +
                        "101\n" +
                        "000", 4),
                Arguments.of("2 3\n" +
                        "011\n" +
                        "000", 4),
                Arguments.of("2 3\n" +
                        "000\n" +
                        "110", 4),
                Arguments.of("2 3\n" +
                        "000\n" +
                        "101", 4),
                Arguments.of("2 3\n" +
                        "000\n" +
                        "011", 4),

                Arguments.of("2 3\n" +
                        "110\n" +
                        "100", 4),
                Arguments.of("2 3\n" +
                        "110\n" +
                        "010", 6),
                Arguments.of("2 3\n" +
                        "110\n" +
                        "001", 8),

                Arguments.of("2 3\n" +
                        "101\n" +
                        "000", 4),
                Arguments.of("2 3\n" +
                        "101\n" +
                        "100", 6),
                Arguments.of("2 3\n" +
                        "101\n" +
                        "010", 10),
                Arguments.of("2 3\n" +
                        "111\n" +
                        "000", 6),
                Arguments.of("2 3\n" +
                        "111\n" +
                        "001", 4),
                Arguments.of("2 3\n" +
                        "111\n" +
                        "011", 2),
                Arguments.of("2 3\n" +
                        "111\n" +
                        "101", 3),
                Arguments.of("2 3\n" +
                        "111\n" +
                        "110", 2),
                Arguments.of("3 1\n" +
                        "0\n" +
                        "0\n" +
                        "0", 0),
                Arguments.of("3 1\n" +
                        "1\n" +
                        "0\n" +
                        "0", 1),
                Arguments.of("3 1\n" +
                        "0\n" +
                        "0\n" +
                        "1", 1),
                Arguments.of("3 1\n" +
                        "0\n" +
                        "1\n" +
                        "0", 2),
                Arguments.of("3 1\n" +
                        "1\n" +
                        "0\n" +
                        "1", 2),
                Arguments.of("3 1\n" +
                        "1\n" +
                        "1\n" +
                        "0", 1),
                Arguments.of("3 1\n" +
                        "0\n" +
                        "1\n" +
                        "1", 1),
//                Arguments.of("", 3),
                Arguments.of("1  1\n" +
                        "1", 0)
        );
    }
}
