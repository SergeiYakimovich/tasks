package yandex.context1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * решение задач в терминале Яндекса
 */
public class YandexTasks {
class App6 { // задача shifr

    public static void main(String[] args) throws Exception {
        Path path = Paths.get("input.txt");
        String textIn = Files.readString(path);

        String[] masIn = textIn.trim().split("\n");
        Integer n = Integer.parseInt(masIn[0]);
        for(int i = 1; i<=n; i++) {
            String[] next = masIn[i].trim().split(",");
            int numberOfSymbols = countSymbols(next[0] + next[1] + next[2]);
            int sumOfDigits = countDigits(next[3]) + countDigits(next[4]);
            int position = countPosition(next[0].charAt(0));
            int shifr = numberOfSymbols + sumOfDigits*64 + position*256;
            String hex = "00" + Integer.toHexString(shifr).toUpperCase();
            System.out.println(hex.substring(hex.length()-3,hex.length()));
        }

    }
    public static int countSymbols(String str) {
        Set<Character> set = new HashSet<>();
        for(int i=0; i<str.length(); i++) {
            set.add(str.charAt(i));
        }
        return set.size();
    }
    public static int countDigits(String str) {
        int sum = 0;
        for(int i=0; i< str.length(); i++) {
            sum += Character.getNumericValue(str.charAt(i));
        }
        return sum;
    }
    public static int countPosition(char input) {
        return (int) Character.toLowerCase(input) - 'a' + 1;
    }

}

class App7 { // задача ракеты

    public static void main(String[] args) throws Exception {
        Path path = Paths.get("input.txt");
        String textIn = Files.readString(path);

        String[] masIn = textIn.trim().split("\n");
        Integer n = Integer.parseInt(masIn[0].substring(0,1));

        Map<String, List<Integer>> map = new HashMap<>();
        for(int i = 1; i<=n; i++) {
            String[] next = masIn[i].trim().split(" ");
            Integer data = Integer.parseInt(next[0])*24*60 + Integer.parseInt(next[1])*60
                    + Integer.parseInt(next[2]);
            if(!next[4].contains("B")) {
                if(map.containsKey(next[3])) {
                    map.get(next[3]).add(data);
                } else {
                    List<Integer> list = new ArrayList<>();
                    list.add(data);
                    map.put(next[3], list);
                }
            }
        }
        List<Integer> result = new ArrayList<>();
        for(Map.Entry<String,List<Integer>> item : map.entrySet()) {
            List<Integer> list = item.getValue();
            Collections.sort(list);
            int sum = 0;
            for(int i =0; i<list.size(); i+=2 ) {
                sum += list.get(i+1) - list.get(i);
            }
            result.add(sum);
        }
        Collections.sort(result, Collections.reverseOrder());
        String textOut = "";
        for(int i=0; i<result.size(); i++) {
            textOut += result.get(i) + " ";
        }
        System.out.print(textOut.substring(0, textOut.length() - 1));
    }

}

class App8 { // задача подсчет заказов

    public static void main(String[] args) throws Exception {
        Path path = Paths.get("input.txt");
        String textIn = Files.readString(path);
        String[] masIn = textIn.trim().split("\n");

        Integer n = Integer.parseInt(masIn[0]);
        List<Integer[]> listOrders = new ArrayList<>();
        for(int i = 1; i<=n; i++) {
            String[] next = masIn[i].trim().split(" ");
            Integer[] nextMas = new Integer[3];
            for(int j=0; j < 3; j++) {
                nextMas[j] = Integer.parseInt(next[j]);
            }
            listOrders.add(nextMas);
        }

        Integer q = Integer.parseInt(masIn[n+1]);
        List<Integer[]> listRequests = new ArrayList<>();
        for(int i = 1; i<=q; i++) {
            String[] next = masIn[n + 1 + i].trim().split(" ");
            Integer[] nextMas = new Integer[3];
            for(int j=0; j < 3; j++) {
                nextMas[j] = Integer.parseInt(next[j]);
            }
            listRequests.add(nextMas);
        }

        List<Integer> result = new ArrayList<>();
        for(Integer[] request : listRequests) {
            if(request[2] == 1) {
                int sum = 0;
                for(Integer[] order : listOrders) {
                    if((order[0] >= request[0]) && (order[0] <= request[1]) ) {
                        sum += order[2];
                    }
                }
                result.add(sum);
            } else {
                int sum = 0;
                for(Integer[] order : listOrders) {
                    if((order[1] >= request[0]) && (order[1] <= request[1]) ) {
                        sum += order[1] - order[0];
                    }
                }
                result.add(sum);
            }
        }
        String textOut = "";
        for(int i=0; i<result.size(); i++) {
            textOut += result.get(i) + " ";
        }
        Path pathOut = Paths.get("output.txt");
        Files.writeString(pathOut, textOut);

    }

}

class App9 { // задача зелье

    public static void main(String[] args) throws Exception {
        Path path = Paths.get("input.txt");
        String textIn = Files.readString(path);
        textIn = textIn.replaceAll("\r", "");
        String[] masIn = textIn.trim().split("\n");

        Integer n = Integer.parseInt(masIn[0]) - 2;
        Map<Integer, List<Integer>> map = new HashMap<>();
        map.put(1,List.of(1,0));
        map.put(2,List.of(0,1));
        for(int i = 1; i<=n; i++) {
            String[] next = masIn[i].trim().split(" ");
            Integer numberIngredients = Integer.parseInt(next[0]);
            int a = 0;
            int b = 0;
            boolean hasZelie = true;
            for(int j=1; j <= numberIngredients && hasZelie; j++) {
                int zelie = Integer.parseInt(next[j]);
                if(map.containsKey(zelie)) {
                    a += map.get(zelie).get(0);
                    b += map.get(zelie).get(1);
                } else {
                    hasZelie = false;
                }
            }
            if(hasZelie) {
                map.put(i + 2, List.of(a,b));
            }
        }

        Integer q = Integer.parseInt(masIn[n+1]);
        List<Integer[]> listRequests = new ArrayList<>();
        for(int i = 1; i<=q; i++) {
            String[] next = masIn[n + 1 + i].trim().split(" ");
            Integer[] nextMas = new Integer[3];
            for(int j=0; j < 3; j++) {
                nextMas[j] = Integer.parseInt(next[j]);
            }
            listRequests.add(nextMas);
        }

        List<Integer> result = new ArrayList<>();
        for(Integer[] request : listRequests) {
            int zelie = request[2];
            if(!map.containsKey(zelie)) {
                result.add(0);
            } else {
                List<Integer> recept = map.get(zelie);
                if(recept.get(0) <= request[0] && recept.get(1) <= request[1]) {
                    result.add(1);
                } else {
                    result.add(0);
                }
            }
        }

        String textOut = result.stream()
                .map(x -> String.valueOf(x))
                .collect(Collectors.joining(""));
        System.out.println(textOut);

    }

}

}
