package utilities;

import fr.tp.inf112.projects.canvas.model.RectangleShape;

/**
 * An implementation of the RectangleShape interface.
 */
public class RectangleShapeImpl implements RectangleShape {

    /**
     * The width of the rectangle.
     */
    private int width;

    /**
     * The height of the rectangle.
     */
    private int height;

    /**
     * Constructs a new RectangleShapeImpl object with the given width and height.
     *
     * @param width  the width of the rectangle
     * @param height the height of the rectangle
     */
    public RectangleShapeImpl(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Gets the width of the rectangle.
     *
     * @return the width
     */
    @Override
    public int getWidth() {
        return this.width;
    }

    /**
     * Gets the height of the rectangle.
     *
     * @return the height
     */
    @Override
    public int getHeight() {
        return this.height;
    }

    /**
     * Sets the width and height of the rectangle.
     *
     * @param width  the new width
     * @param height the new height
     */
    public void setWidhthandheight(int width, int height) {
        this.width = width;
        this.height = height;
    }

}