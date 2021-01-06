package creational.bridge;

import lombok.Data;
import lombok.Setter;

public class BridgeDemo {

    public static void main(String[] args) {
        Color red = new Red();
        Color black = new Black();
        Circle circle = new Circle(red);
        circle.fillColor();
        circle.setColor(black);
        circle.fillColor();
    }

}
interface Color{
    void render();
}

class Red implements Color {
    @Override
    public void render() {
        System.out.println(":::::::::: Red");
    }
}

class Black implements Color {
    @Override
    public void render() {
        System.out.println(":::::::::: Black");
    }
}

abstract class Shape {
    protected String name;
    public abstract void fillColor();
}

@Data
class Circle extends Shape {
    private Color color;
    public Circle (Color color){
        this.color = color;
    }
    @Override
    public void fillColor() {
        color.render();
    }
}