package tinkoff;

import java.util.Arrays;
import java.util.Scanner;

public class TinkoffTry {

    class App1 {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int c = scanner.nextInt();
            int d = scanner.nextInt();

            int result = d>b ? a+c*(d-b) : a;
            System.out.println(result);
        }
    }

    class App2 {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            int personsNumber = scanner.nextInt();
            int timeT = scanner.nextInt();
            int[] persons = new int[personsNumber];
            for(int i = 0; i < personsNumber; i++) {
                persons[i] = scanner.nextInt();
            }
            int personT = scanner.nextInt();
            int floorT = persons[personT - 1];

            Arrays.sort(persons);
            int min = persons[0];
            int max = persons[personsNumber-1];
            int result = floorT-min < timeT ? max-min : Math.min(max-min+max-floorT, max-min+floorT-min);

            System.out.println(result);
        }
    }


}
