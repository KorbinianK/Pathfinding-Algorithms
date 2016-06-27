package map;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import main.Settings;
import pathfinding.Node;

public class Obstacles {

	
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

	
	
//	 Removes Thymios Start field as well as the End to avoid some impossible cases
	private static char[][] freeImportantFields(char[][] obs) {
		Obstacles.obs = obs;
		
		for(Node node : obstacleList){
			if(node == Settings.getStartNode()){
				node.setObstacle(false);
				obstacleList.remove(node);
				obs[node.getXCoord()][node.getYCoord()] = 0;
			}
			if(node == Settings.getEndNode()){
				node.setObstacle(false);
				obstacleList.remove(node);
				obs[node.getXCoord()][node.getYCoord()] = 0;
			}
		}
		
		return obs;
	}

	
}
