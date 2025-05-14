package model;

import java.util.ArrayList;

import fr.tp.inf112.projects.canvas.model.Style;
import fr.tp.inf112.projects.canvas.model.impl.RGBColor;
import utilities.Point;
import utilities.StyleImpl;

public class ChargingStation extends Rectangle {
	
	private boolean hasRobot;
	private static final long serialVersionUID = 202405101309L;
	// The amount of charge that a robot gets when it charges at the charging station
	private static double chargeQuantity = 0.05; 

	/**
	 * Constructor to create a new charging station with the given parameters.
	 *
	 * @param name                  the name of the charging station
	 * @param xCoord                the x-coordinate of the charging station
	 * @param yCoord                the y-coordinate of the charging station
	 * @param xLenght               the x-length of the charging station
	 * @param yLenght               the y-length of the charging station
	 * @param destXCoord            the destination x-coordinate of the charging station
	 * @param destYCoord            the destination y-coordinate of the charging station
	 * @param factory               the factory that the charging station belongs to
	 */
	public ChargingStation(String name, int xCoord, int yCoord, int xLenght, int yLenght, int destXCoord, int destYCoord, Factory factory) {
		super(name, xCoord, yCoord, xLenght, yLenght, destXCoord, destYCoord, factory);
		this.hasRobot = false;
	}

	/**
	 * Returns the style of the charging station.
	 *
	 * @return the style of the charging station
	 */
	@Override
	public Style getStyle() {
		float[] myFloatArray = {0.2F, 0.1F};
		Style style = new StyleImpl(RGBColor.YELLOW, RGBColor.GRAY, 0.2F, myFloatArray);
		return style;
	}

	/**
	 * Returns false, as the charging station does not overlap with any points.
	 *
	 * @param point the point to check for overlap
	 * @return false
	 */
	@Override
    public boolean overlays(Point point) {
    	return false;
    }

	/**
	 * Checks if the charging station overlaps with a given robot.
	 *
	 * @param robot the robot to check for overlap
	 * @return true if the charging station overlaps with the robot, false otherwise
	 */
	public boolean overlaysRobot(Robot robot) {
		return ((this.getxCoord() - Robot.getRadius()) < robot.getxCoord() && (this.getxCoord() + this.getxLenght() + Robot.getRadius()) > robot.getxCoord()) &&
	               ((this.getyCoord() - Robot.getRadius()) < robot.getyCoord() && (this.getyCoord() + this.getyLenght() + Robot.getRadius()) > robot.getyCoord());
	}

	/**
	 * Behaves like a charging station, charging any robots that are on top of it.
	 */
	public void behave() {
		ArrayList<Robot> RobotsCopy = new ArrayList<Robot>(this.getFactory().getRobots());
		Rectangle thisChargSta = (Rectangle) this;
		for(Robot robot : RobotsCopy) {
			if(overlaysRobot(robot)) {
				robot.setCharging(!robot.charge(chargeQuantity));
			}
		}
	}
}
