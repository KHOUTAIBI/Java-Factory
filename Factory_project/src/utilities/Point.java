package utilities;

import java.io.Serializable;

public class Point implements Serializable {
	private int xCoord;
	private int yCoord;
	private static final long serialVersionUID = 202405101155L;
	
	public Point(int xCoord, int yCoord) {
		this.xCoord = xCoord;
		this.yCoord = yCoord;
	}

	public int getxCoord() {
		return xCoord;
	}

	public void setxCoord(int xCoord) {
		this.xCoord = xCoord;
	}

	public int getyCoord() {
		return yCoord;
	}

	public void setyCoord(int yCoord) {
		this.yCoord = yCoord;
	}
	
	public int getSquareNorm() {
		return (this.xCoord*this.xCoord) + (this.yCoord*this.yCoord);
	}
}
