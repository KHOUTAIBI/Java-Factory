package model;

import java.io.Serializable;

import fr.tp.inf112.projects.canvas.model.Figure;
import utilities.Point;

/**
 * An abstract class representing a component in a factory.
 * Components can have a name, coordinates, a factory, and a destination point.
 * They also implement the Serializable and Figure interfaces.
 */
public abstract class Component implements Serializable, Figure {
    private String name; // the name of the component
    private Point coordinates; // the current coordinates of the component
    private Factory factory; // the factory that the component belongs to
    private Point destinationPoint; // the destination point of the component
    private static final long serialVersionUID = 202405101153L;

    /**
     * Constructs a new component with the given name, coordinates, destination point, and factory.
     *
     * @param name            the name of the component
     * @param xCoord          the x-coordinate of the component
     * @param yCoord          the y-coordinate of the component
     * @param destXCoord      the x-coordinate of the destination point
     * @param destYCoord      the y-coordinate of the destination point
     * @param factory         the factory that the component belongs to
     */
    public Component(String name, int xCoord, int yCoord, int destXCoord, int destYCoord, Factory factory) {
        this.name = name;
        this.coordinates = new Point(xCoord, yCoord);
        this.factory = factory;
        this.destinationPoint = new Point(destXCoord, destYCoord);
    }

    /**
     * Returns the x-coordinate of the component.
     *
     * @return the x-coordinate of the component
     */
    public final int getxCoord() {
        return coordinates.getxCoord();
    }

    /**
     * Returns the y-coordinate of the component.
     *
     * @return the y-coordinate of the component
     */
    public final int getyCoord() {
        return coordinates.getyCoord();
    }

    /**
     * Returns the coordinates of the component.
     *
     * @return the coordinates of the component
     */
    public final Point getCoordinates() {
        return coordinates;
    }

    /**
     * Returns the destination point of the component.
     *
     * @return the destination point of the component
     */
    public Point getDestPoint() {
        return this.destinationPoint;
    }

    /**
     * Sets the x-coordinate of the component.
     * Notifies the factory's observers.
     *
     * @param xCoordinate the new x-coordinate of the component
     */
    public void setxCoordinates(int xCoordinate) {
        (this.coordinates).setxCoord(xCoordinate);
        factory.notifyObservers();
    }

    /**
     * Sets the y-coordinate of the component.
     * Notifies the factory's observers.
     *
     * @param yCoordinate the new y-coordinate of the component
     */
    public void setyCoordinates(int yCoordinate) {
        (this.coordinates).setyCoord(yCoordinate);
        factory.notifyObservers();
    }

    /**
     * Returns the name of the component.
     *
     * @return the name of the component
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the x-coordinate of the component.
     *
     * @return the x-coordinate of the component
     */
    public int getxCoordinate() {
        return this.getxCoord();
    }

    /**
     * Returns the y-coordinate of the component.
     *
     * @return the y-coordinate of the component
     */
    public int getyCoordinate() {
        return this.getyCoord();
    }

    /**
     * Defines the behavior of the component.
     * Can be overridden by subclasses.
     */
    public void behave() {
    }

    /**
     * Returns the factory that the component belongs to.
*
     * @return the factory that the component belongs to
     */
    public Factory getFactory() {
        return this.factory;
    }

    /**
     * Sets the factory that the component belongs to.
     *
     * @param factory the new factory that the component belongs to
     */
    public void setFactory(Factory factory) {
        this.factory = factory;
    }

    /**
     * Returns the coordinates of the component as a string.
     *
     * @return the coordinates of the component as a string
     */
    public String getCoordString() {
        return "(" + Integer.toString(this.getxCoord()) + "," + Integer.toString(this.getyCoord()) + ")";
    }

    /**
     * Checks if the component overlays the given point.
     * Should be implemented by subclasses.
     *
     * @param point the point to check
     * @return true if the component overlays the point, false otherwise
     */
    public abstract boolean overlays(Point point);

    /**
     * Returns the Euclidean distance between two components.
     *
     * @param component1 the first component
     * @param component2 the second component
     * @return the Euclidean distance between the two components
     */
    public int distance(Component component1, Component component2) {
        return (int) Math.sqrt(Math.pow(component1.getxCoordinate() - component2.getxCoordinate(), 2) + Math.pow(component1.getyCoordinate() - component2.getyCoordinate(), 2));
    }

}
