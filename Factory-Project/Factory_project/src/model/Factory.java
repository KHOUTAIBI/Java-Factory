package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Collection;
import fr.tp.inf112.projects.canvas.model.*;
import fr.tp.inf112.projects.canvas.model.impl.*;
import utilities.StyleImpl;
import fr.tp.inf112.projects.canvas.controller.*;



public class Factory implements Canvas, Observable, Serializable {
	
	private String name;
	private ArrayList<Component> components;
	private transient HashSet<Observer> observers;
	private boolean started;
	private ArrayList<Robot> robots;
	private String id = null;
	private ArrayList<Component> Chargingstations;
	public static final long serialVersionUID = 202405101152L;
	
	
	public Factory(String name) {
		super();
		this.name = name;
		this.components = new ArrayList<Component>();
		this.observers = new HashSet<>();
		this.started = false;
		this.Chargingstations = new ArrayList<Component>();
		this.robots = new ArrayList<Robot>();
	}
	
	private Boolean checkRobot(String name) {
		ArrayList<Component> componentsCopy = new ArrayList<Component>(components);
		for ( Component robot : componentsCopy ) {
			if (robot instanceof Robot && name.equals(robot.getName())) {
					return false;	
			}
		}
		return true;
	}
	
	
	public Robot addRobot(String name, int xCoord, int yCoord, int speed, Room room) {
		if (checkRobot(name)) {
			Robot newRobot = new Robot(name, xCoord, yCoord, speed, this) ;
			this.components.add(newRobot);
			this.robots.add(newRobot);
			return newRobot ;
		}
		else {return null;}
	}
	
	
	public Room addRoom(String name, int xCoord, int yCoord, int xlenght, int ylenght) {
		Room newRoom = new Room(name, xCoord, yCoord, xlenght, ylenght, this);
		this.components.add(newRoom);
		return newRoom;
	}
	
	public Conveyor addConveyor(String name, int xCoord, int yCoord, int xlenght, int ylenght, int destXCoord, int destYCoord, int rate, Area targetArea, String direction) {
		Conveyor newConvoyor = new Conveyor(name, xCoord, yCoord, xlenght, ylenght, destXCoord, destYCoord, rate, targetArea, direction, this);
		this.components.add(newConvoyor);
		return newConvoyor;
	}
	
	public ChargingStation addChargingStation(String name, int xCoord, int yCoord, int xLenght, int yLenght, int destXCoord, int destYCoord, Room room) {
		ChargingStation newChargingStation = new ChargingStation(name, xCoord, yCoord, xLenght, yLenght, destXCoord, destYCoord, this);
		this.components.add(newChargingStation);
		this.Chargingstations.add(newChargingStation);
		return newChargingStation;
	}
	
	public Area addArea(String name, int xCoord, int yCoord, int xLenght, int yLenght, int destXCoord, int destYCoord, Room room) {
		Area newArea = new Area(name, xCoord, yCoord, xLenght, yLenght, destXCoord, destYCoord, this);
		this.components.add(newArea);
		room.addArea(newArea);
		return newArea;
	}
	
	public ProdUnit addProdUnit(String name, int xCoord, int yCoord, int xLenght, int yLenght, int destXCoord, int destYCoord, int rate, Area targetArea, Room room) {
		ProdUnit newProdUnit = new ProdUnit(name, xCoord, yCoord, xLenght, yLenght, rate, destXCoord, destYCoord, targetArea, this);
		this.components.add(newProdUnit);
		room.addProdUnit(newProdUnit);
		return newProdUnit;
	}
	
	public Door addDoor(String name, int xCoord, int yCoord, int xLenght, int yLenght,Room room) {
		Door newDoor = new Door(name, xCoord, yCoord, xLenght, yLenght, "right" , this);
		this.components.add(newDoor);
		room.addDoor(newDoor);
		return newDoor;
	}
	
	public Wall addWall(String name,int xCoord, int yCoord, int xLenght, int yLenght,Room room) {
		Wall newWall = new Wall(name,xCoord, yCoord, xLenght, yLenght, this);
		this.components.add(newWall);
		room.addWall(newWall);
		return newWall;
	}
	
	public Puck addPuck(int xCoord, int yCoord) {
		Puck newPuck  = new Puck(xCoord,yCoord, this);
		this.components.add(newPuck);
		return newPuck;
	}
	
	public ArrayList<Component> getChargingstations(){
		return this.Chargingstations;
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
	public boolean addComponent(Component component) {	
		if (components.add(component))
		{
			return true;
		}
		return false;
	}
	
	// Method to get a component by its index
	public Component getComponent(int i) {
		return components.get(i);
	}
		
	// Getter for the list of components
	public ArrayList<Component> getComponents() {
		return this.components;
	}
	
	public ArrayList<Robot> getRobots(){
		return this.robots;
	}
	
	@Override
	public int getWidth() {
		return 2000;
	}

	@Override
	public int getHeight() {
		return 1000;
	}
	
	@Override
	public Collection<Figure> getFigures() {
		return (Collection) new ArrayList<Component>(components);
	}
	
	@Override
	public Style getStyle() {
		float[] myFloatArray = {0.1F};
		Style style = new StyleImpl(RGBColor.BLUE, RGBColor.WHITE, 0.2F, myFloatArray);
		return style;
	}
	
	protected HashSet<Observer> getObservers() {
			if (observers == null) {
				observers = new HashSet<>();
			}
			return observers;
	}
	
	@Override
	public boolean addObserver(Observer observer) {
		
		return this.getObservers().add(observer);
	}
	
	@Override
	public boolean removeObserver(Observer observer) {
		return this.getObservers().add(observer);
	}
	
	protected void notifyObservers() {
		// To be called every time model data is modified
		for (final Observer observer : observers) {
			observer.modelChanged();
		}
	}
	
	public void behave() {
		ArrayList<Component> componentsCopy = new ArrayList<Component>(components);
		for (Component component : componentsCopy) {
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
	
	

