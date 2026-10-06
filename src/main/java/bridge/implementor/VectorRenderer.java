package bridge.implementor;

import java.util.Locale;


public class VectorRenderer implements Renderer {

    private final int canvasWidth;
    private final int canvasHeight;
    private final StringBuilder elements = new StringBuilder();

    public VectorRenderer(int canvasWidth, int canvasHeight) {
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
    }

    @Override
    public void drawCircle(double centerX, double centerY, double radius) {
        elements.append(String.format(Locale.US,
                "  <circle cx=\"%.1f\" cy=\"%.1f\" r=\"%.1f\" fill=\"steelblue\"/>%n",
                centerX, centerY, radius));
    }

    @Override
    public void drawRectangle(double x, double y, double width, double height) {
        elements.append(String.format(Locale.US,
                "  <rect x=\"%.1f\" y=\"%.1f\" width=\"%.1f\" height=\"%.1f\" fill=\"tomato\"/>%n",
                x, y, width, height));
    }

    @Override
    public String result() {
        return String.format(Locale.US,
                "<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"%d\" height=\"%d\">%n%s</svg>%n",
                canvasWidth, canvasHeight, elements);
    }

    @Override
    public String name() {
        return "VectorRenderer (SVG)";
    }
}
