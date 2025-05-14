package path;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import model.Component;
import model.Factory;
import model.Rectangle;
import utilities.Point;

/**
 * PathFinder class implementing the FactoryPathFinder interface to find a path between two components in a factory.
 */
public class PathFinder implements FactoryPathFinder, Serializable{

	private static final long serialVersionUID = 202405261212L;

	// Graph to store the vertices and edges
    private Graph<Point, DefaultEdge> graph;
    
    // Map to store the dots with their names as keys
    private Map<String, Point> pointsMap;
    
    // Factory object
    private Factory factory;
    
    private static int POINTSTEP = 20;
    
    /**
     * Constructor to initialize the PathFinder object with a factory.
     *
     * @param factory the factory object
     */
    public PathFinder(Factory factory) {
        this.factory = factory;
        this.graph = new DefaultDirectedGraph<Point, DefaultEdge>(DefaultEdge.class);
        this.pointsMap = new HashMap<String, Point>();
        addVerticesAndEdges(factory);  // Initialize the graph
    }

    /**
     * Private method to add vertices and edges to the graph.
     *
     * @param factory the factory object
     */
    private void addVerticesAndEdges(Factory factory) {
        // Add vertices
        for (int i = POINTSTEP; i < factory.getWidth(); i += POINTSTEP) {
            for (int j = POINTSTEP; j < factory.getHeight(); j += POINTSTEP) {
                Point point = new Point(i, j);
                pointsMap.put(point.getName(), point);
                this.graph.addVertex(point);
            }
        }

        // Remove vertices that are overlapped by rectangles
        List<Point> verticesToRemove = new ArrayList<>();
        
        for (int i = POINTSTEP; i < factory.getWidth(); i += POINTSTEP) {
            for (int j = POINTSTEP; j < factory.getHeight(); j += POINTSTEP) {
                Point point = pointsMap.get("(" + i + "," + j + ")");
                boolean shouldRemove = false;
                ArrayList<Component> componentsCopy = new ArrayList<Component>(this.factory.getComponents());
                for (Component component : componentsCopy) {
                    if (component instanceof Rectangle && ((Rectangle) component).overlays(point)) {
                        shouldRemove = true;
                        break;
                    }
                }
                if (shouldRemove) {
                    verticesToRemove.add(point);
                }
            }
        }

        // Remove vertices and update dotsMap
        for (Point dot : verticesToRemove) {
            this.graph.removeVertex(dot);
            pointsMap.remove(dot.getName());
        }

        // Add edges
        for (int i = POINTSTEP; i < factory.getWidth(); i += POINTSTEP) {
            for (int j = POINTSTEP; j < factory.getHeight(); j += POINTSTEP) {
            	Point point1 = pointsMap.get("(" + i + "," + j + ")");
                if (point1 == null) continue; // Skip if the vertex was removed

                // Connect to the left neighbor
                if (i > POINTSTEP) {
                	Point point2 = pointsMap.get("(" + (i - POINTSTEP) + "," + j + ")");
                    if (point2 != null) {
                        this.graph.addEdge(point1, point2);
                        this.graph.addEdge(point2, point1); // If bi-directional
                    }
                }

                // Connect to the right neighbor
                if (i < factory.getWidth() - POINTSTEP) {
                	Point point4 = pointsMap.get("(" + (i + POINTSTEP) + "," + j + ")");
                    if (point4 != null) {
                        this.graph.addEdge(point1, point4);
                        this.graph.addEdge(point4, point1); // If bi-directional
                    }
                }

                // Connect to the upper neighbor
                if (j > POINTSTEP) {
                	Point point3 = pointsMap.get("(" + i + "," + (j - POINTSTEP) + ")");
                    if (point3 != null) {
                        this.graph.addEdge(point1, point3);
                        this.graph.addEdge(point3, point1); // If bi-directional
                    }
                }

                // Connect to the lower neighbor
                if (j < factory.getHeight() - POINTSTEP) {
                	Point point5 = pointsMap.get("(" + i + "," + (j + POINTSTEP) + ")");
                    if (point5 != null) {
                        this.graph.addEdge(point1, point5);
                        this.graph.addEdge(point5, point1); // If bi-directional
                    }
                }
            }
        }
    }
    
    /**
     * Public method to find the shortest path between two points in our graph/Factory using the Dijkstra Algorithm.
     *
     * @param Dots starting point . Dots finish point.
     * @return ArrayList of points to visit between start an finish.
     */
    
    @Override
    public ArrayList<Point> findPath(Component start, Component finish) {
    	
    	
    	
        // Use vertices from the dotsMap to ensure consistency
    	Point startPoint = pointsMap.get("(" +  start.getxCoord() + "," + (start.getyCoord()) + ")");
        Point finishPoint = pointsMap.get(finish.getDestPoint().getName());

        if (startPoint == null || finishPoint == null) {
            throw new IllegalArgumentException("No such vertex in graph: " + (startPoint == null ? start.getName() : finish.getName()));
        }

        // Initialize the Dijkstra's algorithm to find the shortest path between the two dots
        DijkstraShortestPath<Point, DefaultEdge> dijkstraAlg = new DijkstraShortestPath<Point, DefaultEdge>(graph);

        // Get the list of edges representing the shortest path between the start and finish dots
        List<DefaultEdge> paths = dijkstraAlg.getPath(startPoint, finishPoint).getEdgeList();

        ArrayList<Point> intPaths = new ArrayList<Point>();

        // Iterate through each edge in the shortest path
        for (DefaultEdge edge : paths) {
            // Get the source and target Points for the current edge
        	Point source = graph.getEdgeSource(edge);
        	Point target = graph.getEdgeTarget(edge);

            // If the sourcePoint or targetPoint is not already in the intPaths list, add it
            if (!intPaths.contains(source)) {
                intPaths.add(source);
            }
            if (!intPaths.contains(target)) {
                intPaths.add(target);
            }
        }
        // Return the list of points representing the shortest path between the start and finish dots
        return intPaths;
    }
}
