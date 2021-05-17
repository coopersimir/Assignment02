/**
 * @author Simir Cooper 
 * @since 5/17/21
 * This is a graph implementation that constructs the connections of each of the movie stars present in our text file.
 */
package Graph_Resources;
import java.util.*;

public class Graph {

    /**
     * Stores a list of vertices in this Graph.
     */
    protected LinkedList<String> nodes = new LinkedList<String>();

    /**
     * Creates a mapping from a node to its neighbors.
     */
    protected Map<String, LinkedList<String>> map = new HashMap<String, LinkedList<String>>();

    /**
     * Add vertex 
     * @param source
     */
    public void addVertex(String source)
    {
        map.put(source, new LinkedList<String>());
    }

    /**
     * Adds a bidirectional edge between two nodes.
     * @param source      the source vertex.
     * @param destination the destination vertex, to be connected from source.
     */
    public void addEdge(String source, String destination) {
        // Adds a new path.
        if (!map.containsKey(source)) {
        	 addVertex(source);
        } 
        if (!map.containsKey(destination)) {
        	 addVertex(destination);
        }
        map.get(source).add(destination); //add edge from source to destination
        map.get(destination).add(source); //add edge from destination to source
        
        storeNodes(source, destination); //store the nodes in the linkedlist
    }

    /**
     * @param source
     * @param destination
     * Stores the vertices in this Graph.
     */
    private void storeNodes(String source, String destination) {
        if (!source.equals(destination)) {
            if (!nodes.contains(destination)) {
                nodes.add(destination);
            }
        }
        if (!nodes.contains(source)) {
            nodes.add(source);
        }
    }

    /**
     * Returns the Neighboring Vertices for a source vertex.
     * @param node the node where its neighbors will be searched for. Requires:
     *             node must be present in this Graph and not null.
     * @return the NeighborsList for this node.
     */
    public LinkedList<String> getNeighbors(String node) {
        LinkedList<String> NeighborsList;
        Set<String> keys = map.keySet();
        for (String key : keys) {
            if (key.equals(node)) {
                NeighborsList = map.get(key);
                return new LinkedList<String>(NeighborsList);
            }
        }
        return new LinkedList<String>();
    }

    /**
     * Checks if the vertex is in this Graph.
     * @return true if the vertex is in this Graph.
     */
    public boolean memberOf(String node) {
        return nodes.contains(node);
    }

    /**
     * Returns a string representation of this Graph
     * @return a string representation of this Graph.
     */
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
  
        for (String v : map.keySet()) {
            builder.append(v.toString() + ": ");
            for (String w : map.get(v)) {
                builder.append(w.toString() + " ");
            }
            builder.append("\n");
        }
  
        return (builder.toString());
    }
}

