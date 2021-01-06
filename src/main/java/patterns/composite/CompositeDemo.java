package patterns.composite;

import java.util.ArrayList;
import java.util.List;

interface Weight {
    double getWeight();
}

class Box implements Weight {
    private double weight;
    private List<Weight> items = new ArrayList<>();
    private Box(double weight){
        this.weight = weight;
    }
    public static Box of(double weight){
        return new Box(weight);
    }
    @Override
    public double getWeight() {
        return this.weight + this.items.stream().mapToDouble(Weight::getWeight)
                .reduce(0, Double::sum);
    }

    public boolean addItem(Weight item){
        return items.add(item);
    }
}

class Item implements Weight {
    private double weight;
    private Item(double weight){
        this.weight = weight;
    }
    public static Item of(double weight){
        return new Item(weight);
    }
    @Override
    public double getWeight() {
        return weight;
    }
}

public class CompositeDemo {
    public static void main(String[] args) {
        Box box = Box.of(12);
        box.addItem(Item.of(10));
        box.addItem(Item.of(5));
        Box smallerBox = Box.of(5);
        smallerBox.addItem(Item.of(3));
        box.addItem(smallerBox);
        Weight weight = box;
        System.out.println(String.format("Total weight %s", weight.getWeight()));

    }
}
