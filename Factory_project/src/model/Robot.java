package model;

import fr.tp.inf112.projects.canvas.model.Shape;
import java.util.ArrayList;
import fr.tp.inf112.projects.canvas.model.Style;
import fr.tp.inf112.projects.canvas.model.impl.RGBColor;
import utilities.CircleShapeImpl;
import utilities.StyleImpl;

public class Robot extends Component {
	private int radius;
	private int speed;
	private Puck inventory;
	private Battery battery;
	private ArrayList<Component> toVisit;
	private static final long serialVersionUID = 202405101305L;

	
	public Robot(String name, int xCoord, int yCoord, int radius, int speed, Factory factory) {
		super(name, xCoord, yCoord, factory);
		this.radius = radius;
		this.speed = speed;
		this.inventory = null;
		this.battery = new Battery();
		this.toVisit = new ArrayList<Component>();
	}
	
	public void setComponents(ArrayList<Component> components) {
		this.toVisit= components;
	}
	
	public void AddComponentToVisit(Component component) {
		(this.toVisit).add(component);
	}
	
	
	@Override
	public Style getStyle() {
		float[] myFloatArray = {0.1F};
		Style style = new StyleImpl(RGBColor.BLACK, RGBColor.BLACK, 0.2F, myFloatArray);
		return style;
	}
	@Override
	public Shape getShape() {
		return new CircleShapeImpl(radius);
	}
	
	public void move(int x, int y) {	
		// We define Vect((xCoord-x,yXcoo-y)), a vector space of all vectors in the line between the starting point and the end point of our trajectory
		// multiplying any vector of this vector space by this scalar gives us points existing in the trajectory
		
		// TODO in the function speed varies with direction
		
		float VectX = this.getxCoordinate() - x; 	
		float VectY = this.getyCoordinate() - y;
		float director = VectY/VectX;
		int xCoo = this.getxCoordinate();
		int y0 = this.getyCoordinate();
		int x0 = this.getxCoordinate();
		int step = speed ;
				
		if (xCoo < x) {
			this.setxCoordinates(xCoo + step);
			xCoo = this.getxCoordinate();
		}
		
		else if (xCoo > x) {
			//System.out.println(this.getxCoordinate()> x);
			this.setxCoordinates(xCoo - step);
			xCoo = this.getxCoordinate();
		}

		this.setyCoordinates((int)(y0 + (xCoo-x0)*director));
		
	}
	
	@Override
	public void behave() {
		if (toVisit.size() > 0) {
			Component objectif = toVisit.get(0);
			
			if(objectif.getyCoordinate() == this.getyCoordinate() && objectif.getyCoordinate() == this.getyCoordinate()) {
				toVisit.remove(objectif);
			}
			else {
				move(objectif.getxCoordinate(), objectif.getyCoordinate());
			}
		}
	}
	
}