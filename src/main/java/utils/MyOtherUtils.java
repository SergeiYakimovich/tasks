package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyOtherUtils {
    public static void decartMultiply() {
        List<List<String>> input = Arrays.asList(
                Arrays.asList("a", "b", "c"),
                Arrays.asList("x", "y"),
                Arrays.asList("1", "2", "3")
        );
        List<String> result = new ArrayList<>(input.get(0));
        for (int i = 1; i < input.size(); i++) {
            List<String> temp = new ArrayList<>();
            for (int j = 0; j < input.get(i).size(); j++) {
                int finalI = i;
                int finalJ = j;
                result.forEach(s -> temp.add(s + input.get(finalI).get(finalJ)));
            }
            result = temp;
        }
//        result = result.stream().sorted().collect(Collectors.toList());
        System.out.println(result);

    }


}
