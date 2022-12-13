import static org.assertj.core.api.Assertions.assertThat;

import utils.NumberUtils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;

class AppTest {

    @Test
    void testSortNumberByDigits() {
        Integer result = NumberUtils.sortNumberByDigits(132496758);
        Integer expected = 987654321;
        assertThat(expected).isEqualTo(result);
    }

    @ParameterizedTest
    @CsvSource({
            "132496758, 987654321",
            "123, 321",
            "4713, 7431"
    })
    void testSortNumberByDigits_1(int number, int expected) {
        Integer result = NumberUtils.sortNumberByDigits(number);
        assertThat(expected).isEqualTo(result);
    }

    @ParameterizedTest
    @CsvSource({
            "3, 2, 5, 2",
            "30, 20, 10, 10",
            "1, 12, 7, 1"
    })
    void testSortMinOfNumbers(int a, int b, int c, int expected) {
        int result = NumberUtils.minOfNumbers(a, b, c);
        assertThat(expected).isEqualTo(result);
    }

}
