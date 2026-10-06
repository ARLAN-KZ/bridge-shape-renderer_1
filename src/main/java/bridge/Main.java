package bridge;

import bridge.abstraction.Circle;
import bridge.abstraction.Shape;
import bridge.abstraction.Square;
import bridge.implementor.RasterRenderer;
import bridge.implementor.Renderer;
import bridge.implementor.VectorRenderer;

import java.util.List;


public class Main {

    private static final int WIDTH = 30;
    private static final int HEIGHT = 14;

    public static void main(String[] args) {
        System.out.println("=== 1. Same shapes, VectorRenderer ===");
        Renderer vector = new VectorRenderer(WIDTH, HEIGHT);
        render(vector, new Circle(vector, 8, 7, 5), new Square(vector, 17, 3, 8));

        System.out.println("=== 2. Same shapes, RasterRenderer ===");
        Renderer raster = new RasterRenderer(WIDTH, HEIGHT);
        render(raster, new Circle(raster, 8, 7, 5), new Square(raster, 17, 3, 8));

        System.out.println("=== 3. Runtime switch: ONE Circle object, renderer replaced ===");
        Renderer firstRenderer = new VectorRenderer(WIDTH, HEIGHT);
        Circle circle = new Circle(firstRenderer, 15, 7, 6);
        render(firstRenderer, circle);

        Renderer secondRenderer = new RasterRenderer(WIDTH, HEIGHT);
        circle.setRenderer(secondRenderer);        // swap implementation, Circle is untouched
        circle.resize(0.6);                        // abstraction-side operation still works
        render(secondRenderer, circle);
    }

    private static void render(Renderer renderer, Shape... shapes) {
        List.of(shapes).forEach(Shape::draw);
        System.out.println(renderer.name());
        System.out.println(renderer.result());
    }
}
