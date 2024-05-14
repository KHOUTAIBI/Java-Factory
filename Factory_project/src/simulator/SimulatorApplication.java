package simulator;


import fr.tp.inf112.projects.canvas.view.CanvasViewer;
import model.Convoyor;
import model.Factory;
import model.Room;
import model.Area;
import model.ChargingStation;
import model.Robot;




public class SimulatorApplication 
{


	public static void main(String[] args) {
		
		
		Factory ourFactory = new Factory("Sky Factory One");
		Room room1 = ourFactory.addRoom("Living room", 100, 100, 800, 800, ourFactory);
		Room room2 = ourFactory.addRoom("bedroom", 1100, 100, 800, 800,ourFactory);
		Area area1 = ourFactory.addArea("Living area",300, 300, 200, 200, room1, ourFactory);
		Convoyor ourFirstConvoyor = ourFactory.addConvoyor("tapis", 150, 700, 300, 50, 10, area1, "right", ourFactory);
		Convoyor ourSecondConvoyor = ourFactory.addConvoyor("tapis", 1150, 700, 300, 50, 5, area1, "left", ourFactory);
		ChargingStation charger = ourFactory.addChargingStation("charger", 200, 200, 50,50, room1, ourFactory);
		Robot Balle = ourFactory.addRobot("Ball-e", 200, 400, 50, 10, room1, ourFactory);
		Balle.AddComponentToVisit(ourFirstConvoyor);
		Balle.AddComponentToVisit(charger);
		Balle.AddComponentToVisit(ourSecondConvoyor);
		ourFactory.addArea("bed area",1300, 300, 200, 200, room2, ourFactory);
		ourFactory.addProdUnit("food prod", 350, 350, 100, 100, 3, room2, ourFactory);
		ourFactory.addProdUnit("sleep prod", 1350, 350, 100, 100, 1, room2, ourFactory);
		SimulatorController controller = new SimulatorController(ourFactory);
		CanvasViewer ourSimulation = new CanvasViewer(controller);
	}

}
