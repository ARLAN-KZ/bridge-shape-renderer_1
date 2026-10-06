package bridge.implementor;

import java.util.Arrays;


public class RasterRenderer implements Renderer {

    private static final char FILLED = '#';
    private static final char EMPTY = '.';

    private final char[][] pixels;

    public RasterRenderer(int width, int height) {
        pixels = new char[height][width];
        for (char[] row : pixels) {
            Arrays.fill(row, EMPTY);
        }
    }

    @Override
    public void drawCircle(double centerX, double centerY, double radius) {
        fillWhere((x, y) -> Math.hypot(x - centerX, y - centerY) <= radius);
    }

    @Override
    public void drawRectangle(double x, double y, double width, double height) {
        fillWhere((px, py) -> px >= x && px < x + width && py >= y && py < y + height);
    }

    private void fillWhere(PixelTest test) {
        for (int row = 0; row < pixels.length; row++) {
            for (int col = 0; col < pixels[row].length; col++) {
                if (test.contains(col, row)) {
                    pixels[row][col] = FILLED;
                }
            }
        }
    }

    @Override
    public String result() {
        StringBuilder out = new StringBuilder();
        for (char[] row : pixels) {
            for (char pixel : row) {
                out.append(pixel).append(pixel); // 2 chars per pixel: terminal cells are ~2x taller than wide
            }
            out.append(System.lineSeparator());
        }
        return out.toString();
    }

    @Override
    public String name() {
        return "RasterRenderer (ASCII pixels)";
    }

    @FunctionalInterface
    private interface PixelTest {
        boolean contains(int x, int y);
    }
}
