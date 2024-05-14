package model;

import fr.tp.inf112.projects.canvas.model.Shape;
import fr.tp.inf112.projects.canvas.model.Style;
import fr.tp.inf112.projects.canvas.model.impl.RGBColor;
import utilities.CircleShapeImpl;
import utilities.StyleImpl;

public class Puck extends Component{
	
	private static final long serialVersionUID = 202405101302L;
	private int id;
	static private int number = 1;
	
	public Puck(String name, int xCoord, int yCoord, Factory factory) {
		super("Puck number " + Integer.toString(number), xCoord, yCoord, factory);
		this.id = number;
		number =+ 1;
	}

	public int getId() {
		return id;
	}

	@Override
	public Style getStyle() {
		float[] myFloatArray = {0.1F};
		Style style = new StyleImpl(RGBColor.GREEN, RGBColor.BLACK, 0.2F, myFloatArray);
		return style;
	}

	@Override
	public Shape getShape() {
		return new CircleShapeImpl(10);
	}
}