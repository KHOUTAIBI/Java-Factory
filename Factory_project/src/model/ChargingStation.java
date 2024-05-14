package model;

import fr.tp.inf112.projects.canvas.model.Style;
import fr.tp.inf112.projects.canvas.model.impl.RGBColor;
import utilities.StyleImpl;

public class ChargingStation extends Rectangle {
	
	private boolean hasRobot;
	private static final long serialVersionUID = 202405101309L;

	public ChargingStation(String name, int xCoord, int yCoord, int xLenght, int yLenght, Factory factory) {
		super(name, xCoord, yCoord, xLenght, yLenght, factory);
		this.hasRobot = false;
	}

	@Override
	public Style getStyle() {
		float[] myFloatArray = {0.2F, 0.1F};
		Style style = new StyleImpl(RGBColor.YELLOW, RGBColor.GRAY, 0.2F, myFloatArray);
		return style;
	}
	
}
