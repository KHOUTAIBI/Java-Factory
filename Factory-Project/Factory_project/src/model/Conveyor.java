package model;

import java.util.ArrayList;

import fr.tp.inf112.projects.canvas.model.Style;
import fr.tp.inf112.projects.canvas.model.impl.RGBColor;
import utilities.StyleImpl;

public class Conveyor extends Area {
	private static final long serialVersionUID = 202405101303L;
	// The rate at which the conveyor belt moves
	private int rate;
	// The target area where the conveyor belt delivers the pucks
	private Area targetArea;
	// The direction in which the conveyor belt moves
	private String direction;
	// The factory that the conveyor belt belongs to
	//private Rectangle booth;

	/**
	 * Constructor to create a new conveyor belt with the given parameters.
	 *
	 * @param name                  the name of the conveyor belt
	 * @param xCoord                the x-coordinate of the conveyor belt
	 * @param yCoord                the y-coordinate of the conveyor belt
	 * @param xLenght               the x-length of the conveyor belt
	 * @param yLenght               the y-length of the conveyor belt
	 * @param destXCoord            the destination x-coordinate of the conveyor belt
	 * @param destYCoord            the destination y-coordinate of the conveyor belt
	 * @param rate                  the rate at which the conveyor belt moves
	 * @param targetArea            the target area where the conveyor belt delivers the pucks
	 * @param direction             the direction in which the conveyor belt moves
	 * @param factory               the factory that the conveyor belt belongs to
	 */
	public Conveyor(String name, int xCoord, int yCoord, int xLenght, int yLenght, int destXCoord, int destYCoord, int rate, Area targetArea,
			String direction , Factory factory) {
		super(name, xCoord, yCoord, xLenght, yLenght, destXCoord, destYCoord, factory);
		this.rate = rate;
		this.targetArea = targetArea;
		this.direction = direction;
	}

	/**
	 * Returns the style of the conveyor belt.
	 *
	 * @return the style of the conveyor belt
	 */
	@Override
	public Style getStyle() {
		float[] myFloatArray = { 0.1F, 0.1F };
		Style style = new StyleImpl(RGBColor.LIGHT_GRAY, RGBColor.BLACK, 0.2F, myFloatArray);
		return style;
	}

	/**
	 * Returns the target area of the conveyor belt.
	 *
	 * @return the target area of the conveyor belt
	 */
	public Area getTargetArea() {
		return targetArea;
	}

	/**
	 * Performs the action of the conveyor belt on the given robot.
	 *
	 * @param robot the robot to perform the action on
	 * @return true if the action was successful, false otherwise
	 */
	@Override
	public boolean performAction(Robot robot) {
		return robot.putPuck(this);
	}

	/**
	 * Gives a puck to the conveyor belt.
	 *
	 * @param puck the puck to give to the conveyor belt
	 * @return true if the puck was successfully given to the conveyor belt, false otherwise
	 */
	public boolean givePuck(Puck puck) {
		this.getFactory().addComponent(puck);
		puck.setxCoordinates(this.getDestPoint().getxCoord() + this.getxLenght() / 2);
		puck.setyCoordinates(this.getDestPoint().getyCoord());
		this.getInventory().add(puck);
		return true;
	}

	/**
	 * Behaves like a conveyor belt, moving pucks along the belt and delivering them to the target area.
	 */
	public void behave() {
		ArrayList<Puck> inventoryCopy = new ArrayList<Puck>(this.getInventory());
		for (Puck puck : inventoryCopy) {
			if(this.overlays(puck.getCoordinates()) && this.targetArea.isNotFull()) {
				puck.moveInConvoyor(direction, rate);
			}
			else if (this.targetArea.isNotFull()) {
				targetArea.givePuck(puck);
				this.removePuck(puck);
			}
		}
	}
}
