package simulator;

import java.io.IOException;
import java.util.ArrayList;
import model.Factory;
import fr.tp.inf112.projects.canvas.controller.CanvasViewerController;
import fr.tp.inf112.projects.canvas.controller.Observer;
import fr.tp.inf112.projects.canvas.model.Canvas;
import fr.tp.inf112.projects.canvas.model.CanvasPersistenceManager;
import fr.tp.inf112.projects.canvas.view.FileCanvasChooser;

/**
 * A class for controlling a simulation.
 */
public class SimulatorController implements CanvasViewerController {

    /**
     * The factory being simulated.
     */
    private Factory factory;

    /**
     * The manager for persisting the factory.
     */
    private CanvasPersistenceManager manager;

    /**
     * Constructs a new SimulatorController object with the given factory.
     *
     * @param factory the factory to simulate
     */
    public SimulatorController(Factory factory) {
        this.factory = factory;
        this.manager = new Manager(new FileCanvasChooser("txt", "Canvas"));
    }

    /**
     * Adds an observer to the factory.
     *
     * @param observer the observer to add
     * @return true if the observer was added, false otherwise
     */
    @Override
    public boolean addObserver(Observer observer) {
        return factory.addObserver(observer);
    }

    /**
     * Removes an observer from the factory.
     *
     * @param observer the observer to remove
     * @return true if the observer was removed, false otherwise
     */
    @Override
    public boolean removeObserver(Observer observer) {
        return factory.removeObserver(observer);
    }

    /**
     * Gets the factory being simulated.
     *
     * @return the factory
     */
    @Override
    public Canvas getCanvas() {
        return this.factory;
    }

    /**
     * Starts the animation of the factory.
     */
    @Override
    public void startAnimation() {
        factory.startSimulation();
        while (factory.isSimulationStarted()) {
            factory.behave();
            try {
                Thread.sleep(200);
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Stops the animation of the factory.
     */
    @Override
    public void stopAnimation() {
        factory.stopSimulation();
    }

    /**
     * Checks if the animation of the factory is running.
     *
     * @return true if the animation is running, false otherwise
     */
    @Override
    public boolean isAnimationRunning() {
        return factory.isSimulationStarted();
    }

    /**
     * Sets the factory being simulated.
     *
     * @param factory the factory to set
     */
    public void setFactory(Factory factory) {
        this.factory = factory;
    }

    /**
     * Sets the canvas being simulated.
     *
     * @param canvasModel the canvas to set
     */
    @Override
    public void setCanvas(Canvas canvasModel) {
        if (canvasModel instanceof Factory) {
            this.factory = (Factory) canvasModel;
        }
        else {
            System.out.println("canvasModel is not a Factory");
        }
    }

    /**
     * Gets the manager for persisting the factory.
     *
     * @return the manager
     */
    @Override
    public CanvasPersistenceManager getPersistenceManager() {
        return manager;
    }

}
