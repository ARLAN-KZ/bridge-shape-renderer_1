package bridge.abstraction;

import bridge.implementor.Renderer;

import java.util.Objects;


public abstract class Shape {

    protected Renderer renderer;

    protected Shape(Renderer renderer) {
        this.renderer = Objects.requireNonNull(renderer, "renderer must not be null");
    }

    /** Draws the shape using whatever renderer is currently plugged in. */
    public abstract void draw();

    /** Scales the shape; independent of how it is rendered. */
    public abstract void resize(double factor);

    /** Switches the implementation at runtime without touching the shape's own logic. */
    public void setRenderer(Renderer renderer) {
        this.renderer = Objects.requireNonNull(renderer, "renderer must not be null");
    }
}
