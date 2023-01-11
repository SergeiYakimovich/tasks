package tinkoff;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TinkoffTasks {

    public class App1 { // задача таблички с разными цветами
        public static void main(String[] argus) {

            Scanner scanner = new Scanner(System.in);
            int number = scanner.nextInt();
            scanner.nextLine();
            String names = scanner.nextLine();
            String colors = scanner.nextLine();
            String[] namesArr = names.split(" ");
            int result = 0;
            int count = 0;
            for(String word : namesArr) {
                int wordLength = word.length();
                String wordColor = colors.substring(count, count + wordLength);
                if(wordColor.contains("BB") || wordColor.contains("YY")) {
                    result++;
                }
                count += wordLength;
            }
            System.out.println(result);
        }
    }

    public class App2 { // задача min NOK
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            int number = scanner.nextInt();

            int minA = 0;
            int minB = 0;
            int minNok = Integer.MAX_VALUE;
            for(int a = 1; a <= number / 2; a++) {
                int b = number - a;
                int newNok = nok(a,b);
                if(newNok < minNok) {
                    minNok = newNok;
                    minA = a;
                    minB = b;
                }
            }

            System.out.println(minA + " " + minB);
        }

        public static int nok(int a, int b) {
            return a * b / nod(a,b);
        }
        public static int nod(int x1, int x2) {
            int min = x1<x2 ? x1 : x2;
            for (int i=min; i>=2; i--) {
                if (x1 % i == 0 && x2 % i == 0) {
                    return i;
                }
            }
            return 1;
        }
    }

    public class App3 { // задача максимальный XOR
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            int number = scanner.nextInt();
            List<Integer> list = new ArrayList<>();
            int result = 0;
            for(int i = 1; i <= number; i++) {
                int next = scanner.nextInt();
                if(!list.contains(next)) {
                    list.add(next);
                    result = maxXor(list);
                }
                System.out.println(result);
            }
        }

        public static int maxXor(List<Integer> list) {
            int max = 0;
            for(int i = 0; i < list.size() - 1; i++) {
                for(int j = i + 1; j < list.size(); j++) {
                    int newXor = list.get(i) ^ list.get(j);
                    max = newXor > max ? newXor : max;
                }
            }
            return max;
        }


    }

    public class App4 { // задача перевод валют
        public static void main(String[] args) {
            final int numberValues = 3;
            Scanner scanner = new Scanner(System.in);
//        int[] coefs = new int[numberValues];
//        int[] values = new int[numberValues];
//        for(int i =0; i < numberValues; i++) {
//            coefs[i] = scanner.nextInt();
//        }
//        for(int i =0; i < numberValues; i++) {
//            values[i] = scanner.nextInt();
//        }
            double[] coefs = new double[]{1.0,2.0,3.0};
            int[] values = new int[]{3,5,4};
            double v0 = coefs[0];
            for(int i = 1; i < numberValues; i++) {
                coefs[i] = coefs[i] / v0;
            }

            double sum = countSum(values, coefs);

            int[] max = new int[numberValues];
            for(int i = 0; i < numberValues; i++) {
                max[i] = (int) Math.round(sum * coefs[i]);
            }
            int result = 0;
            for(int v1 = 0; v1 <= max[0]; v1++ ) {
                for(int v2 = 0; v2 <= max[1]; v2++ ) {
                    for(int v3 = 0; v3 <= max[2]; v3++ ) {
                        double newSum = countSum(new int[]{v1,v2,v3}, coefs);
                        if(Math.abs(newSum - sum) < 0.01) {
                            result++;
                        }
                    }
                }
            }

            System.out.println(result);
        }

        public static double countSum(int[] values, double[] coefs) {
            double sum = values[0] + values[1] / coefs[1] + values[2] / coefs[2];
            return sum;
        }


    }

    public class App5 { // задача треугольники в многоугольнике
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            int number = scanner.nextInt();
            int numberOfTriangles = number / 2;
            double corner = Math.toRadians(180 * (number - 2) / number);
            double side = 0.99999999999999;
            double square = 0.5 * side * side * Math.sin(corner);
            double result = numberOfTriangles * square;

            System.out.println(result);
        }
    }

}
