package model;

import java.io.Serializable;

public class Battery implements Serializable {
	private int powerLevel;
	private static final long serialVersionUID = 202405101258L;


	public Battery() {
		this.powerLevel = 100;
	}
}
