import static org.assertj.core.api.Assertions.assertThat;
import static utils.NumberUtils.sortNumberByDigits;

import ii.MyII;
import ii.MyII.Student;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import utils.NumberUtils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import utils.StringUtils;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class AppTest {
    @Mock
    StringUtils stringUtils;
    @Test
    void testMockito1() {
        Mockito.doReturn("!!!").when(stringUtils).reverseByRecurs(Mockito.anyString());
        String result = stringUtils.reverseByRecurs("123");
        assertThat(result).isEqualTo("!!!");
    }

    @Test
    void testMockito2() {
        NumberUtils numberUtils = Mockito.mock(NumberUtils.class);
        Mockito.when(numberUtils.sumOfDigits(123)).thenReturn(1);
        Mockito.when(numberUtils.sumOfDigits(12)).thenReturn(2);
        int result = numberUtils.sumOfDigits(123);
        assertThat(result).isEqualTo(1);
        result = numberUtils.sumOfDigits(12);
        assertThat(result).isEqualTo(2);
    }

    @Test
    void testMockitoStaticMethod() {
        try (MockedStatic<NumberUtils> theMock = Mockito.mockStatic(NumberUtils.class)) {
//            theMock.when(NumberUtils::count).thenReturn(5);
//            assertThat(NumberUtils.count()).isEqualTo(5);
//            theMock.when(NumberUtils::count).thenReturn(10);
//            assertThat(NumberUtils.count()).isEqualTo(10);

            theMock.when(() -> NumberUtils.sortNumberByDigits(123)).thenReturn(100);
            int n = NumberUtils.sortNumberByDigits(123);
            assertThat(n).isEqualTo(100);
        }

    }

    @Test
    void testSortNumberByDigits() {
        Integer result = sortNumberByDigits(132496758);
        Integer expected = 987654321;
        assertThat(expected).isEqualTo(result);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "132496758, 987654321",
            "123, 321",
            "4713, 7431"
    }, nullValues = {"null"})
    void testSortNumberByDigits_1(int number, int expected) {
        Integer result = sortNumberByDigits(number);
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

    @Test
    void testFilterStudentsByAge() {
        List<Student> students = List.of(Student.builder().name("A").age(20).build(), Student.builder().name("B").age(30).build(), Student.builder().name("C").age(40).build());
        List<Student> result = MyII.filterStudentsByAge(students, 30);
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("B");
    }

}
