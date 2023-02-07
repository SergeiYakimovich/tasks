package utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * задачки с мапами
 */
public class MyMapUtils {

    /**
     * сортировка мап по значению
     */
    public static void sortMapByValue() {
        Map<String, Integer> myMap = Map.of("Ivan", 2000, "Petr", 1990, "Egor", 1995);

        Map<String, Integer> sortedMap1 = myMap.entrySet().stream()
//                .sorted(Comparator.comparingInt(Map.Entry::getValue))
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
        System.out.println(sortedMap1); // => {Petr=1990, Egor=1995, Ivan=2000}

        Map<String, Integer> sortedMap2 = new TreeMap<String, Integer>(
                Comparator.comparing(myMap::get));
        sortedMap2.putAll(myMap);

        System.out.println(sortedMap2); // => {Petr=1990, Egor=1995, Ivan=2000}
    }

    /**
     * переворот мап - смена ключ-значение
     */
    public static void changeKeyValue() {
        Map<String, Integer> myMap = Map.of("Ivan", 2000, "Petr", 1990, "Egor", 1995);
        Map<Integer, String> newMap =
                myMap.entrySet()
                        .stream()
                        .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
        System.out.println(newMap); // => {2000=Ivan, 1990=Petr, 1995=Egor}

        // если мы не уверены в уникальности значений и хотим сохранить все варианты, то лучше так :
        Map<String, Integer> myMap1 = Map.of("Ivan", 2000, "Petr", 1990, "Egor", 2000);
        Map<Integer, List<String>> newMap1 = myMap1.entrySet()
                .stream()
                .collect(Collectors.groupingBy(Map.Entry::getValue,
                        Collectors.mapping(Map.Entry::getKey, Collectors.toList())));
        System.out.println(newMap1); // => {2000=[Egor, Ivan], 1990=[Petr]}
    }

    /**
     * посчитать слова в списке
     */
    public static Map<String, Integer> countWordInList(List<String> arr) {
        Map<String, Integer> res = new HashMap<>();
        for(String word : arr) {
            if(res.containsKey(word)) {
                res.put(word, res.get(word) + 1);
            } else {
                res.put(word, 1);
            }
        }
        return res;
    }

}
