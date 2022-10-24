package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NumberUtils {

//    Перевод латинских чисел
    public static Integer romanDigits(String str) {
        Map<String, Integer> table1 = new HashMap<>(Map.of(
                "I", 1, "V", 5, "X", 10,
                "L", 50, "C", 100, "D", 500, "M", 1000));
        Map<String, Integer> table2 = new HashMap<>(Map.of(
                "IV", 4, "IX", 9, "XL", 40, "XC", 90,
                "CD", 400, "CM", 900));
        Integer result = 0;
        int i = 0;
        while(i < str.length()) {
            if (i + 1 < str.length() && table2.containsKey(str.substring(i, i + 2))) {
                result = result + table2.get(str.substring(i, i + 2));
                i += 2;
            } else {
                result = result + table1.get(str.substring(i, i + 1));
                i++;
            }
        }
        return result;
    }

//    Сортировка числа по цифрам
    public static Integer sortNumberByDigits(Integer num) {
        String[] mas = num.toString().split("");
        Arrays.sort(mas, Collections.reverseOrder());
        String str = String.join("",mas);
        return Integer.parseInt(str);
    }

    public static Integer maxOfNumbers(Integer a, Integer b, Integer c) {
        int[] arr = {a,b,c};
        Arrays.sort(arr);
        Double d = Math.pow(arr[1], 2) + Math.pow(arr[2], 2);
        return d.intValue();
    }
    public static Integer minOfNumbers(Integer a, Integer b, Integer c) {
        int[] arr = {a,b,c};
        Arrays.sort(arr);
        return arr[0];
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

}
