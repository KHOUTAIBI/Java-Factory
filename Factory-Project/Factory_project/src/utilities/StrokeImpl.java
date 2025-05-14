package utilities;

import fr.tp.inf112.projects.canvas.model.Color;
import fr.tp.inf112.projects.canvas.model.Stroke;

/**
 * An implementation of the Stroke interface.
 */
public class StrokeImpl implements Stroke {

    /**
     * The color of the stroke.
     */
    private Color color;

    /**
     * The thickness of the stroke.
     */
    private float thickness;

    /**
     * The dash pattern of the stroke.
     */
    private float[] dashPattern;

    /**
     * Constructs a new StrokeImpl object with the given color, thickness, and dash pattern.
     *
     * @param color      the color of the stroke
     * @param thickness  the thickness of the stroke
     * @param dashPattern the dash pattern of the stroke
     */
    public StrokeImpl(Color color, float thickness, float[] dashPattern) {
        this.color = color;
        this.thickness = thickness;
        this.dashPattern = dashPattern;
    }

    /**
     * Gets the color of the stroke.
     *
     * @return the color
     */
    @Override
    public Color getColor() {
        return this.color;
    }

    /**
     * Gets the thickness of the stroke.
     *
     * @return the thickness
     */
    @Override
    public float getThickness() {
        return this.thickness;
    }

    /**
     * Gets the dash pattern of the stroke.
     *
     * @return the dash pattern
     */
    @Override
    public float[] getDashPattern() {
        return this.dashPattern;
    }

}
