package utilities;

import fr.tp.inf112.projects.canvas.model.Color;
import fr.tp.inf112.projects.canvas.model.Stroke;

public class StrokeImpl implements Stroke {
	private Color color;
	private float thickness;
	private float[] dashPattern;
	
	public StrokeImpl(Color color, float thickness, float[] dashPattern) {
		super();
		this.color = color;
		this.thickness = thickness;
		this.dashPattern = dashPattern;
	}
	
	@Override
	public Color getColor() {
		return this.color;
	}

	@Override
	public float getThickness() {
		return this.thickness;
	}
	
	@Override
	public float[] getDashPattern() {
		return this.dashPattern;
	}

}
