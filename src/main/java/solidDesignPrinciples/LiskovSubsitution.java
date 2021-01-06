package solidDesignPrinciples;

class Rectangle {
    protected int width;
    protected int height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getArea(){
        return width * height;
    }
}

class Square extends Rectangle {

    public Square(int width) {
        super(width, width);
    }

    public void setWidth(int width) {
        this.width = width;
        setHeight(width);
    }

    public void setHeight(int height) {
        this.height = height;
        setWidth(height);
    }

}

public class LiskovSubsitution {
    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle(10, 20);
        System.out.println("Area of Rectangle " + rectangle.getArea());

        Rectangle square = new Square(10);
        System.out.println("Area of Square " + square.getArea());
    }
}
