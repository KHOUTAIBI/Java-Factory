package model;

import java.io.Serializable;

// Import necessary classes
import fr.tp.inf112.projects.canvas.model.Style;
import fr.tp.inf112.projects.canvas.model.impl.RGBColor;
import utilities.Point;
import utilities.StyleImpl;

/**
 * ProdUnit class representing a production unit in the factory.
 * Implements the Serializable interface.
 */
public class ProdUnit extends Area implements Serializable {
    // Class variables to store the target area, production rate, and rate counter
    private Area targetArea;
    private int prodRate;
    private static final long serialVersionUID = 202405101305L;
    private int rateCounter;

    /**
     * Constructor to initialize the ProdUnit object.
     *
     * @param name           the name of the production unit
     * @param xCoord         the x-coordinate of the production unit
     * @param yCoord         the y-coordinate of the production unit
     * @param xLenght        the x-length of the production unit
     * @param yLenght        the y-length of the production unit
     * @param prodRate       the production rate of the production unit
     * @param destXCoord     the x-coordinate of the destination
     * @param destYCoord     the y-coordinate of the destination
     * @param targetArea     the target area for the production unit
     * @param factory        the factory that the production unit belongs to
     */
    public ProdUnit(String name, int xCoord, int yCoord, int xLenght, int yLenght, int prodRate, int destXCoord, int destYCoord, Area targetArea, Factory factory) {
        // Initialize the Area object with the given parameters
        super(name, xCoord, yCoord, xLenght, yLenght, destXCoord, destYCoord, factory);
        // Initialize the targetArea variable with the given target area
        this.targetArea = targetArea;
        // Initialize the prodRate variable with the given production rate
        this.prodRate = prodRate;
        // Initialize the rateCounter variable to 0
        this.rateCounter = 0;
    }

    /**
     * Returns the style of the production unit.
     *
     * @return the style of the production unit
     */
    @Override
    public Style getStyle() {
        // Create a float array
        float[] myFloatArray = {0.1F, 0.1F};
        // Create a new StyleImpl object with the specified parameters and return it
        Style style = new StyleImpl(RGBColor.MAGENTA, RGBColor.GRAY, 0.2F, myFloatArray);
        return style;
    }

    @Override
    public boolean overlays(Point point) {
        // Check if the point is within the bounds of the production unit
        return ((this.getxCoord() - Robot.getRadius()) < point.getxCoord() && (this.getxCoord() + this.getxLenght() + Robot.getRadius()) > point.getxCoord()) &&
               ((this.getyCoord() - Robot.getRadius()) < point.getyCoord() && (this.getyCoord() + this.getyLenght() + Robot.getRadius()) > point.getyCoord());
    }

    @Override
    public void behave() {
        // Increment the rateCounter variable
        rateCounter++;
        // If the rateCounter is a multiple of the production rate
        if (rateCounter % prodRate == 0) {
            // Call the givePuck() method of the targetArea
            targetArea.givePuck();
        }
    }

}
