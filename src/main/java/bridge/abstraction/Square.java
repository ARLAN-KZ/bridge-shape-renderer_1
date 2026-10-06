package bridge.abstraction;

import bridge.implementor.Renderer;


public class Square extends Shape {

    private final double x;
    private final double y;
    private double side;

    public Square(Renderer renderer, double x, double y, double side) {
        super(renderer);
        this.x = x;
        this.y = y;
        this.side = side;
    }

    @Override
    public void draw() {
        renderer.drawRectangle(x, y, side, side);
    }

    @Override
    public void resize(double factor) {
        side *= factor;
    }
}
