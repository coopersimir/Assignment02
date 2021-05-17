/**
 * @author Simir Cooper 
 * @since 5/17/21
 * This program takes in a csv file and constructs a searchable graph to find the path between 2 actors.
 */
import java.io.FileReader;
import java.io.Reader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import Graph_Resources.*;
import java.util.*;
public class Actor_to_Actor {

	public static void main(String[] args) {
		ShortestPath movie_graph = new ShortestPath();
		//construct the graph
		try {
			Reader reader = new FileReader(args[0]);
			CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
			JSONParser jsonParser = new JSONParser();
			int movies = 0;
			for (CSVRecord csvRecord : csvParser) {
				if(movies>=1) {
					String castJSON = csvRecord.get(2); //read cast in 
					Object object = jsonParser.parse(castJSON); //parse cast 
					JSONArray jsonArray = (JSONArray)object; //turn cast into an array
					//double forloop creates the graph 
					for (int i = 0; i < jsonArray.size()-1; i++) {
						JSONObject jsonObject1 = (JSONObject)jsonArray.get(i);
						JSONObject jsonObject2 = (JSONObject)jsonArray.get(i+1);
						movie_graph.addEdge((String)jsonObject1.get("name"),(String)jsonObject2.get("name"));
					}
				}
				++movies;
			}
			csvParser.close();
		}
		
		catch(Exception e){
			System.out.println("File "+args[0]+" is invalid or is in the wrong format.");
		}
		//Game Code Starts 
		boolean play = true;
		while(play==true){
			System.out.println("Welcome to Six Degrees of Kevin Bacon:"); //introduction
			System.out.println("Please enter two actor names to see how they're connected. Press enter after each input."); 
			Scanner myscan = new Scanner(System.in);
			String actor1 = myscan.nextLine();
			String actor2 = myscan.nextLine();
			if(movie_graph.memberOf(actor1)==false||movie_graph.memberOf(actor2)==false) {
				System.out.println("Actor not found.");
				System.out.println("Would you like to play again? Enter y for yes or n for no and press enter.");
				Scanner playscan = new Scanner(System.in);
				String answer = playscan.nextLine();
				if(answer.toLowerCase().equals("y")) {
					play = true;
				}
				else {
					play = false;
				}
			
			}else {
				LinkedList actors = movie_graph.breadthFirstSearch(movie_graph, actor1, actor2); //linked list with path
				String result = "";
		    	for(int i = 0; i < actors.size(); i++) {
		    		if(i ==actors.size()-1) {
		    			result+= actors.get(i)+ "";
		    		}else {
		    			result+= actors.get(i)+ "--> ";
		    		}
		    	}
		    	System.out.println(result); //print path
		    	System.out.println("Would you like to play again? Enter y for yes or n for no and press enter.");
		    	Scanner playscan2 = new Scanner(System.in);
		    	String answer2 = playscan2.nextLine();
		    	if(answer2.toLowerCase().equals("y")) {
					play = true;
				}
				else {
					play = false;
				}
		    	
			}
			
			
		 
		}
           
    }

	

}


