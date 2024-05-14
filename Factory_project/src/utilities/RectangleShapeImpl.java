package utilities;

import fr.tp.inf112.projects.canvas.model.RectangleShape;

public class RectangleShapeImpl implements RectangleShape{
	private final int width;
	private final int height;
	
	public RectangleShapeImpl(int width, int height) {
		super();
		this.width = width;
		this.height = height;
	}

	@Override
	public int getWidth() {
		return this.width;
	}

	@Override
	public int getHeight() {
		return this.height;
	}
	
}
