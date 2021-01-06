package patterns.bridge;
abstract class Shape {
    Color color;
    Shape (Color color){
        this.color = color;
    }
    abstract void render();
}

interface Color {
    void renderInColor();
}
class Red implements Color {
    @Override
    public void renderInColor() {
        System.out.println("Render in Red Color");
    }
}

class Black implements Color {
    @Override
    public void renderInColor() {
        System.out.println("Render in Black Color");
    }
}

class Circle extends Shape {

    Circle(Color color) {
        super(color);
    }

    @Override
    public void render() {
        System.out.println("Renders as Circle");
    }
}
public class BridgeDemo {
    public static void main(String[] args) {
        Shape circle = new Circle(new Red());
        circle.color.renderInColor();
    }
}
