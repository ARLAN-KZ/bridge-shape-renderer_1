package bridge.abstraction;

import bridge.implementor.Renderer;


public class Circle extends Shape {

    private final double centerX;
    private final double centerY;
    private double radius;

    public Circle(Renderer renderer, double centerX, double centerY, double radius) {
        super(renderer);
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
    }

    @Override
    public void draw() {
        renderer.drawCircle(centerX, centerY, radius);
    }

    @Override
    public void resize(double factor) {
        radius *= factor;
    }
}
