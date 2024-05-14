package utilities;

import java.io.Serializable;

public class Door implements Serializable{
	private Point point1;
	private Point point2;
	private String side;
	private static final long serialVersionUID = 202405101300L;

	
	public Door(int xCoord1, int yCoord1, int xCoord2, int yCoord2, String side) {
		this.point1 = new Point(xCoord1, yCoord1);
		this.point2 = new Point(xCoord2, yCoord2);
		this.side = side;
	}

	public Point getPoint1() {
		return point1;
	}

	public void setPoint1(Point point1) {
		this.point1 = point1;
	}

	public Point getPoint2() {
		return point2;
	}

	public void setPoint2(Point point2) {
		this.point2 = point2;
	}

	public String getSide() {
		return side;
	}
	
	public boolean doesCollide(Door otherDoor) {
		// TODO 
		return false;
	}

}