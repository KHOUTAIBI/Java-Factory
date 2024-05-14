package model;

import fr.tp.inf112.projects.canvas.model.Style;
import fr.tp.inf112.projects.canvas.model.impl.RGBColor;
import utilities.StyleImpl;

public class Convoyor extends Area {
	private static final long serialVersionUID = 202405101303L;
	private float rate;
	private Area targetArea;
	private String direction;
	//private Rectangle booth;
	
	public Convoyor(String name, int xCoord, int yCoord, int xLenght, int yLenght, float rate, Area targetArea,
			String direction , Factory factory) {
		super(name, xCoord, yCoord, xLenght, yLenght, factory);
		this.rate = rate;
		this.targetArea = targetArea;
		this.direction = direction;
	}
	
	@Override
	public Style getStyle() {
		float[] myFloatArray = {0.1F, 0.1F};
		Style style = new StyleImpl(RGBColor.GRAY, RGBColor.BLACK, 0.2F, myFloatArray);
		return style;
	}
	
	
}
