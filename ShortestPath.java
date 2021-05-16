package Graph_Resources;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class ShortestPath extends Graph {

    /**
     * The shortest path between two nodes in a graph.
     */
    protected static ArrayList<String> shortestPath = new ArrayList<String>();

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
    public static ArrayList<String> breadthFirstSearch(Graph graph, String source,
                                                       String destination) {
        shortestPath.clear();

        // A list that stores the path.
        ArrayList<String> path = new ArrayList<String>();

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
            visited.offer(vertex);

            ArrayList<String> neighborList = graph.getNeighbors(vertex);
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
    private static ArrayList<String> processPath(String src, String destination,
                                                 ArrayList<String> path) {

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
}
