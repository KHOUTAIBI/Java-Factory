package simulator;

import model.Factory;
import fr.tp.inf112.projects.canvas.controller.CanvasViewerController;
import fr.tp.inf112.projects.canvas.controller.Observer;
import fr.tp.inf112.projects.canvas.model.Canvas;
import fr.tp.inf112.projects.canvas.model.CanvasPersistenceManager;


public class SimulatorController implements CanvasViewerController 
{
	
	private Factory factory;
	private CanvasPersistenceManager manager;
	
	public SimulatorController(Factory factory) {
		this.factory = factory;
		this.manager = new Manager(null);
	}
	
	@Override
	public boolean addObserver(Observer observer) {
		return factory.addObserver(observer);
	}

	@Override
	public boolean removeObserver(Observer observer) {
		return factory.removeObserver(observer);
	}

	@Override
	public Canvas getCanvas() {
		return this.factory;
	}
	
	@Override
	public void startAnimation() {
		factory.startSimulation();
		while (factory.isSimulationStarted()) {
			factory.behave();
			try {
			Thread.sleep( 200 );
			}
			catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public void stopAnimation() {
		factory.stopSimulation();
	}

	@Override
	public boolean isAnimationRunning() {
		return factory.isSimulationStarted();
	}
	
	public void setFactory(Factory factory) {
		this.factory = factory;
	}

	@Override
	public void setCanvas(Canvas canvasModel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CanvasPersistenceManager getPersistenceManager() {
		return manager;
	}
	
	
}
