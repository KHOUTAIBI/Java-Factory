package model;

// Import necessary classes
import java.io.Serializable;

import fr.tp.inf112.projects.canvas.model.Style;
import fr.tp.inf112.projects.canvas.model.impl.RGBColor;
import utilities.Point;
import utilities.StyleImpl;

/**
 * Door class representing a door in the factory.
 * Implements the Serializable interface.
 */
public class Door extends Rectangle implements Serializable {
    // Class variables to store the coordinates of the two points defining the door and its side
    private String side;
    private boolean isopen;

    // Serial version UID for serialization
    private static final long serialVersionUID = 202405242355L;

    /**
     * Constructor to initialize the Door object.
     *
     * @param xCoord1          the x-coordinate of the first point
     * @param yCoord1          the y-coordinate of the first point
     * @param side             the side of the door
     * @param isopen           check if door is open
     */
    public Door(String name, int xCoord, int yCoord, int xLenght, int yLenght, String side, Factory factory) {
        // Initialize the point1 and point2 variables with the given coordinates
        super(name, xCoord, yCoord, xLenght, yLenght, xCoord + xLenght / 2, yCoord + yLenght / 2, factory);
        // Initialize the side variable with the given side
        this.side = side;
        // Initialize if door is open
        this.isopen = false;
    }

    /**
     * Returns the value of the isopen variable.
     *
     * @return if the door is open or not
     */
    public boolean getIsopen() {
        return this.isopen;
    }

    /**
     * Sets the status of the door to the given value.
     *
     * @param isopen the new status of the door
     */
    public void setOpen(boolean isopen) {
        this.isopen = isopen;
        this.setDoorcolor(isopen);
    }

    /**
     * Sets the status of the door and its color based on the given value.
     *
     * @param isopen the new status of the door
     */
    public void setDoorstatus(boolean isopen) {
        this.isopen = isopen;
        this.setDoorcolor(isopen);
    }

    /**
     * Sets the color of the door based on its status.
     *
     * @param isopen the status of the door
     */
    public void setDoorcolor(boolean isopen) {
        if (isopen) {
            ((StyleImpl) this.getStyle()).setColor(RGBColor.GREEN);
        } else {
            ((StyleImpl) this.getStyle()).setColor(RGBColor.RED);
        }
        this.getFactory().notifyObservers();
    }

    /**
     * Returns the side of the door.
     *
     * @return the side of the door
     */
    public String getSide() {
        return side;
    }

    @Override
    public boolean overlays(Point point) {
        return false;
    }

    @Override
    public Style getStyle() {
        // Create a float array
        float[] myFloatArray = {0.1F};
        // Create a new StyleImpl object with the specified parameters and return it according to the open status
        if (this.isopen) {
            return new StyleImpl(RGBColor.PINK, RGBColor.BLACK, 0.2F, myFloatArray);
        }
        return new StyleImpl(RGBColor.RED, RGBColor.BLACK, 0.2F, myFloatArray);
    }

    /**

     * Defines the behavior of the door.

     * The door opens if there is a robot within a certain distance.

     */

    @Override

    public void behave() {
    	boolean shouldOpen = false;

        for (Robot robot : this.getFactory().getRobots()) {

            if (distance(this, robot) < 100) {

                shouldOpen = true;

                break;

            }

        }

        this.setDoorstatus(shouldOpen);

    }
}
