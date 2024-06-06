package utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static utils.MyNumberUtils.minOfNumbers;

/**
 * задачки со строками
 */
public class MyStringUtils {
    private static final Pattern CYRILLIC_PATTERN = Pattern.compile("[А-Я]{1}[а-я]+");

    /**
     * Заменить повторяющиеся слова множественным числом (+"s")
     * List.of("dog", "cat", "pig", "dog", "pig") -> [cat, dogs, pigs]
     */
    public static List<String> changeDuplicatedWordsByAddingS(List<String> words) {
        List<String> result = words.stream()
                .collect(HashMap<String, Integer>::new, (m, c) -> {m.put(c, m.containsKey(c) ? (1 + m.get(c)) : 1);
                }, HashMap::putAll)
                .entrySet()
                .stream()
                .map(e -> (int) e.getValue() > 1 ? e.getKey() + "s" : e.getKey())
                .collect(Collectors.toList());
        return result;
    }

    /**
     * Посчитать слово как сумму ASCII
     */
    public static int countWordAsASCII(String word) {
        return (int) Arrays.stream(word.split(""))
                .mapToInt(c -> c.charAt(0))
                .sum();
    }

    /**
     * Проверить код на ISBN
     */
    public static Boolean validate_ISBN(String code) {
        Integer[] mas = Arrays.stream(code.split(""))
                .filter(x -> !x.equals("-"))
                .map(x -> Integer.parseInt(x))
                .toArray(Integer[]::new);
        int count = 0;
        for(int i=0; i < mas.length; i++) {
            count += mas[i] * (10 - i);
        }
        return count % 11 == 0;
    }

    /**
     * Проверить на открытые скобки
     */
    public static Boolean hasOpenBrackets(String brackets) {
        int n1 = 0;
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        for(int i = 0; i < brackets.length(); i++) {
            switch(brackets.charAt(i)) {
                case '(' : n1++;
                    break;
                case ')' : n1--;
                    break;
                case '[' : n2++;
                    break;
                case ']' : n2--;
                    break;
                case '<' : n3++;
                    break;
                case '>' : n3--;
                    break;
                case '{' : n4++;
                    break;
                case '}' : n4--;
            }
            if(n1<0 || n2<0 || n3<0 || n4 < 0) {
                return false;
            }
        }
        if ((n1 + n2 + n3 + n4) != 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Вычислить расстояние Левенштейна (за сколько шагов можно перейти от слова1 к слову2)
     */
    public static Integer levenshteinDistance(String s1, String s2) {
        int l1 = s1.length() + 1;
        int l2 = s2.length() + 1;
        int[][] mas = new int[l2][l1];
        for(int i = 0; i < l2; i++) {
            mas[i][0] = i;
        }
        for(int j = 0; j < l1; j++) {
            mas[0][j] = j;
        }
        for(int i = 1; i < l2; i++) {
            for(int j = 1; j < l1; j++) {
                int m = s1.charAt(j - 1)==s2.charAt(i - 1) ? 0 : 1;
                mas[i][j] = minOfNumbers(mas[i][j-1] + 1, mas[i-1][j] + 1,
                        mas[i-1][j-1] + m);
            }
        }
//        for(int i = 0; i < l2; i++) {
//            System.out.println(Arrays.toString(mas[i]));
//        }
        return mas[l2-1][l1-1];
    }

    /**
     * проверить все ли слова с заглавной
     */
    public Boolean IsAllWordsBeginsUpperCase(String title) {
        String[] words = title.split(" ");
        for(String word : words) {
            char[] symbols = word.toCharArray();
            if(!Character.isUpperCase(symbols[0])) {
                return false;
            }
        }
        return true;
    }

    /**
     * удалить слова нечетной длины
     */
    public static String DelEvenWordFromText(String str) {
        return  Arrays.stream(str.split(" "))
                .filter(s -> s.length() % 2 == 0)
                .collect(Collectors.joining(" "));
    }

/*    Последовательность Конвэя "Посмотри и Скажи" - это последовательность чисел,
    в которой в каждом терме цифры "читаются вслух". 1 читается как "one 1". 11
    читается как "two 1's". 21 читается как "one 2, then one 1".
        1211 читается как "one 1, then one 2, then two 1's".
 */
    public static String conveyLookAndSay(String str) {
        Map<String, Integer> map = Map.of("one", 1, "two", 2,"three",3,"four",4,
                "five",5,"six",6,"seven",7,"eight",8,"nine",9);
        String text = "";
        str = "then " + str;
        String[] arr = str.split(",");
        for(int i=0; i<arr.length;i++) {
            String[] mas = arr[i].trim().split(" ");
            int n = 0;
            if(map.get(mas[1]) != null) {
                n=map.get(mas[1]);
            }
            for(int j=1; j<=n;j++) {
                text += mas[2].substring(0,1);
            }
        }

        return text;
    }

    /**
     * найти мксимальный палиндром
     */
    public static String maxPalindrom(String s) {
        String str="";
        int max=0;
        for(int i=0; i < s.length()-1; i++) {
            for(int j=i+1; j <= s.length(); j++) {
                String next = s.substring(i,j);
                if(isPalindrom(next)) {
                    if(next.length()>max) {
                        max=next.length();
                        str=next;
                    }
                }
            }

        }
        return str;
    }

    /**
     * проверка на палиндром
     */
    public static boolean isPalindrom(String str) {
        if(str.length()==0) {
            return false;
        }
        if(str.length()==1) {
            return true;
        }
        for(int i = 0; i< str.length() / 2; i++) {
            if(str.charAt(i) != str.charAt(str.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * проверка на аннаграмму
     */
    public static boolean isAnagram(String str1, String str2) {
        if(str1.length() != str2.length()) {
            return false;
        }
        char[] arr1 = str1.toCharArray();
        char[] arr2 = str2.toCharArray();
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        return Arrays.equals(arr1,arr2) ? true : false;

    }

    /**
     * переворот строки рекурсией
     */
    public String reverseByRecurs(String str) {
        if(str.length() == 0) {
            return "";
        } else {
            return reverseByRecurs(str.substring(1)) + str.charAt(0);
        }
    }
}
