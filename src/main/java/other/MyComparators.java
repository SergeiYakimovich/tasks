package other;

import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

public class MyComparators {
    public static void main(String[] args) {
        List<Item> items = new ArrayList<>();
        items.add(new Item("гитара",5, 800));
        items.add(new Item("утюг",5, 500));
        items.add(new Item("чайник",3, 300));
        items.add(new Item("лампа",3, 500));
        items.add(new Item("телевизор",15, 2000));
        items.add(new Item("ваза",2, 450));
        items.add(new Item("миксер",1, 400));
        items.add(new Item("блендер",1, 200));

        items.sort((x1, x2) -> {
            if(x1.getWeight() == x2.getWeight()) return x1.getCost()> x2.getCost() ? -1 : 1;
            return x1.getWeight() > x2.getWeight() ? -1 : 1;
        });
        System.out.println(items);
    }
    @ToString
    public static class Item {
        private String name;
        private int weight;
        private int cost;

        public Item(String name, int weight, int cost) {
            this.name = name;
            this.weight = weight;
            this.cost = cost;
        }

        public String getName() {
            return name;
        }

        public int getWeight() {
            return weight;
        }

        public int getCost() {
            return cost;
        }
    }
}
