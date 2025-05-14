package model;

import fr.tp.inf112.projects.canvas.model.*;
import java.util.ArrayList;
import fr.tp.inf112.projects.canvas.model.Style;
import fr.tp.inf112.projects.canvas.model.impl.RGBColor;
import path.FactoryPathFinder;
import path.PathFinder;
import utilities.*;

/**
 * The Robot class represents a robot component in the factory simulation.
 * It can move, charge its battery, and interact with other components.
 */
public class Robot extends Component {
    private static int radius = 10;
    private int speed;
    private boolean isFull;
    private Battery battery;
    private Puck inventory;
    private ArrayList<Component> toVisit;
    private static final long serialVersionUID = 202405101305L;
    private FactoryPathFinder pathFinder;
    private ArrayList<Point> pathPoints;
    private boolean lookToCharge;
    private boolean charging;
    private boolean gotoCharge;

    /**
     * Constructor for the Robot class.
     *
     * @param name    the name of the robot
     * @param xCoord  the x-coordinate of the robot
     * @param yCoord  the y-coordinate of the robot
     * @param speed   the speed of the robot
     * @param factory the factory the robot belongs to
     */
    public Robot(String name, int xCoord, int yCoord, int speed, Factory factory) {
        super(name, xCoord, yCoord, xCoord, yCoord, factory);
        this.speed = speed;
        this.isFull = false;
        this.battery = new Battery(this);
        this.toVisit = new ArrayList<Component>();
        this.pathPoints = new ArrayList<Point>();
        this.pathFinder = new PathFinder(factory);
        this.lookToCharge = false;
        this.charging = false;
        this.gotoCharge = false;
    }

    /**
     * Set the components the robot needs to visit.
     *
     * @param components the components to visit
     */
    public void setComponents(ArrayList<Component> components) {
        this.toVisit = components;
    }

    /**
     * Add a component to the list of components to visit.
     *
     * @param component the component to add
     */
    public void AddComponentToVisit(Component component) {
        this.toVisit.add(component);
    }

    @Override
    public int getxCoordinate() {
        return this.getxCoord() - radius;
    }

    @Override
    public int getyCoordinate() {
        return this.getyCoord() - radius;
    }

    public static int getRadius() {
        return radius;
    }

    public void setFull(boolean full) {
        this.isFull = full;
    }

    public boolean getIsFull() {
        return this.isFull;
    }

    /**
     * @return true if the robot is charging, false otherwise
     */
    public boolean isCharging() {
        return charging;
    }

    public void setCharging(boolean charging) {
        this.charging = charging;
    }

    /**
     * Charge the robot's battery.
     *
     * @param chargeQuantity the amount to charge
     * @return true if the battery was successfully charged, false otherwise
     */
    public boolean charge(double chargeQuantity) {
        return battery.increaseBatteryPower(chargeQuantity);
    }

    @Override
    public Style getStyle() {
        double batteryPower = this.battery.getBatterypower();
        float[] myFloatArray = {0.1F};
        if (batteryPower >= 0.7)
            return new StyleImpl(RGBColor.GREEN, RGBColor.BLACK, 0.2F, myFloatArray);
        else if (batteryPower >= 0.5)
            return new StyleImpl(RGBColor.YELLOW, RGBColor.BLACK, 0.2F, myFloatArray);
        else if (batteryPower >= 0.2)
            return new StyleImpl(RGBColor.ORANGE, RGBColor.BLACK, 0.2F, myFloatArray);
        else
            return new StyleImpl(RGBColor.RED, RGBColor.BLACK, 0.2F, myFloatArray);
    }

    @Override
    public Shape getShape() {
        if (this.getIsFull())
            return new RectangleShapeImpl(2 * radius, 2 * radius);
        return new CircleShapeImpl(4 * radius);
    }

    /**
     * Adjust the shape based on the fullness of the robot.
     */
    public void setShape() {
        if (this.getIsFull())
            ((RectangleShapeImpl) this.getShape()).setWidhthandheight(radius * 4, radius * 4);
    }

    /**
     * Put a puck on the conveyor.
     *
     * @param conveyor the conveyor to put the puck on
     * @return true if the puck was successfully put on the conveyor, false otherwise
     */
    public boolean putPuck(Conveyor conveyor) {
        if (conveyor.isNotFull() && isFull) {
            conveyor.givePuck(inventory);
            isFull = false;
            return true;
        }
        return false;
    }

    /**
     * Take a puck from the target area.
     *
     * @param targetArea the area to take the puck from
     * @return true if the puck was successfully taken, false otherwise
     */
    public boolean takePuck(Area targetArea) {
        if (targetArea.getInventory().size() > 0 && !isFull) {
            inventory = targetArea.getLastPuck();
            targetArea.removePuck(inventory);
            isFull = true;
            return true;
        }
        return false;
    }

    /**
     * Move the robot towards a given point.
     *
     * @param point the target point
     */
    public void move(Point point) {
        int currentX = this.getxCoord();
        int currentY = this.getyCoord();
        int step = this.speed;
        int x = point.getxCoord();
        int y = point.getyCoord();

        // Move until the robot reaches the target position
        int diffX = x - currentX;
        int diffY = y - currentY;

        // Update x coordinate
        if (diffX != 0) {
            currentX += (diffX > 0) ? step : -step;
            this.battery.reduceBatterypower(0.95);
        }

        // Update y coordinate
        if (diffY != 0) {
            currentY += (diffY > 0) ? step : -step;
            this.battery.reduceBatterypower(0.95);
        }

        // Set the new coordinates
        this.setxCoordinates(currentX);
        this.setyCoordinates(currentY);
    }

    /**
     * Navigate to the current objective. This is complexe, please read Slowly !
     */
    public void gotoObjectif() {

        //begin iterating on your point list if its size is > 0

        if (pathPoints.size() > 0) {
            Point objectifPoint = pathPoints.get(0);
            if (objectifPoint.getxCoord() == this.getxCoord() && objectifPoint.getyCoord() == this.getyCoord()) {
                pathPoints.remove(objectifPoint);
            } else {
                move(objectifPoint);
            }
        } 
        
        //In the case where the point list in null or size ==0, let us check the objective that
        //we are in
        
        else if (toVisit.size() > 0) {
            //of course we will not charge here !
            gotoCharge = false;
            Component objectifComponent = toVisit.get(0);

            //If we reach the objectif
            if (objectifComponent.getDestPoint().getxCoord() == this.getxCoord() && objectifComponent.getDestPoint().getyCoord() == this.getyCoord()) {

                //Depending on the component, the robot does an action, Look at the functinons in 
                // the classes !
                if (objectifComponent instanceof Area) {
                    if (((Area) objectifComponent).performAction(this)) {
                        toVisit.remove(objectifComponent);
                    }
                } 
                
                //remove the current objective, now we move to the next one 
                else {
                    toVisit.remove(objectifComponent);
                }
            } else if (!charging) {
                pathPoints = pathFinder.findPath(this, objectifComponent);
            }
        }
    }

    //This functions takes into consideration the depleting battery and choses whether or not to 
    //continue of to charge the Robot

    @Override
    public void behave() {
        
        //Verify the battery level and choose if to charge or not
        if (this.battery.getBatterypower() < 0.2 && !lookToCharge && !gotoCharge) {
            lookToCharge = true;
        }

        //If you don't need to charge just go to your Objectif !!
        if (!lookToCharge) {
            gotoObjectif();
        } 

        //However if you need to charge decide whether or not to continue working or to charge
        else if (lookToCharge && !gotoCharge) {

            // Getting the nearest charging station or objective
            ArrayList<Point> subPathPoints = pathFinder.findPath(this, this.getFactory().getChargingstations().get(0));

            //initialize a variable to decide what is the shortest distance: The current Objectif or 
            //the charger ?

            int minsize = subPathPoints.size();

            //iterate on all the components and do a dijkstra, the smallest list is the closest !
            for (Component chargingStation : this.getFactory().getChargingstations()) {
                ArrayList<Point> whichpath = pathFinder.findPath(this, chargingStation);
                if (minsize > whichpath.size()) {
                    minsize = whichpath.size();
                    subPathPoints = whichpath;
                }
            }

            //decide to go to charge

            gotoCharge = true;

            ArrayList<Point> currentObjectif = new ArrayList<Point>();

            if (toVisit.size() > 0) {
                currentObjectif = pathFinder.findPath(this, toVisit.get(0));
            }

            //decide if to go to the objective or to just charge

            if (currentObjectif.size() <= subPathPoints.size()) {
                subPathPoints = currentObjectif;
                gotoCharge = true;
            }
            
            //reconfigure the graph following the decision made by the robot
            pathPoints = subPathPoints;
            lookToCharge = false;
        }
    }


    //Consider that the robot doesnt overlay the factory
    @Override
    public boolean overlays(Point point) {
        return false;
    }
}

