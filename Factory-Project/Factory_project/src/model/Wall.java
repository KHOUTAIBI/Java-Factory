package model;

import java.io.Serializable;

import fr.tp.inf112.projects.canvas.model.Style;
import fr.tp.inf112.projects.canvas.model.impl.RGBColor;
import utilities.StyleImpl;

public class Wall extends Rectangle implements Serializable {

	// Class variables to store the coordinates of the two points defining the Wall and its side
    private String side;


    // Serial version UID for serialization
    private static final long serialVersionUID = 202405101300L;

    /**
     * Constructor to initialize the Door object.
     *
     * @param xCoord the x-coordinate of the first point
     * @param yCoord the y-coordinate of the first point
    */
    
    public Wall(String name,int xCoord, int yCoord,int xLenght, int yLenght ,Factory factory) {
        // Initialize the point1 and point2 variables with the given coordinates
    	super(name, xCoord, yCoord, xLenght, yLenght, xCoord, yCoord, factory);
    }   

    /**
     * Returns the side of the door.
     *
     * @return the side of the door
     */
    public String getSide() {
        return this.side;
    }

	@Override
	public Style getStyle() {
		// Create a float array
        float[] myFloatArray = {0.1F};
        // Create a new StyleImpl object with the specified parameters and return it according to the open status
        return new StyleImpl(RGBColor.GRAY, RGBColor.BLACK, 0.2F, myFloatArray);
	}
	
}


