package utilities;

import java.io.Serializable;

/**
 * A class for representing a point in a two-dimensional space.
 */
public class Point implements Serializable {

    /**
     * The x-coordinate of the point.
     */
    private int xCoord;

    /**
     * The y-coordinate of the point.
     */
    private int yCoord;

    /**
     * The serial version UID.
     */
    private static final long serialVersionUID = 202405101155L;

    /**
     * Constructs a new Point object with the given x- and y-coordinates.
     *
     * @param xCoord the x-coordinate of the point
     * @param yCoord the y-coordinate of the point
     */
    public Point(int xCoord, int yCoord) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    /**
     * Gets the x-coordinate of the point.
     *
     * @return the x-coordinate
     */
    public int getxCoord() {
        return xCoord;
    }

    /**
     * Sets the x-coordinate of the point.
     *
     * @param xCoord the new x-coordinate
     */
    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    /**
     * Gets the y-coordinate of the point.
     *
     * @return the y-coordinate
     */
    public int getyCoord() {
        return yCoord;
    }

    /**
     * Sets the y-coordinate of the point.
     *
     * @param yCoord the new y-coordinate
     */
    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    /**
     * Gets the square norm of the point.
     *
     * @return the square norm
     */
    public int getSquareNorm() {
        return (this.xCoord * this.xCoord) + (this.yCoord * this.yCoord);
    }

    /**
     * Gets the name of the point.
     *
     * @return the name
     */
    public String getName() {
        return "(" + Integer.toString(xCoord) + "," + Integer.toString(yCoord) + ")";
    }

}
