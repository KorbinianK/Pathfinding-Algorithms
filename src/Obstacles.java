import java.util.Random;

public class Obstacles {
	
	private static int probability = Settings.getObstacleProbability();
	private static int prob_range = Settings.getObstacleProbabilityRange();
	private static char[][] obs = new char[Settings.getCanvasHeight()/Settings.getFieldHeight()+1][Settings.getCanvasHeight()/Settings.getFieldHeight()+1];
	Random r = new Random();
	
	
	public Obstacles(){
		createObs(r);
	}
	// Create random Obstacles
	private char[][] createObs(Random r){
		for (int i = 0; i < obs.length; i++) {
			for (int j = 0; j < obs.length; j++) {
				int rnd = r.nextInt(prob_range);
				if(rnd < probability){
					obs[i][j] = 'X';
					
				}else{
					obs[i][j] = ' ';
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
		return createObs(r);
	}
	
	// Check if the position contains an obstacle
	public boolean isObstacle(int x, int y){
		
		if (obs[x][y] == 'X'){
			return true;
		}else{
			return false;
		}
	}
}
