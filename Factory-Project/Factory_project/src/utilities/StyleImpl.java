package utilities;

import fr.tp.inf112.projects.canvas.model.Color;
import fr.tp.inf112.projects.canvas.model.Stroke;
import fr.tp.inf112.projects.canvas.model.Style;

/**
 * An implementation of the Style interface.
 */
public class StyleImpl implements Style {

    /**
     * The background color of the style.
     */
    private Color color;

    /**
     * The stroke of the style.
     */
    private Stroke stroke;

    /**
     * Constructs a new StyleImpl object with the given background color, stroke color, stroke thickness, and stroke dash pattern.
     *
     * @param color            the background color of the style
     * @param strokeColor      the color of the stroke
     * @param thickness        the thickness of the stroke
     * @param dashPattern      the dash pattern of the stroke
     */
    public StyleImpl(Color color, Color strokeColor, float thickness, float[] dashPattern) {
        this.color = color;
        this.stroke = new StrokeImpl(strokeColor, thickness, dashPattern);
    }

    /**
     * Gets the background color of the style.
     *
     * @return the background color
     */
    @Override
    public Color getBackgroundColor() {
        return this.color;
    }

    /**
     * Sets the background color of the style.
     *
     * @param color the new background color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Gets the stroke of the style.
     *
     * @return the stroke
     */
    @Override
    public Stroke getStroke() {
        return this.stroke;
    }

}