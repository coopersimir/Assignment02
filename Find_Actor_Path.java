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
		try {
			Reader reader = new FileReader("/Users/simircooper/Desktop/tmdb_5000_credits.csv");
			CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
			JSONParser jsonParser = new JSONParser();
			Graph<String> movie_graph = new Graph<String>();
			int movies = 0;
			for (CSVRecord csvRecord : csvParser) {
				if(movies==1) {
					String castJSON = csvRecord.get(2); //read cast in 
					Object object = jsonParser.parse(castJSON); //parse cast 
					JSONArray jsonArray = (JSONArray)object; //turn cast into an array
					for (int i = 0; i < jsonArray.size(); i++) {
						for(int w=1; i<jsonArray.size()-1;w++) {
							JSONObject jsonObject1 = (JSONObject)jsonArray.get(i);
							JSONObject jsonObject2 = (JSONObject)jsonArray.get(w);
							movie_graph.addEdge((String)jsonObject1.get("name"),(String)jsonObject2.get("name"), true);
						}
					}
					System.out.println(movie_graph.toString());
				
						
				}
				++movies;
			}
			csvParser.close();
		}
		catch (Exception e) {
			System.out.println("File is invalid or is in the wrong format.");
			
		}

	}

}

