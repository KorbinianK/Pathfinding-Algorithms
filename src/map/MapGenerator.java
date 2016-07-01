package map;
import java.io.*;
import java.util.Random;

import main.Settings;


public class MapGenerator {
	
	
	private static int probability = Settings.getObstacleProbability();
	private static int prob_range = Settings.getObstacleProbabilityRange();
	private static Random r = new Random();
	private static String source;
	
	private static boolean randomObs = true;

	
//	Constructor
	public MapGenerator(){
		
		source = Settings.getObstacleSrc();
		
		System.out.println("No map found, created map");
		generateMap(source);

	}

	

//	If no CSV file exists, a new Map will be generated
	private static void generateMap(String src) {

		boolean alreadyExists = new File(src).exists();

		try{
			if(!alreadyExists  ){

				File file = new File(src);
				
				  FileWriter writer = new FileWriter(file,true);
					
				  for (int i = 0; i < Settings.getCanvasHeight()/Settings.getFieldHeight(); i++) {
					  for (int j = 0; j < Settings.getCanvasWidth()/Settings.getFieldHeight(); j++) {
						  	int rnd = r.nextInt(prob_range);
						  	if(randomObs ){
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


}
