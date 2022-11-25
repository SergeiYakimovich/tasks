import org.w3c.dom.Node;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class App {

    public static void main(String[] args) throws Exception {
        Map<String, Integer> map = Map.of("Ivan", 2000, "Petr", 2010, "Egor", 2000);
        Set<String> mapKeys = map.keySet();
        Set<Integer> mapValues = map.values().stream().collect(Collectors.toSet());
        Set<Map.Entry<String, Integer>> mapSet = map.entrySet();

        System.out.println(mapKeys); // => [Petr, Ivan, Egor]
        System.out.println(mapValues); // => [2000, 2010]
        System.out.println(mapSet); // => [Petr=2010, Ivan=2000, Egor=2000]

        Set<String> mySet = Set.of("Ivan", "Petr", "Egor");
        Iterator myIterator = mySet.iterator();
        while(myIterator.hasNext()) {
            System.out.print(myIterator.next() + " "); // => Petr Ivan Egor
        }


    }

    public static String reverseByRecurs(String str) {
        if(str.length() == 0) {
            return "";
        } else {
            return reverseByRecurs(str.substring(1)) + str.charAt(0);
        }
    }



}

//        System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
//        System.out.println();
