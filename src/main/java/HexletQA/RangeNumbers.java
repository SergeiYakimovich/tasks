package HexletQA;

import java.time.temporal.ValueRange;

/**
 * задание диапазонов для ранжирования
 */
public class RangeNumbers {
    public static void makeRange() {
        ValueRange rang = ValueRange.of(0, 10);

        System.out.println(rang.isValidValue(2)); // => true
        System.out.println(rang.isValidValue(20)); // => false
        System.out.println(rang);  // => 0 - 10
    }

    public enum Range {
        RANGE1(Double.MIN_VALUE,1),
        RANGE2(1, 2),
        RANGE3(2, 3),
        RANGE4(3, 4),
        RANGE5(4, 5),
        RANGE6(5, 6),
        RANGE7(6, 7),
        RANGE8(7, 8),
        RANGE9(8, 9),
        RANGE10(9, Double.MAX_VALUE);

        public double min;
        public double max;

        Range(double min, double max) {
            this.min = min;
            this.max = max;
        }

        @Override
        public String toString() {
            return "Диапазон {" +
                    " min=" + min +
                    ", max=" + max +
                    " }\n";
        }

        public static Range getRange(double x) {
            for(Range range : Range.values()) {
                if(x >= range.min && x < range.max) {
                    return range;
                }
            }
            return null;
        }
    }

    public static void main(String[ ] args) {
        double x = 4.5;
        Range range = Range.getRange(x);
        System.out.println(range.toString()); // => Диапазон { min=4.0, max=5.0 }
    }
}
