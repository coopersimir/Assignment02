import java.io.FileReader;
import java.io.Reader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import Graph_Resources.*;
public class Find_Actor_Path {

	public static void main(String[] args) {
		ShortestPath movie_graph = new ShortestPath();
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
		System.out.println(movie_graph.breadthFirstSearch(movie_graph,"Hugh Jackman", "Patrick Stewart"));

	}

}

