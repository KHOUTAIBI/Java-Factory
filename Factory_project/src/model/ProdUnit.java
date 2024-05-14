package model;

import fr.tp.inf112.projects.canvas.model.Style;
import fr.tp.inf112.projects.canvas.model.impl.RGBColor;
import utilities.StyleImpl;

public class ProdUnit extends Area {
	private float prodRate;
	private static final long serialVersionUID = 202405101305L;

	public ProdUnit(String name, int xCoord, int yCoord, int xLenght, int yLenght, float prodRate , Factory factory) {
		super(name, xCoord, yCoord, xLenght, yLenght, factory);
		this.prodRate = prodRate;
	}
	
	@Override
	public Style getStyle() {
		float[] myFloatArray = {0.1F, 0.1F};
		Style style = new StyleImpl(RGBColor.PINK, RGBColor.GRAY, 0.2F, myFloatArray);
		return style;
	}

}
