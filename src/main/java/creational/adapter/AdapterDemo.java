package creational.adapter;

import java.util.Arrays;
import java.util.List;

interface Quack {
    void quack();
}

class Duck implements Quack {

    @Override
    public void quack() {
        System.out.println("Main Quack");
    }
}

class WoodenDuck {
    public void quack() {
        System.out.println("Wood Quack");
    }
}

class DuckAdapter implements Quack {

    WoodenDuck woodenDuck;

    public DuckAdapter(WoodenDuck woodenDuck) {
        this.woodenDuck = woodenDuck;
    }

    @Override
    public void quack() {
        woodenDuck.quack();
    }
}

public class AdapterDemo {
    public static void main(String[] args) {
        Quack duck1 = new Duck();
        Quack duck2 = new Duck();
        Quack duck3 = new Duck();
        WoodenDuck woodenDuck = new WoodenDuck();
        Quack adapter = new DuckAdapter(woodenDuck);

        List<Quack> ducks = Arrays.asList(duck1, duck2, duck3, adapter);

        for (Quack duck : ducks){
            duck.quack();
        }
    }
}
