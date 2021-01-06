package creational.factory;
class Point {
    double x;
    double y;

    private Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    static class Factory {
        public static Point getNewCartesian(double x, double y){
            return new Point(x, y);
        }

        public static Point getNewTheta(double x, double y){
            return new Point(x * Math.sin(y), x * Math.cos(y));
        }
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}


public class FactoryDemo {
    public static void main(String[] args) {
        Point cartesian = Point.Factory.getNewCartesian(2, 3);
        System.out.println(cartesian);
        Point theta = Point.Factory.getNewTheta(2, 3);
        System.out.println(theta);
    }
}
