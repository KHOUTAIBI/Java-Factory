package utilities;

import fr.tp.inf112.projects.canvas.model.OvalShape;

public class CircleShapeImpl implements OvalShape {

	private int radius;
	
	
	public CircleShapeImpl(int radius) {
		this.radius = radius;
	}

	@Override
	public int getWidth() {
		return radius / 2 ;
	}

	@Override
	public int getHeight() {
		return getWidth();
	}

}
