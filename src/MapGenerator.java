import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MapGenerator {
	
	
	private static int probability = Settings.getObstacleProbability();
	private static int prob_range = Settings.getObstacleProbabilityRange();
	private static Random r = new Random();
	private static String source;
	private static boolean randomObs = Settings.randomObstacles();
	private static List<Node> nodes = new ArrayList<Node>();

	
	
	public MapGenerator(){
		if(randomObs){
			source = Settings.getObstacleSrc();
		}else{
			source =  Settings.getEmptySrc();
		}	
		
		generateMap(source);
		generateNodes();
		
	}

	private static void generateNodes() {
		for (int i = 0; i < Settings.getCanvasHeight(); i++) {
			  for (int j = 0; j < Settings.getCanvasWidth(); j++) {

					int mult = (int) (j*Settings.getCanvasWidth());
					int id = i+mult;
					Node node = new Node(id, i, j, null, 0);
					nodes.add(node);
			  }
		}

	}

	private static void generateMap(String src) {
		
		boolean alreadyExists = new File(src).exists();
		try{
			if(!alreadyExists){
				File file = new File(src);
				
				  FileWriter writer = new FileWriter(file,true);
					
				  for (int i = 0; i < Settings.getCanvasHeight()/Settings.getFieldHeight(); i++) {
					  for (int j = 0; j < Settings.getCanvasWidth()/Settings.getFieldHeight(); j++) {
						  	int rnd = r.nextInt(prob_range);
						  	if(randomObs){
								if(rnd < probability){
									writer.append("1");	
								}else{
									writer.append("0");
								}
						  	}else{
						  		writer.append("0");
						  	}
							
							if(j+1 != Settings.getCanvasWidth()){
								writer.append(',');
							}
						  }
					  writer.append('\n');
				}					
				    writer.flush();
				    writer.close();
				   
			}
		  
		}
		catch(IOException e){
		     e.printStackTrace();
		} 
}

	
	
	
	public static String getSource(){
		return source;
	}
	
	public List<Node> getNodes(){
		return nodes;
	}

}
