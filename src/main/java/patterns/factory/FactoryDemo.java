package patterns.factory;

import lombok.ToString;

@ToString
class Point {
    private double x;
    private double y;

    private Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    public static class Factory{
        public static Point newCartesianPoint(double x, double y){
            return new Point(x, y);
        }

        public static Point newPolarPoint(double x, double y){
            return new Point(x * Math.cos(y), x * Math.sin(y));
        }
    }
}

interface HotDrink {
    void consume();
}

class Tea implements HotDrink {
    private Tea() {}
    public static Tea of(){
        return new Tea();
    }
    @Override
    public void consume() {
        System.out.println("Tea is delicious");
    }
}

class Coffee implements HotDrink {
    private Coffee(){}
    public static Coffee of(){
        return new Coffee();
    }
    @Override
    public void consume() {
        System.out.println("Coffee is delicious");
    }
}

interface HotDrinkFactory {
    HotDrink process();
}

class TeaFactory implements HotDrinkFactory {
    @Override
    public HotDrink process() {
        System.out.println("..... Processing for Tea");
        return Tea.of();
    }
}

class CoffeeFactory implements HotDrinkFactory {
    @Override
    public HotDrink process() {
        System.out.println("..... Processing for Coffee");
        return Coffee.of();
    }
}

public class FactoryDemo {
    public static void main(String[] args) {
        Point cartesianPoint = Point.Factory.newCartesianPoint(2, 3);
        Point polarPoint = Point.Factory.newPolarPoint(2, 3);
        System.out.println(cartesianPoint);
        System.out.println(polarPoint);
        System.out.println();
    }
}
