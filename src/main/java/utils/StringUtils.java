package utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static utils.NumberUtils.minOfNumbers;

public class StringUtils {

//    Заменить повторяющиеся слова множественным числом (+"s")
//        List.of("dog", "cat", "pig", "dog", "pig") -> [cat, dogs, pigs]
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

//    Посчитать слово как сумму ASCII
    public static int countWordAsASCII(String word) {
        return (int) Arrays.stream(word.split(""))
                .mapToInt(c -> c.charAt(0))
                .sum();
    }

//    Проверить адрес на IPv4
    public Boolean isIPv4(String address) {
        String[] mas = address.split("\\.");
        if(mas.length != 4) {
            return false;
        }
        for(String item : mas) {
            char[] chars = item.toCharArray();
            for(char c : chars) {
                if(!Character.isDigit(c)) {
                    return false;
                }
            }
            int n = Integer.parseInt(item);
            if (n > 255) {
                return false;
            }
        }
        return true;
    }

//    Проверить код на ISBN
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

//    Проверить на открытые скобки
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

//    Вычислить расстояние Левенштейна (за сколько шагов можно перейти от слова1 к слову2)
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

    public static List<String> removeCharFromListOfString(List<String> words, String character) {
        return words.stream()
                .map(word -> {
                    return Arrays.stream(word.split(""))
                            .filter(c -> !c.equals(character))
                            .collect(Collectors.joining (""));
                })
                .collect(Collectors.toList());
    }

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

    public static String DelEvenWordFromText(String str) {
        return  Arrays.stream(str.split(" "))
                .filter(s -> s.length() % 2 == 0)
                .collect(Collectors.joining(" "));
    }

}
