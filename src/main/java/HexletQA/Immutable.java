package HexletQA;

import java.util.ArrayList;
import java.util.List;

final class ImmutableList {
    private List<String> list;

    ImmutableList(List<String> listValue) {
        list = new ArrayList<>();
        for(String item : listValue) {
            list.add(item);
        }
    }

    public List<String> getList() {
        List<String> listValue = new ArrayList<>();
        for(String item : list) {
            listValue.add(item);
        }
        return listValue;
    }
}

public class Immutable {

        public static void main(String[] args) {
            List<String> list = new ArrayList<>(List.of("1", "2", "3"));
            ImmutableList immutableList = new ImmutableList(list);

            list.add("4");
            System.out.println(list); // => [1, 2, 3, 4]
            System.out.println(immutableList.getList()); // => [1, 2, 3]

            List<String> testList = immutableList.getList();
            testList.add("5");
            System.out.println(testList); // => [1, 2, 3, 5]
            System.out.println(immutableList.getList()); // => [1, 2, 3]
        }
}
