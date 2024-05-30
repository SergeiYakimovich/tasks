import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlaneSeatsTest {
    @Test
    public void test() throws IOException {
        String expected = readClassPathResourceToString("expected.txt");
        String result = readClassPathResourceToString("result.txt");
        Scanner scannerExpected = new Scanner(expected);
        Scanner scannerResult = new Scanner(result);

        while (scannerExpected.hasNextLine()) {
            String expectedLine = scannerExpected.nextLine();
            String resultLine = scannerResult.nextLine();
            if (expectedLine.equals(resultLine)) {
                continue;
            } else {
                for (int i = 0; i < expectedLine.length(); i++) {
                    if (expectedLine.charAt(i) != resultLine.charAt(i)) {
                        System.out.printf("Position %s, Expected: %s but got %s%n", i, expectedLine.charAt(i),resultLine.charAt(i));
                        assertEquals(expectedLine, resultLine);
                    }
                }
            }
        }
    }

    public String readClassPathResourceToString(String path) throws IOException {
        return IOUtils.toString(this.getClass().getResourceAsStream(path));
    }

}
