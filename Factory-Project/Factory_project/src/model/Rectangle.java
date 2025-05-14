package model;

import java.util.ArrayList;

import fr.tp.inf112.projects.canvas.model.Shape;
import utilities.Point;
import utilities.RectangleShapeImpl;

/**
 * Represents a rectangle-shaped component in the factory.
 */
public abstract class Rectangle extends Component {

    // The length of the rectangle along the x-axis
    private int xLenght;

    // The length of the rectangle along the y-axis
    private int yLenght;

    // The serial version UID
    private static final long serialVersionUID = 202405250003L;

    /**
     * Constructs a new rectangle component with the given parameters.
     *
     * @param name           the name of the rectangle
     * @param xCoord         the x-coordinate of the rectangle
     * @param yCoord         the y-coordinate of the rectangle
     * @param xLenght        the length of the rectangle along the x-axis
     * @param yLenght        the length of the rectangle along the y-axis
     * @param destXCoord     the destination x-coordinate of the rectangle
     * @param destYCoord     the destination y-coordinate of the rectangle
     * @param factory        the factory that the rectangle belongs to
     */
    public Rectangle(String name, int xCoord, int yCoord, int xLenght, int yLenght, int destXCoord, int destYCoord, Factory factory) {
        super(name, xCoord, yCoord, destXCoord, destYCoord, factory);
        this.xLenght = xLenght;
        this.yLenght = yLenght;
    }

    /**
     * Returns the length of the rectangle along the x-axis.
     *
     * @return the length of the rectangle along the x-axis
     */
    public int getxLenght() {
        return xLenght;
    }

    /**
     * Returns the length of the rectangle along the y-axis.
     *
     * @return the length of the rectangle along the y-axis
     */
    public int getyLenght() {
        return yLenght;
    }

    /**
     * Returns the shape of the rectangle.
     *
     * @return the shape of the rectangle
     */
    public Shape getShape() {
        return new RectangleShapeImpl(this.xLenght, this.yLenght);
    }

    /**
     * Checks if the rectangle overlays a given dot.
     *
     * @param dot the dot to check
     * @return true if the rectangle overlays the dot, false otherwise
     */
    public boolean overlays(Point point) {
        return ((this.getxCoord() - Robot.getRadius()) < point.getxCoord() && (this.getxCoord() + this.getxLenght() + Robot.getRadius()) > point.getxCoord()) &&
               ((this.getyCoord() - Robot.getRadius()) < point.getyCoord() && (this.getyCoord() + this.getyLenght() + Robot.getRadius()) > point.getyCoord());
    }

}
