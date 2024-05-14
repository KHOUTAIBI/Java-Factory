package model;

import java.util.ArrayList;


import fr.tp.inf112.projects.canvas.model.Style;
import fr.tp.inf112.projects.canvas.model.impl.RGBColor;
import utilities.StyleImpl;

public class Area extends Rectangle 
{
	private static final long serialVersionUID = 202405101303L;
	private ArrayList<Puck> inventory;

	public Area(String name, int xCoord, int yCoord, int xLenght, int yLenght, Factory factory) {
		super(name, xCoord, yCoord, xLenght, yLenght, factory);
		this.inventory = new ArrayList<Puck>();
	}

	@Override
	public Style getStyle() {
		float[] myFloatArray = {0.1F, 0.1F};
		Style style = new StyleImpl(RGBColor.CYAN, RGBColor.GRAY, 0.2F, myFloatArray);
		return style;
	}
	
}
