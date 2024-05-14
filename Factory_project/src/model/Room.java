package model;

import java.util.ArrayList;

import fr.tp.inf112.projects.canvas.model.Style;
import fr.tp.inf112.projects.canvas.model.impl.RGBColor;
import utilities.StyleImpl;

public class Room extends Rectangle {
	private ArrayList<Robot> robots;
	private ArrayList<Rectangle> surfaces;
	private static final long serialVersionUID = 202405101306L;

	public Room(String name, int xCoord, int yCoord, int xlenght, int ylenght, Factory factory) {
		super(name, xCoord, yCoord, xlenght, ylenght, factory);
		this.robots = new ArrayList<Robot>();
		this.surfaces = new ArrayList<Rectangle>();
	}
	
	// 	addRobot is only used by Factory how add robots in the room it wants
	public void addRobot(Robot robot) {
		robots.add(robot);
	}
	// same as addRobot
	void addChargingStation(ChargingStation chargingStation) {
		surfaces.add(chargingStation);
	}
	
	void addArea(Area area) {
		surfaces.add(area);
	}
	
	void addProdUnit(ProdUnit prodUnit) {
		surfaces.add(prodUnit);
	}

	@Override
	public Style getStyle() {
		float[] myFloatArray = {0.1F};
		Style style = new StyleImpl(RGBColor.WHITE, RGBColor.BLACK, 0.2F, myFloatArray);
		return style;
	}
	
}
