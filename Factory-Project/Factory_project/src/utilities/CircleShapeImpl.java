package utilities;

import fr.tp.inf112.projects.canvas.model.OvalShape;

/**
 * An implementation of the OvalShape interface for a circle.
 */
public class CircleShapeImpl implements OvalShape {

    /**
     * The radius of the circle.
     */
    private int radius;

    /**
     * Constructs a new CircleShapeImpl object with the given radius.
     *
     * @param radius the radius of the circle
     */
    public CircleShapeImpl(int radius) {
        this.radius = radius;
    }

    /**
     * Gets the width of the circle.
     *
     * @return the width
     */
    @Override
    public int getWidth() {
        return radius / 2;
    }

    /**
     * Gets the height of the circle.
     *
     * @return the height
     */
    @Override
    public int getHeight() {
        return getWidth();
    }

    /**
     * Sets the height and width of the circle.
     *
     * @param radius the new radius of the circle
     */
    public void settHeightandwidth(int radius) {
        this.radius = radius;
    }

}
