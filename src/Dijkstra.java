import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dijkstra {

	private static String[][] board = Settings.getBoard().boardAsArray();
	private static Thymio thymio =  Controller.thymio;
	static List<String> visited = new ArrayList<String>();
//	private static Thymio thymio2 = new Thymio(orig_thymio.getXPos(), orig_thymio.getYPos(), Settings.getFieldHeight(), Settings.getFieldHeight(), Settings.getThymioImg());
	
	public static void print(){
		System.out.println(Arrays.deepToString(board));
	}
	
	private static int getX(String[][] board, int x, int y){
		String[] arr = board[x][y].split(",");
		int intX = Integer.parseInt(arr[0]); 
		return intX;
	}
	private static int getY(String[][] board, int x, int y){
		String[] arr = board[x][y].split(",");
		int intY = Integer.parseInt(arr[1]); 
		return intY;
	}
	
	public static int calcCostToSurrounding(int x, int y){
		int distance = 0;
		int intStartX = getX(board,x,y); 
		int intStartY = getY(board,x,y);
		visited.add(board[x][y]);
		System.out.println(visited);
		int currentX = intStartX;
		int currentY= intStartY;
		int cheapest = 999;
		String cheapestDirection = null;

		String direction = "";
		if(Settings.getBoard().fieldHasBottomNeighbour(currentX, currentY)){
			direction = "bottom";
			int cost = calculateCost(currentX,currentY,direction);
			if (cost < cheapest){
				cheapest = cost;
				cheapestDirection = direction;
			}
			System.out.println("Cost to bottom "+cost);
		}
		if(Settings.getBoard().fieldHasTopNeighbour(currentX, currentY)){
			direction = "top";
			int cost = calculateCost(currentX,currentY,direction);
			if (cost < cheapest){
				cheapest = cost;
				cheapestDirection = direction;
			}
			System.out.println("Cost to top "+cost);
		}
		if(Settings.getBoard().fieldHasRightNeighbour(currentX, currentY)){
			direction = "right";
			int cost = calculateCost(currentX,currentY,direction);
			if (cost < cheapest){
				cheapest = cost;
				cheapestDirection = direction;
			}
			System.out.println("Cost to right "+cost);
		}
		if(Settings.getBoard().fieldHasLeftNeighbour(currentX, currentY)){
			direction = "left";
			int cost = calculateCost(currentX,currentY,direction);
			if (cost < cheapest){
				cheapest = cost;
				cheapestDirection = direction;
			}
			System.out.println("Cost to left "+cost);
		}
		System.out.println(cheapestDirection);
		if(cheapestDirection != null){
			switch (cheapestDirection) {
			case "right":
				thymio.moveRight();		
				break;
			case "top":
				thymio.moveUp();			
				break;
			case "left":
				thymio.moveLeft();			
				break;
			case "bottom":
				thymio.moveDown();
				break;
			}
		}
		
		return distance;
	}

	private static int calculateCost(int currentX, int currentY, String direction) {
		int cost = 0;
		int bottom = 180;
		int top = 0;
		int left = 270;
		int right = 90;
		int orientation = thymio.getOrientation();
//		System.out.println("orientation="+orientation);
//		thymio.setOrientation(direction);
		switch (direction) {
		case "bottom":
			
				if((orientation > bottom || orientation < bottom)  && orientation != top){
					cost = 2;
				}else if(orientation == top){
					cost = 3;
				}else{
					cost +=1;
				}
			
			
			return cost;
		case "top":
				if(orientation > top && orientation != bottom){
					cost += 2;
				}else if(orientation == bottom){
					
					cost += 3;
				}else{
					cost +=1;
				}
			

			return cost;
		case "left":
			
				if((orientation == top || orientation == bottom)){
					cost += 2;
				}else if(orientation == right){
					cost += 3;
				}else{
					cost +=1;
				}
			
			
			return cost;
		case "right":
			
				if((orientation == top || orientation == bottom)){
					cost += 2;
				}else if(orientation == left){
					cost += 3;
				}else{
					cost +=1;
				}
			
			
			return cost;
		default:
			return cost;
		}
		
	
		
	}
}
