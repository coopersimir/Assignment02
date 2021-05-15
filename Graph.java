
package Graph_Resources;
import java.util.*;
public class Graph<T> {
	 public Map<T, LinkedList<T>> map = new HashMap<>();
	 /**
	  * Add vertex 
	  * @param v
	  */
	 public void addVertex(T v) {
		 map.put(v, new LinkedList<T>());
	 }
	 /**
	  * Add edge
	  * @param source
	  * @param destination
	  * @param bidirectional
	  */
	 public void addEdge(T source,T destination,boolean bidirectional) {
		 if (!map.containsKey(source)) {
			 addVertex(source);
		 }
		 if (!map.containsKey(destination)) {
			 addVertex(destination);
		 }
		 map.get(source).add(destination);
		 if (bidirectional == true) {
	            map.get(destination).add(source);
	        }
	           
	 }
	 /**
	  * Get count of vertices 
	  */
	 public int getVertexCount() {
	        System.out.println("The graph has "+ map.keySet().size()+ " vertices");
	        return map.keySet().size();
	 }
	 /**
	  * 
	  * @param bidirection 
	  */
	 public int getEdgesCount(boolean bidirection)
	    {
	        int count = 0;
	        for (T v : map.keySet()) {
	            count += map.get(v).size();
	        }
	        if (bidirection == true) {
	            count = count / 2;
	        }
	        System.out.println("The graph has "+ count+ " edges.");
	        return count;
	    }
	 /**
	  * 
	  * @param v
	  */
	 public boolean hasVertex(T v)
	    {
	        if (map.containsKey(v)) {
	            System.out.println("The graph contains "+ v + " as a vertex.");
	            return true;
	        }
	        else {
	            System.out.println("The graph does not contain "+ v + " as a vertex.");
	        }
	        return false;
	    }
	 public boolean hasEdge(T v1, T v2)
	    {
	        if (map.get(v1).contains(v2)) {
	            System.out.println("The graph has an edge between "+ v1 + " and " + v2 + ".");
	            return true;
	        }
	        else {
	            System.out.println("The graph has no edge between "+ v1 + " and " + v2 + ".");
	        }
	        return false;
	    }
	 public String toString()
	    {
	        StringBuilder builder = new StringBuilder();
	  
	        for (T v : map.keySet()) {
	            builder.append(v.toString() + ": ");
	            for (T w : map.get(v)) {
	                builder.append(w.toString() + " ");
	            }
	            builder.append("\n");
	        }
	  
	        return (builder.toString());
	    }
	 
	

}
