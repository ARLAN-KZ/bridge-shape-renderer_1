package bridge.abstraction;

import bridge.implementor.Renderer;


public class Rectangle extends Shape {

    private final double x;
    private final double y;
    private double width;
    private double height;

    public Rectangle(Renderer renderer, double x, double y, double width, double height) {
        super(renderer);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw() {
        renderer.drawRectangle(x, y, width, height);
    }

    @Override
    public void resize(double factor) {
        width *= factor;
        height *= factor;
    }
}
