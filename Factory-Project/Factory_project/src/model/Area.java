package model;

import java.util.ArrayList;

import fr.tp.inf112.projects.canvas.model.Shape;
import fr.tp.inf112.projects.canvas.model.Style;
import fr.tp.inf112.projects.canvas.model.impl.RGBColor;
import utilities.Point;
import utilities.StyleImpl;

public class Area extends Rectangle {
	
	// A unique identifier for the area
	private static final long serialVersionUID = 202405101303L;
	
	// The inventory of pucks in the area
	private ArrayList<Puck> inventory;
	
	// The total capacity of the area in terms of the number of pucks it can hold
	private int totalCapacityX;
	private int totalCapacityY;

	/**
	 * Constructor to create a new area with the given parameters.
	 *
	 * @param name                  the name of the area
	 * @param xCoord                the x-coordinate of the area
	 * @param yCoord                the y-coordinate of the area
	 * @param xLenght               the x-length of the area
	 * @param yLenght               the y-length of the area
	 * @param destXCoord            the destination x-coordinate of the area
	 * @param destYCoord            the destination y-coordinate of the area
	 * @param factory               the factory that the area belongs to
	 */
	public Area(String name, int xCoord, int yCoord, int xLenght, int yLenght, int destXCoord, int destYCoord, Factory factory) {
		super(name, xCoord, yCoord, xLenght, yLenght, destXCoord, destYCoord, factory);
		this.inventory = new ArrayList<Puck>();
		this.totalCapacityX = (this.getxLenght() / Puck.getSide());
		this.totalCapacityY = (this.getyLenght() / Puck.getSide());
	}

	/**
	 * Returns the style of the area.
	 *
	 * @return the style of the area
	 */
	@Override
	public Style getStyle() {
		float[] myFloatArray = {0.1F, 0.1F};
        Style style = new StyleImpl(RGBColor.ORANGE, RGBColor.GRAY, 0.2F, myFloatArray);
        return style;
	}

	/**
	 * Adds a puck to the inventory of the area.
	 *
	 * @return true if the puck was successfully added, false otherwise
	 */
	public Boolean givePuck() {
		int pucksNumber = inventory.size();
		if (pucksNumber < totalCapacityX * totalCapacityY) {
			int xRow = pucksNumber / totalCapacityX;
			int yRow = pucksNumber % totalCapacityX;
			Puck newPuck = this.getFactory().addPuck(this.getxCoord() + xRow * Puck.getSide() + Puck.getSide() / 2,
													 this.getyCoord() + yRow * Puck.getSide() + Puck.getSide() / 2);
			this.inventory.add(newPuck);
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Allows a robot to take a puck from the inventory of the area.
	 *
	 * @param robot the robot that is taking the puck
	 * @return true if the robot was able to take a puck, false otherwise
	 */
	public boolean performAction(Robot robot) {
		return robot.takePuck(this);
	}

	/**
	 * Returns the inventory of pucks in the area.
	 *
	 * @return the inventory of pucks in the area
	 */
	public ArrayList<Puck> getInventory(){
		return this.inventory;
	}

	/**
	 * Returns the last puck in the inventory of the area.
	 *
	* @return the last puck in the inventory of the area
	 */
	public Puck getLastPuck() {
		return inventory.get(inventory.size() - 1);
	}

	/**
	 * Checks if the area is not full.
	 *
	 * @return true if the area is not full, false otherwise
	 */
	public boolean isNotFull() {
		return inventory.size() < totalCapacityX * totalCapacityY;
	}

	/**
	 * Adds a given puck to the inventory of the area.
	 *
	 * @param puck the puck to add to the inventory
	 * @return true if the puck was successfully added, false otherwise
	 */
	public boolean givePuck(Puck puck) {
		int pucksNumber = inventory.size();
		if (this.isNotFull()) {
			int xRow = pucksNumber / totalCapacityX;
			int yRow = pucksNumber % totalCapacityX;
			puck.setxCoordinates(this.getxCoord() + xRow * Puck.getSide() + Puck.getSide() / 2);
			puck.setyCoordinates(this.getyCoord() + yRow * Puck.getSide() + Puck.getSide() / 2);
			this.getFactory().addComponent(puck);
			this.inventory.add(puck);
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Removes the last puck from the inventory of the area.
	 *
	 * @return true if a puck was successfully removed, false otherwise
	 */
	public boolean removePuck() {
		if (this.inventory.size()==0) return false;
		Puck puck = this.inventory.get(this.inventory.size() - 1);
		this.inventory.remove(puck);
		this.getFactory().getComponents().remove(puck);
		return false;
	}

	/**
	 * Removes a given puck from the inventory of the area.
	 *
	 * @param puck the puck to remove from the inventory
	 * @return true if the puck was successfully removed, false otherwise
	 */
	public boolean removePuck(Puck puck) {
		if (this.inventory.size()==0) return false;
		this.inventory.remove(puck);
		this.getFactory().getComponents().remove(puck);
		return false;
	}
}
