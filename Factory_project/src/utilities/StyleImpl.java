package utilities;

import fr.tp.inf112.projects.canvas.model.Color;
import fr.tp.inf112.projects.canvas.model.Stroke;
import fr.tp.inf112.projects.canvas.model.Style;

public class StyleImpl implements Style {
	private Color color;
	private Stroke stroke;
	
	public StyleImpl(Color color, Color strokeColor, float thickness, float[] dachPattern) {
		super();
		this.color = color;
		this.stroke = new StrokeImpl(strokeColor, thickness, dachPattern);
	}

	@Override
	public Color getBackgroundColor() {
		return this.color;
	}

	@Override
	public Stroke getStroke() {
		return this.stroke;
	}
	
}
