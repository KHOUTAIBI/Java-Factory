package simulator;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;
import fr.tp.inf112.projects.canvas.view.CanvasViewer;
import model.*;

// This is the main class of the simulator application
public class SimulatorApplication {
	
	// Create a logger for the application
	private static final Logger LOGGER = Logger.getLogger(SimulatorApplication.class.getName());

	// This is the main method of the application
	public static void main(String[] args) throws IOException {
		
		// Log a message to indicate that the simulator is starting
		LOGGER.info("Starting the robot simulator...");
		
		// Log the parameters passed to the application
		LOGGER.config("With parameters " + Arrays.toString(args) + ".");
		
		// Create a factory with the name "Sky Factory One"
		Factory ourFactory = new Factory("Sky Factory One");
		
		//Note ! please make sure that all the components are places in x and y axises that are 
		//multiples of the graph's step. Otherwise and exception will be thrown !
		//This too applies to the robot's speed since the robot's step = speed!
		//Of course the robot has to only move according to the graph's step!
		//The graph's step can be found in the pathFinder function. 


		// Create rooms and areas in the factory
		Room room1 = ourFactory.addRoom("Production room", 0, 0, 900, 500);
		Room room2 = ourFactory.addRoom("Storage room", 900, 0, 1200, 500);
		Room room3 = ourFactory.addRoom("Delivery room", 0, 500, 1400, 500);
		Room room4 = ourFactory.addRoom("Conveyor room", 1400, 500, 600, 500);
		
		// Create packaging areas
		Area packing = ourFactory.addArea("Packing Area", 100, 300, 150, 150, 260, 360, room1);
		Area stock  = ourFactory.addArea("Stock Unit", 50, 800, 300, 150, 200, 780, room1);
		
		// Create conveyors, a charging station, doors and a robot in the factory
		ChargingStation charger1 = ourFactory.addChargingStation("chargingSta", 800, 50, 70, 70, 840, 80, room1);
		ChargingStation charger2 = ourFactory.addChargingStation("chargingSta", 800, 550, 70, 70, 840, 580, room1);
		ChargingStation charger3 = ourFactory.addChargingStation("chargingSta", 1600, 550, 70, 70, 1640, 580, room1);
		
		// Add more areas and production units to the factory
		Area bedArea = ourFactory.addArea("Puck Receiver", 1700, 520, 300, 180, 1700, 520, room2);
		Conveyor ourFirstConveyor1 = ourFactory.addConveyor("Conveyor", 1900, 700, 100, 300, 1880, 860, 10, bedArea, "up");
		Conveyor ourFirstConveyor2 = ourFactory.addConveyor("Conveyor", 1900, 700, 100, 300, 1880, 820, 10, bedArea, "up");
		Conveyor ourFirstConveyor3 = ourFactory.addConveyor("Conveyor", 1900, 700, 100, 300, 1880, 900, 10, bedArea, "up");
		ourFactory.addProdUnit("Production Unit", 200, 200, 50, 150, 260, 200, 5, packing, room2);
		
		// Add doors to the factory
		Door door1 = ourFactory.addDoor("door1", 900, 200, 21, 100, room1);
		Door door2 = ourFactory.addDoor("door2", 500, 500, 100, 21, room1);
		Door door3 = ourFactory.addDoor("door3", 900, 700, 21, 100, room1);
		Door door4 = ourFactory.addDoor("door4", 1150, 500, 100, 21, room1);
		
		// Add walls to the factory
		Wall wall1 = ourFactory.addWall("wall", 900, 0, 21, 200, room2);
		Wall wall2 = ourFactory.addWall("wall", 0, 0, 21, 2000, room1);
		Wall wall3 = ourFactory.addWall("wall", 0, 0, 2000, 21, room1);
		Wall wall4 = ourFactory.addWall("wall", 0, 980, 2000, 21, room3);
		Wall wall5 = ourFactory.addWall("wall", 1980, 0, 21, 2000, room2);
		Wall wall6 = ourFactory.addWall("wall", 0, 500, 500 , 21, room2);
		Wall wall7 = ourFactory.addWall("wall", 900, 800, 21, 200, room4);
		Wall wall8 = ourFactory.addWall("wall", 900, 300, 21, 220, room4);
		Wall wall9 = ourFactory.addWall("wall", 600, 500, 550, 21, room4);
		Wall wall10 = ourFactory.addWall("wall", 900, 500, 21, 200, room4);
		Wall wall11 = ourFactory.addWall("wall", 1250, 500, 750, 21, room4);
		
		// Initialize robots at some coordinates
		Robot Balle = ourFactory.addRobot("Ball-e", 100, 140, 20, room1);
		Robot Walle = ourFactory.addRobot("Wall-e", 600, 100, 20, room2);
		Robot Dalle = ourFactory.addRobot("Dall-e", 1600, 100, 20, room2);
		
		// Add a puck to the factory
		Puck puck = ourFactory.addPuck(200, 860);
		
		// Create a list of points for the robot to visit
		Balle.AddComponentToVisit(packing);
		Balle.AddComponentToVisit(ourFirstConveyor1);
		
		Walle.AddComponentToVisit(packing);
		Walle.AddComponentToVisit(ourFirstConveyor2);
		
		Dalle.AddComponentToVisit(packing);
		Dalle.AddComponentToVisit(ourFirstConveyor3);
		
		// Create a simulator controller and a canvas viewer for the simulation
		SimulatorController controller = new SimulatorController(ourFactory);
		CanvasViewer ourSimulation = new CanvasViewer(controller);
	}

}
