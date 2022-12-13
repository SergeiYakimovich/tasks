package utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class MapUtils {

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

    public static List<String> getTextOfUser(List<Map<String, String>> messages, String user) {
        List<String> result = new ArrayList();
        messages.stream()
                .forEach(map -> {
                    if(map.get("user").equals(user)) {
                        result.add(map.get("text"));
                    }
                });
        if(result.size() == 0) {
            result.add("no messages!");
        }
        return result;
    }

}
