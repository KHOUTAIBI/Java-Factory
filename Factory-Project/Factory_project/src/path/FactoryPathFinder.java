package path;

import java.util.ArrayList;

import model.Component;
import utilities.Point;

/**
 * An interface for finding a path in a factory.
 */
public interface FactoryPathFinder {

    /**
     * Finds a path in the factory from the given start component to the given finish component.
     *
     * @param start the start component
     * @param finish the finish component
     * @return a list of graph points to visit in order to complete the path
     */
    ArrayList<Point> findPath(Component start, Component finish);

}