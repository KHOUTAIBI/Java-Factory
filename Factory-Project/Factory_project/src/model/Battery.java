package model;

import java.io.Serializable;

public class Battery implements Serializable {
	
	// The current power level of the battery
	private double powerLevel;
	
	// A unique identifier for the battery
	private static final long serialVersionUID = 202405101258L;
	
	// The robot that the battery belongs to
	private Robot robot;

	/**
	 * Constructor to create a new battery with the given robot.
	 *
	 * @param robot the robot that the battery belongs to
	 */
	public Battery(Robot robot) {
		this.powerLevel = 1;
		this.robot = robot;
	}

	/**
	 * Reduces the power level of the battery by the given amount.
	 *
	 * @param powerReduc the amount to reduce the power level by
	 */
	public void reduceBatterypower(double powerReduc) {
		this.powerLevel *= powerReduc;
	}

	/**
	 * Increases the power level of the battery by the given amount.
	 *
	 * @param quantity the amount to increase the power level by
	 * @return true if the power level was increased above 1, false otherwise
	 */
	public boolean increaseBatteryPower(double quantity) {
		double newCharge = powerLevel + quantity;
		if (newCharge > 1) {
			powerLevel = 1;
			return true;
		}
		else {
			powerLevel = newCharge;
			return false;
		}
	}

	/**
	 * Returns the current power level of the battery.
	 *
	 * @return the current power level of the battery
	 */
	public double getBatterypower() {
		return this.powerLevel;
	}
}
