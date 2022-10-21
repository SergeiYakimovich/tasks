
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static utils.NumberUtils.minOfNumbers;


public class App {

    public static void main(String[] args) throws Exception {
        System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
        System.out.println();
     
    }

    


    public static List<String> solution2(List<String> words) {
        List<String> result = words.stream()
                .collect(HashMap<String, Integer>::new, (m, c) -> {m.put(c, m.containsKey(c) ? (1 + m.get(c)) : 1);
                    }, HashMap::putAll)
                .entrySet()
                .stream()
                .map(e -> (int) e.getValue() > 1 ? e.getKey() + "s" : e.getKey())
                .collect(Collectors.toList());

//        List<String> result = new ArrayList();
//        Map<String, Integer> map = new HashMap<>();
//        for(String word : words) {
//            if(map.containsKey(word)) {
//                map.put(word, map.get(word) + 1);
//            } else {
//                map.put(word, 1);
//            }
//        }
//        for(String word : words) {
//            if(map.get(word) > 1) {
//                if(!result.contains(word + "s")) {
//                    result.add(word + "s");
//                }
//            } else {
//                result.add(word);
//            }
//        }
        return result;
    }

}
