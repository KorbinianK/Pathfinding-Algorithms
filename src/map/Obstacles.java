package map;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import main.Helper;
import main.Settings;
import pathfinding.Node;

public class Obstacles {
	
	private static int probability = Settings.getObstacleProbability();
	private static int prob_range = Settings.getObstacleProbabilityRange();
	
	private static List<Node> obstacleList = new ArrayList<Node>();

	static Random r = new Random();
	private static char[][] obs = new char[Settings.getBoardArrayWidth()][Settings.getBoardArrayHeight()];
	

//	Creates Obstacles from CSV file
	public static char[][] createFixedObstacles() {
		
		int x = 0;
		List<String[]> csv = Settings.getCsv();
		for(String[] row : csv){
			
			for (int i = 0; i < row.length; i++) {
				String t = row[i];
				char[] arr = t.toCharArray();
				for (int j = 0; j < arr.length; j++) {
					
					obs[i][x] = arr[j];	
				}
			}
			x++;	
		}
		if(obs.length <1){
			return obs;
		}
		return freeImportantFields(obs);
	}


	
	public static char[][] getObstaclesArray(){
		return createFixedObstacles();
	}

	// Create random Obstacles
	private static char[][] createRandomObstacles(Random r){
		
		for (int i = 0; i < obs.length; i++) {
			for (int j = 0; j < obs[0].length; j++) {
				int rnd = r.nextInt(prob_range);
				if(rnd < probability){
					int id = Helper.calculateID(i, j);
					Node obstacle = Settings.getBoardNodes().get(id);
					obstacleList.add(obstacle);
					obs[i][j] = '1';
					
				}else{
					obs[i][j] = '0';
				}
			}
		}	
		return freeImportantFields(obs);
	}
	
	
	// Removes Thymios Start field as well as the End to avoid some impossible cases
	private static char[][] freeImportantFields(char[][] obs) {
		Obstacles.obs = obs;
		
		for(Node node : obstacleList){
			if(node == Settings.getStartNode()){
				obstacleList.remove(node);
				obs[node.getXCoord()][node.getYCoord()] = 0;
			}
			if(node == Settings.getEndNode()){
				obstacleList.remove(node);
				obs[node.getXCoord()][node.getYCoord()] = 0;
			}
		}
		
		return obs;
	}

	// Creates obstacles based on probability and returns them
	public char[][] setObstacles(int probabilitiy){
		Obstacles.probability = probabilitiy;
		return createRandomObstacles(r);
	}
	
	
}
