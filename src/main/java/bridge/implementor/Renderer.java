package bridge.implementor;


public interface Renderer {

    void drawCircle(double centerX, double centerY, double radius);

    void drawRectangle(double x, double y, double width, double height);


    String result();

    String name();
}
