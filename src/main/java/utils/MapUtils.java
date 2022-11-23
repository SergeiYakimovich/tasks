package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUtils {

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
