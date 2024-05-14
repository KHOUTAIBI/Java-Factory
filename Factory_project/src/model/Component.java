package model;

import java.io.Serializable;

import fr.tp.inf112.projects.canvas.model.Figure;
import utilities.Point;

public abstract class Component implements Figure, Serializable {
	private String name;
	private Point coordinates;
	private Factory factory;
	private static final long serialVersionUID = 202405101153L;
	
	Component(String name, int xCoord, int yCoord, Factory factory) {
		this.name = name;
		this.coordinates = new Point(xCoord, yCoord);
		this.factory = factory;
	}
	
	public final int getxCoordinate() {
		return coordinates.getxCoord();
	}
	
	public final int getyCoordinate() {
		return coordinates.getyCoord();
	}
	
	public void setxCoordinates(int xCoordinate) {
		(this.coordinates).setxCoord(xCoordinate);
		factory.notifyObservers();
	}

	public void setyCoordinates(int yCoordinate) {
		(this.coordinates).setyCoord(yCoordinate);
		factory.notifyObservers();
	}

	public String getName() {
		return name;
	}
	
	public void behave()
	{
	}
	
	public void setFactory(Factory factory)
	{
		// TODO should be removed because factory should be final
		this.factory = factory;
	}
	
}
