package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Collection;
import fr.tp.inf112.projects.canvas.model.*;
import fr.tp.inf112.projects.canvas.model.impl.*;
import utilities.StyleImpl;
import fr.tp.inf112.projects.canvas.controller.*;



public class Factory implements Canvas, Observable, Serializable
{
	
	private String name;
	private ArrayList<Component> components;
	private transient HashSet<Observer> observers;
	private boolean started;
	private String id;
	public static final long serialVersionUID = 202405101152L;
	
	
	public Factory(String name) {
		super();
		this.name = name;
		this.components = new ArrayList<Component>();
		this.observers = new HashSet<>();
		this.started = false;
		this.id = name;
	}
	
	private Boolean checkRobot(String name) {
		for ( Component robot : components ) {
			if (robot instanceof Robot && name.equals(robot.getName())) {
					return false;	
			}
		}
		return true;
	}
	
	
	public Robot addRobot(String name, int xCoord, int yCoord, int radius, int speed, Room room , Factory factory) {
		if (checkRobot(name)) {
			Robot newRobot = new Robot(name, xCoord, yCoord, radius, speed, factory) ;
			components.add(newRobot) ;
			room.addRobot(newRobot);
			return newRobot ;
		}
		else {return null;}
	}
	
	
	public Room addRoom(String name, int xCoord, int yCoord, int xlenght, int ylenght, Factory factory) {
		Room newRoom = new Room(name, xCoord, yCoord, xlenght, ylenght, factory);
		components.add(newRoom);
		return newRoom;
	}
	
	public Convoyor addConvoyor(String name, int xCoord, int yCoord, int xlenght, int ylenght, int rate, Area targetArea, String direction, Factory factory) {
		Convoyor newConvoyor = new Convoyor(name, xCoord, yCoord, xlenght, ylenght, rate, targetArea, direction, factory);
		components.add(newConvoyor);
		return newConvoyor;
	}
	
	public ChargingStation addChargingStation(String name, int xCoord, int yCoord, int xLenght, int yLenght, Room room, Factory factory) {
		ChargingStation newChargingStation = new ChargingStation(name, xCoord, yCoord, xLenght, yLenght, factory);
		components.add(newChargingStation);
		room.addChargingStation(newChargingStation);
		return newChargingStation;
	}
	
	public Area addArea(String name, int xCoord, int yCoord, int xLenght, int yLenght, Room room, Factory factory) {
		Area newArea = new Area(name, xCoord, yCoord, xLenght, yLenght, factory);
		components.add(newArea);
		room.addArea(newArea);
		return newArea;
	}
	
	public Boolean addProdUnit(String name, int xCoord, int yCoord, int xLenght, int yLenght, float rate, Room room, Factory factory) {
		ProdUnit newProdUnit = new ProdUnit(name, xCoord, yCoord, xLenght, yLenght, rate, factory);
		components.add(newProdUnit);
		room.addProdUnit(newProdUnit);
		return true;
	}
		
	@Override
	public String toString() {
		return "Factory [name=" + name + ", robots=" + components + "]";
	}

	public void printToConsole() {
		System.out.println(this);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getWidth() {
		return 2000;
	}

	@Override
	public int getHeight() {
		return 1000;
	}
	
	protected HashSet<Observer> getObservers() {
			if (observers == null) {
				observers = new HashSet<>();
			}
			return observers;
		}
	
	@Override
	public Collection<Figure> getFigures() {
		return (Collection)components;
	}
	
	public boolean addComponent(Component component) {	
		if (components.add(component))
		{
			return true;
		}
		return false;
	}
	
	public Component getComponent(int i) {
		return components.get(i);
	}
	
	@Override
	public Style getStyle() {
		float[] myFloatArray = {0.1F};
		Style style = new StyleImpl(RGBColor.BLUE, RGBColor.WHITE, 0.2F, myFloatArray);
		return style;
	}
	
	
	public boolean addObserver(Observer observer) {
		return observers.add(observer);
	}
	
	
	public boolean removeObserver(Observer observer) {
		return observers.remove(observer);
	}
	
	protected void notifyObservers() {
		// To be called every time model data is modified
		for (final Observer observer : observers) {
			observer.modelChanged();
		}
	
	}
	
	public void behave() {
		for (Component component : this.components) {
			component.behave();
		}
	}
	
	public void startSimulation() {
		started = true;
		this.notifyObservers();
	}
	
	public boolean isSimulationStarted() {
		return started;
	}
	
	public void stopSimulation() {
		started = false;
		this.notifyObservers();
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
		
	}
}
	
	

