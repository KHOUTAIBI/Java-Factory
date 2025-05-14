package model;

import fr.tp.inf112.projects.canvas.model.Shape;
import fr.tp.inf112.projects.canvas.model.Style;
import fr.tp.inf112.projects.canvas.model.impl.RGBColor;
import utilities.CircleShapeImpl;
import utilities.Point;
import utilities.RectangleShapeImpl;
import utilities.StyleImpl;

public class Puck extends Component {
	
	private static final long serialVersionUID = 202405101302L;
	// A unique ID for each puck
	private int id;
	// A static variable to keep track of the number of pucks
	static private int number = 1;
	// A static variable for the fixed side length of the puck
	static private int side = 20;
	
	// Constructor to create a new puck at the given coordinates
	public Puck(int xCoord, int yCoord, Factory factory) {
		super("Puck number " + Integer.toString(number), xCoord, yCoord, xCoord, yCoord, factory);
		this.id = number;
		number += 1;
	}
	
	// Returns the unique ID of the puck
	public int getId() {
		return id;
	}
	
	// Returns the adjusted x-coordinate of the puck
	@Override
	public int getxCoordinate() {
		return this.getxCoord() - side / 2;
	}
	
	// Returns the adjusted y-coordinate of the puck
	@Override
	public int getyCoordinate() {
		return this.getyCoord() - side / 2;
	}
	
	// Returns the fixed side length of the puck
	public static int getSide() {
		return side;
	}
	
	/**
     * Returns the style of the puck.
     *
     * @return the style of the puck
     */
    @Override
    public Style getStyle() {
        // Create a new style with a black fill color, gray border color,
        // a border width of 0.2, and a single float value of 0.1F
        float[] myFloatArray = {0.1F};
        Style style = new StyleImpl(RGBColor.BLACK, RGBColor.GRAY, 0.2F, myFloatArray);
        return style;
    }

    /**
     * Returns the shape of the puck.
     *
     * @return the shape of the puck
     */
    @Override
    public Shape getShape() {
        // Return a new rectangle shape with the fixed side length
        return new RectangleShapeImpl(side, side);
    }
	
	// Returns false, indicating that the puck does not overlap with any other object
	@Override
	public boolean overlays(Point point) {
		return false;
	}
	
	// Moves the puck in the specified direction (up, down, left, right)
	// at the given rate
	public boolean moveInConvoyor(String direction, int rate) {
		
		int xStep;
		int yStep;
		
		if(direction.equals("up")){
	        xStep = 0;
	        yStep = - rate;
	    }
	    else if(direction.equals("down")) {
	    	xStep = 0;
	        yStep = rate;
	    }
	    else if(direction.equals("right")) {
	    	xStep = rate;
	        yStep = 0;
	    }
	    else if(direction.equals("left")) {
	    	xStep = - rate;
	        yStep = 0;
	    }
	    else{
	        System.out.println("direction is incorrect");
	        return false;
	    }
		
		// Update the puck's x and y coordinates
		this.setxCoordinates(this.getxCoord() + xStep);
		this.setyCoordinates(this.getyCoord() + yStep);
		
		// Return true to indicate that the puck was moved successfully
		return true;
	}
}