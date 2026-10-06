package bridge;

import bridge.abstraction.Circle;
import bridge.abstraction.Rectangle;
import bridge.abstraction.Shape;
import bridge.abstraction.Square;
import bridge.implementor.RasterRenderer;
import bridge.implementor.Renderer;
import bridge.implementor.VectorRenderer;

/** Dependency-free self-checks. Run with scripts/run.sh (uses java -ea). */
public class BridgeSmokeTest {

    public static void main(String[] args) {
        vectorRendererEmitsSvgForEveryShape();
        rasterRendererFillsPixels();
        rendererCanBeSwitchedAtRuntime();
        nullRendererIsRejected();
        System.out.println("All bridge checks passed.");
    }

    private static void vectorRendererEmitsSvgForEveryShape() {
        Renderer r = new VectorRenderer(20, 20);
        new Circle(r, 5, 5, 3).draw();
        new Square(r, 1, 1, 4).draw();
        new Rectangle(r, 2, 2, 6, 3).draw();
        check(r.result().contains("<circle"), "circle -> <circle>");
        check(r.result().contains("<rect"), "square/rectangle -> <rect>");
    }

    private static void rasterRendererFillsPixels() {
        Renderer r = new RasterRenderer(10, 10);
        new Square(r, 0, 0, 2).draw();
        check(r.result().startsWith("####.."), "2x2 square fills the top-left corner");
    }

    private static void rendererCanBeSwitchedAtRuntime() {
        Renderer vector = new VectorRenderer(10, 10);
        Renderer raster = new RasterRenderer(10, 10);
        Shape circle = new Circle(vector, 5, 5, 2);
        circle.draw();
        circle.setRenderer(raster);
        circle.draw();
        check(vector.result().contains("<circle"), "vector got the first draw");
        check(raster.result().contains("#"), "raster got the second draw");
    }

    private static void nullRendererIsRejected() {
        try {
            new Circle(null, 0, 0, 1);
            check(false, "null renderer must throw");
        } catch (NullPointerException expected) {
            // ok
        }
    }

    private static void check(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError("FAILED: " + message);
        }
    }
}
