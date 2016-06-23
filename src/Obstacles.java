import java.util.Random;

public class Obstacles {
	
	private static int probability = Settings.getObstacleProbability();
	private static int prob_range = Settings.getObstacleProbabilityRange();
	private static char[][] obs = new char[Settings.getCanvasWidth()/Settings.getFieldHeight()+1][Settings.getCanvasHeight()/Settings.getFieldHeight()+1];
	Random r = new Random();
	
//	Constructor
	public Obstacles(){
		if(Settings.randomObstacles()){
			createRandomObstacles(r);
		}else{
			createFixedObstacles();
		}
		
	}
	
	private char[][] createFixedObstacles() {
		
		
		int x = 0;
		for(String[] entry : Settings.getCsv()){
			
			for (int i = 0; i < entry.length; i++) {
				String t = entry[i];
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

	// Create random Obstacles
	private char[][] createRandomObstacles(Random r){
		for (int i = 0; i < obs.length; i++) {
			for (int j = 0; j < obs[0].length; j++) {
				int rnd = r.nextInt(prob_range);
				if(rnd < probability){
					obs[i][j] = '1';
					
				}else{
					obs[i][j] = '0';
				}
			}
		}	
		return freeImportantFields(obs);
	}
	
	
	// Removes Thymios Start field as well as the End to avoid some impossible cases
	private char[][] freeImportantFields(char[][] obs) {
		Obstacles.obs = obs;
		obs[Settings.getStartX()/Settings.getFieldHeight()][Settings.getStartY()/Settings.getFieldHeight()] = ' ';
		obs[Settings.getEndX()/Settings.getFieldHeight()][Settings.getEndY()/Settings.getFieldHeight()] = ' ';
		return obs;
	}

	// Creates obstacles based on probability and returns them
	public char[][] getObstacles(int probabilitiy){
		Obstacles.probability = probabilitiy;
		return createRandomObstacles(r);
	}
	
	// Check if the position contains an obstacle
	public boolean isObstacle(int x, int y){
		if(obs.length > 0){
			if (obs[x][y] == '1' ){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
}
