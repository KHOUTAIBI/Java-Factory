package model;

import java.util.ArrayList;

import fr.tp.inf112.projects.canvas.model.Style;
import fr.tp.inf112.projects.canvas.model.impl.RGBColor;
import utilities.Point;
import utilities.StyleImpl;

/**
 * A room in the factory.
 */
public class Room extends Rectangle {

    // The robots in the room
    private ArrayList<Robot> robots;

    // The surfaces in the room
    private ArrayList<Rectangle> surfaces;

    /**
     * Constructs a new room with the given parameters.
     *
     * @param name           the name of the room
     * @param xCoord         the x-coordinate of the room
     * @param yCoord         the y-coordinate of the room
     * @param xlenght        the x-length of the room
     * @param ylenght        the y-length of the room
     * @param factory        the factory that the room belongs to
     */
    public Room(String name, int xCoord, int yCoord, int xlenght, int ylenght, Factory factory) {
        super(name, xCoord, yCoord, xlenght, ylenght, xCoord, yCoord, factory);
        this.robots = new ArrayList<Robot>();
        this.surfaces = new ArrayList<Rectangle>();
    }

    /**
     * Adds a robot to the room.
     *
     * @param robot the robot to add
     */
    public void addRobot(Robot robot) {
        robots.add(robot);
    }

    /**
     * Adds a charging station to the room.
     *
     * @param chargingStation the charging station to add
     */
    void addChargingStation(ChargingStation chargingStation) {
        surfaces.add(chargingStation);
    }

    /**
     * Adds an area to the room.
     *
     * @param area the area to add
     */
    void addArea(Area area) {
        surfaces.add(area);
    }

    /**
     * Adds a door to the room.
     *
     * @param door the door to add
     */
    void addDoor(Door door) {
        // Add the production unit to the list of surfaces
        surfaces.add(door);
    }

    /**
     * Adds a production unit to the room.
     *
     * @param prodUnit the production unit to add
     */
    void addProdUnit(ProdUnit prodUnit) {
        surfaces.add(prodUnit);
    }

    /**
     * Adds a wall to the room.
     *
     * @param wall the wall to add
     */
    void addWall(Wall wall) {
        this.surfaces.add(wall);
    }

    /**
     * Returns the style of the room.
     *
     * @return the style of the room
     */
    @Override
    public Style getStyle() {
        float[] myFloatArray = {0.1F};
        Style style = new StyleImpl(RGBColor.WHITE, RGBColor.BLACK, 0.2F, myFloatArray);
        return style;
    }

    /**
     * Checks if the room overlays a given point.
     *
     * @param point the point to check
     * @return false, since rooms do not overlay points
     */
    @Override
    public boolean overlays(Point point) {
        // Always return false since rooms do not overlay dots
        return false;
    }

}
