package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class NumberUtils {

//    N членов случайно раскиданы по лестнице из M ступенек, на одной ступеньке может быть сколько угодно членов.
//    Нужно спуститься по этой лестнице вниз. Каждый раз наступая на ступеньку с членами, число
//    позора увеличивается по количеству членов. Спускаться можно на одну или две ступеньки за раз
//            (вы не можете обнаружить число позора дальше, чем в двух ступеньках от вас!).
//    Напишите функцию спуска с лестницы минимизирующую коэффициент позора.
//    Функция принимает массив с количеством членов на каждой ступеньке и возвращает минимизированное число позора.
    public static Integer ladder(List<Integer> arr) {
        Integer[] mas = new Integer[arr.size()];
        mas[0] = arr.get(0);
        mas[1] = arr.get(1);
        for(int i = 2; i < arr.size(); i++) {
            mas[i] = Math.min(mas[i-1], mas[i-2]) + arr.get(i);
        }
        return Math.min(mas[arr.size()-1], mas[arr.size()-2]);
    }

    public static void findNumbersinString(String[] args) {
        String str = "9 jan 2023, temperature -18";

        List<Integer> numbers = Pattern.compile("-?\\d+")
                .matcher(str)
                .results()
                .map(MatchResult::group)
                .map(s -> Integer.parseInt(s))
                .collect(Collectors.toList());

        System.out.print(numbers); // => [9, 2023, -18]
    }

    //    Перевод латинских чисел
    public static Integer romanDigits(String num) {
        Map<String, Integer> table = new HashMap<>(Map.of(
                "I", 1, "V", 5, "X", 10,
                "L", 50, "C", 100, "D", 500, "M", 1000));
        int result = 0;
        int prev = 0;
        int i = num.length() - 1;
        while( i>=0) {
            String s = num.substring(i, i+1);
            int next = table.get(s);
            if(next < prev) {
                result -= next;
            } else {
                result += next;
            }
            prev = next;
            i--;
        }
        return result;

//        Map<String, Integer> table1 = new HashMap<>(Map.of(
//                "I", 1, "V", 5, "X", 10,
//                "L", 50, "C", 100, "D", 500, "M", 1000));
//        Map<String, Integer> table2 = new HashMap<>(Map.of(
//                "IV", 4, "IX", 9, "XL", 40, "XC", 90,
//                "CD", 400, "CM", 900));
//        Integer result = 0;
//        int i = 0;
//        while(i < str.length()) {
//            if (i + 1 < str.length() && table2.containsKey(str.substring(i, i + 2))) {
//                result = result + table2.get(str.substring(i, i + 2));
//                i += 2;
//            } else {
//                result = result + table1.get(str.substring(i, i + 1));
//                i++;
//            }
//        }
//        return result;
    }

//    Сортировка числа по цифрам
    public static Integer sortNumberByDigits(Integer num) {
        String[] mas = num.toString().split("");
        Arrays.sort(mas, Collections.reverseOrder());
        String str = String.join("",mas);
        return Integer.parseInt(str);
    }
    public static int sumOfDigits(int number) {
        number = Math.abs(number);
        int sum = 0;
        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }
    public static int maxOfNumbers(int... numbers) {
        Arrays.sort(numbers);
        return numbers[numbers.length - 1];
    }
    public static int minOfNumbers(int a, int b, int c) {
        int[] arr = {a,b,c};
        Arrays.sort(arr);
        return arr[0];
    }
    public static boolean isSimple(Integer number) {
        if(number < 2) return false;
        for(int i = 2; i < number / 2; i++) {
            if(number % i == 0) {
                return false;
            }
        }
        return true;
    }
    public static Integer siracuzy(Integer num) {
        List<Integer> list = new ArrayList();
        Integer n = num;
        while(n != 1) {
            list.add(n);
            if(n % 2 == 0) {
                n = n / 2;
            } else {
                n = n * 3 + 1;
            }
        }
        return Collections.max(list);
    }

    public static String numberToWord(Integer num) {
        String[] names = {"zero", "one", "two", "three", "four", "five", "six","seven", "eight", "nine",
                "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen",
                "eighteen", "nineteen", "twenty"};
        String word = names[num];
        String text = word.substring(0, 1).toUpperCase() + word.substring(1);
        while(!word.equals("Four") && !word.equals("four")) {
            int n = word.length();
            word = names[n];
            text += " is " + word + ", " + word;
        }
        text += " is magic.";
        return text;
    }

//    Два числа являются взаимно простыми, если их наибольший общий делитель равен 1.
//    Реализуйте функцию Эйлера f(x), которая определяет количество положительных
//    целых чисел меньше x, взаимно простых с x.
    public Integer eiler(Integer num) {
        int count = 0;
        for(int i=1; i<=num; i++) {
            if(nod(i, num) == 1) {
                count++;
            }
        }
        return count;
    }

    public int nod(int x1, int x2) {
        int min = x1<x2 ? x1 : x2;
        for (int i=min; i>=2; i--) {
            if (x1 % i == 0 && x2 % i == 0) {
                return i;
            }
        }
        return 1;
    }

//    Напишите функцию, которая принимает положительное целое число
//        между 0 и 2000 включительно, и возвращает строковое представление
//    этого числа на английском языке.
    public static String digitToText(Integer num) {
        String[] names09 = {"zero", "one", "two", "three", "four",
                "five", "six","seven", "eight", "nine"};
        String[] names1019 = {"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen",
                "eighteen", "nineteen"};
        String[] names2090 = {"twenty","thirty", "forty", "fifty",
                "sixty", "seventy", "eighty", "ninety"};
        String text = "";
        int n1000 = num / 1000;
        if(n1000 != 0) {
            text += names09[n1000] + " thousand, ";
        }
        num = num % 1000;
        int n100 = num / 100;
        if(n100 != 0) {
            text += names09[n100] + " hundred ";
        }
        num = num % 100;
        if(num >= 20) {
            int n10 = num / 10 ;
            text += names2090[n10 - 2];
            num = num % 10;
            if( num != 0) {
                text += "-" + names09[num];
            }
        } else {
            if (num >= 10 ) {
                text += names1019[num % 10];
            } else {
                text += names09[num];
            }
        }

        return text.substring(0,1).toUpperCase() + text.substring(1);

    }

}
