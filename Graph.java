
package Graph_Resources;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Graph {

    /**
     * Stores a list of nodes in this Graph.
     */
    protected ArrayList<String> nodes = new ArrayList<String>();

    /**
     * Creates a mapping from a node to its neighbors.
     */
    protected Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();

    /**
     * Constructs a graph.
     */
    public Graph() {
    }

    /**
     * Adds an edge between two nodes.
     *
     * @param source      the source node.
     * @param destination the destination node, to be connected from source.
     */
    public void addEdge(String source, String destination) {
        // Adds a new path.
        if (!map.containsKey(source)) {
            /*
            Stores a list of neighbors for vertex.
            */
            ArrayList<String> Neighbors = new ArrayList<String>();
            Neighbors.add(destination);
            map.put(source, Neighbors);
        } else {
            // Updates a path.
            ArrayList<String> oldList = map.get(source);

            int index = 0;
            while ((index != oldList.size()) && (!oldList.get(index).equals(destination))) {
                index++;
            }
            // If the destination is not already in the path, then
            // add it to the path.
            if (index == oldList.size()) {
                oldList.add(destination);
                map.put(source, oldList);
            }
        }
        storeNodes(source, destination);
    }

    /**
     * @param source
     * @param destination
     * Stores the nodes in this Graph.
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
     * Returns the NeighborsList for this node.
     *
     * @param node the node where its neighbors will be searched for. Requires:
     *             node must be present in this Graph and not null.
     * @return the NeighborsList for this node.
     */
    public ArrayList<String> getNeighbors(String node) {
        ArrayList<String> NeighborsList;
        Set<String> keys = map.keySet();
        for (String key : keys) {
            if (key.equals(node)) {
                NeighborsList = map.get(key);
                return new ArrayList<String>(NeighborsList);
            }
        }
        return new ArrayList<String>();
    }

    /**
     * Checks if the node is in this Graph.
     *
     * @return true if the node is in this Graph.
     */
    public boolean memberOf(String node) {
        return nodes.contains(node);
    }

    /**
     * Returns a string representation of this Graph, in
     * the form: node => [node 1, node 2, ... , node n], which means
     * that there is a path from node to node 1, node 2, ... , node n.
     *
     * @return a string representation of this Graph.
     */
    public String toString() {
        int counter = 0;
        String string = "";
        Set<String> keys = map.keySet();
        for (String key : keys) {
            if (counter == 0) {
                string = string + key + "--->" + map.get(key).toString();
            } else {
                string = string + "\n" + key + "--->" + map.get(key).toString();
            }
            counter++;
        }
        return string;
    }
}
