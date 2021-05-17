/**
 * @author Simir Cooper 
 * @since 5/17/21
 * The shortest path program extends the 
 */
package Graph_Resources;
import java.util.*;

public class ShortestPath extends Graph {

    /**
     * The shortest path between two nodes in a graph.
     */
    protected static LinkedList<String> shortestPath = new LinkedList<String>();

    /**
     * Finds the shortest path between two nodes (source and destination) in a graph.
     *
     * @param graph       The graph to be searched for the shortest path.
     * @param source      The source node of the graph specified by user.
     * @param destination The destination node of the graph specified by user.
     *
     * @return the shortest path stored as a list of nodes.
     * or null if a path is not found.
     * Requires: source != null, destination != null and must have a name (e.g.
     * cannot be an empty string).
     */
    public static LinkedList<String> breadthFirstSearch(Graph graph, String source,
                                                       String destination) {
        shortestPath.clear();

        // A list that stores the path.
        LinkedList<String> path = new LinkedList<String>();

        // If the source is the same as destination, I'm done.
        if (source.equals(destination) && graph.memberOf(source)) {
            path.add(source);
            return path;
        }

        // A queue to store the visited nodes.
        ArrayDeque<String> queue = new ArrayDeque<String>();

        // A queue to store the visited nodes.
        ArrayDeque<String> visited = new ArrayDeque<String>();

        queue.offer(source);
        while (!queue.isEmpty()) {
            String vertex = queue.poll();
            visited.offer(vertex); //store the visited vertex

            LinkedList<String> neighborList = graph.getNeighbors(vertex);
            int index = 0;
            int neighborSize = neighborList.size();
            while (index != neighborSize) {
                String neighbour = neighborList.get(index);

                path.add(neighbour);
                path.add(vertex);

                if (neighbour.equals(destination)) {
                    return processPath(source, destination, path);
                } else {
                    if (!visited.contains(neighbour)) {
                        queue.offer(neighbour);
                    }
                }
                index++;
            }
        }
        return null;
    }

    /**
     * Adds the nodes involved in the shortest path.
     *
     * @param src         The source node.
     * @param destination The destination node.
     * @param path        The path that has nodes and their neighbor.
     * @return The shortest path.
     */
    private static LinkedList<String> processPath(String src, String destination,
                                                 LinkedList<String> path) {

        // Finds out where the destination node directly comes from.
        int index = path.indexOf(destination);
        String source = path.get(index + 1);

        // Adds the destination node to the shortestPath.
        shortestPath.add(0, destination);

        if (source.equals(src)) {
            // The original source node is found.
            shortestPath.add(0, src);
            return shortestPath;
        } else {
            // We find where the source node of the destination node
            // comes from.
            // We then set the source node to be the destination node.
            return processPath(src, source, path);
        }
    }
    public String printlist() {
    	String result = "";
    	for(int i = 0; i < shortestPath.size(); i++) {
    		result+= shortestPath.get(i)+ "--> ";
    	}
    	return result;
           
    }
}
