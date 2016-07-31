package map;
import java.io.*;
import java.util.Random;

import main.Settings;
/**
 * <h1>MapGenerator Class for the Thymio project</h1>
 * <h3> Course: Informationssysteme (SS 2016) Universitaet Regensburg</h3>
 * 
 * <div>Dozent: Prof. Dr. Bernd Ludwig</div>
 *   
 * Gruppe 6:
 * Bauer Louisa, Durry Jan, Kasberger Korbinian, Mykyttschak Lina 
 * 
 * Takes generates the CSV File and creates the obstacles (if set: random)
 * 
 * @version 1.0
 * @author Korbinian Kasberger: korbinian1.kasberger@stud.uni-regensburg.de
 */

public class MapGenerator {
	
	
	private static final int probability = Settings.getObstacleProbability();
	private static final int prob_range = Settings.getObstacleProbabilityRange();
	private static final Random r = new Random();
	private static final String source = Settings.getObstacleSrc();
	private static final boolean randomObs = true;

	
/**
 * Constructor
 */
	public MapGenerator(){
		System.out.println("New map created");
		generateMap(source);
	}

	

/**
 * Generates the Map as CSV file
 * 
 * Adds random obstacles if set in Settings Class
 * @param src: File name defined in Settings
 */
	private static void generateMap(String src) {

		boolean alreadyExists = new File(src).exists();

		try{
			if(!alreadyExists){

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



}
