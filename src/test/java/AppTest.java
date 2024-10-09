import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static utils.MyNumberUtils.sortNumberByDigits;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.provider.NullSource;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import utils.MyNumberUtils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import utils.MyStringUtils;

@ExtendWith(MockitoExtension.class)
class AppTest {
    @Mock
    MyStringUtils stringUtils;
    @Captor
    ArgumentCaptor<String> captor;
    @Test
    void testMockito1() {
        doReturn("!!!").when(stringUtils).reverseByRecurs(Mockito.anyString());

        String result = stringUtils.reverseByRecurs("123");

        assertThat(result).isEqualTo("!!!");
        verify(stringUtils, times(1)).reverseByRecurs(captor.capture());
        String value = captor.getValue();
        assertThat(value).isEqualTo("123");
    }

    @Test
    void testMockito2() {
        MyNumberUtils numberUtils = Mockito.mock(MyNumberUtils.class);
        Mockito.when(numberUtils.sumOfDigits(123)).thenReturn(1);
        Mockito.when(numberUtils.sumOfDigits(12)).thenReturn(2);
        int result = numberUtils.sumOfDigits(123);
        assertThat(result).isEqualTo(1);
        result = numberUtils.sumOfDigits(12);
        assertThat(result).isEqualTo(2);
    }

    @Test
    void testMockitoStaticMethod() {
        try (MockedStatic<MyNumberUtils> theMock = Mockito.mockStatic(MyNumberUtils.class)) {
//            theMock.when(NumberUtils::count).thenReturn(5);
//            assertThat(NumberUtils.count()).isEqualTo(5);
//            theMock.when(NumberUtils::count).thenReturn(10);
//            assertThat(NumberUtils.count()).isEqualTo(10);

            theMock.when(() -> MyNumberUtils.sortNumberByDigits(123)).thenReturn(100);
            int n = MyNumberUtils.sortNumberByDigits(123);
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
        int result = MyNumberUtils.minOfNumbers(a, b, c);
        assertThat(expected).isEqualTo(result);
    }

    @ParameterizedTest
    @CsvSource({
            "А роза упала на лапу Азора, true",
            "1231, false",
            "@А %роза упала на лапу !А зора, true",
    })
    void testSortMinOfNumbers(String str, boolean expected) {
        boolean result = MyStringUtils.isPalindrome2(str);
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @NullSource
    @CsvSource("123")
    void testNullSource(String str) {
        System.out.println(str);
    }

}
