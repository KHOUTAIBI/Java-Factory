package model;

import java.util.ArrayList;

import fr.tp.inf112.projects.canvas.model.Shape;
import utilities.Door;
import utilities.RectangleShapeImpl;

public abstract class Rectangle extends Component {
	private int xLenght;
	private int yLenght;
	private ArrayList<Door> doors;
	private static final long serialVersionUID = 202405101305L;
	
	public Rectangle(String name, int xCoord, int yCoord, int xLenght, int yLenght, Factory factory) {
		super(name, xCoord, yCoord, factory);
		this.xLenght = xLenght;
		this.yLenght = yLenght;
		this.doors = new ArrayList<Door>();
	}
	
	public Shape getShape() {
		return new RectangleShapeImpl(this.xLenght, this.yLenght);
	}
	
	public boolean addDoor(String side, int position1, int position2) {
		if (position1 > position2) {
			int stock = position1;
			position1 = position2;
			position2 = stock;
		}
		
		int thisRectangleXCoord = getxCoordinate();
		int thisRectangleYCoord = getyCoordinate();
		Door newDoor;
		
		if (position1 < 0) return false;
		switch(side) {
			case "left" :
				if (position2 > this.yLenght) return false;
				newDoor = new Door(thisRectangleXCoord, thisRectangleYCoord + position1, thisRectangleXCoord, thisRectangleYCoord + position2, side);
				break;
			case "right" :
				if (position2 > this.yLenght) return false;
				newDoor = new Door(thisRectangleXCoord + xLenght, thisRectangleYCoord + position1, thisRectangleXCoord + xLenght, thisRectangleYCoord + position2, side);
				break;
			case "top" :
				if (position2 > this.xLenght) return false;
				newDoor = new Door(thisRectangleXCoord + position1, thisRectangleYCoord, thisRectangleXCoord + position2, thisRectangleYCoord, side);
				break;
			case "bottom" :
				if (position2 > this.xLenght) return false;
				newDoor = new Door(thisRectangleXCoord + position1, thisRectangleYCoord + yLenght, thisRectangleXCoord + position2, thisRectangleYCoord + yLenght, side);
				break;
			default :
				return false;
		}
		
		for (Door door : doors) {
			if (newDoor.doesCollide(door)) return false;
		}
		
		doors.add(newDoor);
		return true;
	}
		
}
