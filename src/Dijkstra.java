import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dijkstra {

	private static String[][] board = Settings.getBoard().boardAsArray();
	private static Thymio thymio =  Controller.thymio;
	private static List<String> visited = new ArrayList<String>();
	private static int currentCost = 0;
	
	public static void print(){
		System.out.println(Arrays.deepToString(board));
	}
	
//	Gets only the X Coordinate of the checked field
	private static int getX(String[][] board, int x, int y){
		String[] arr = board[x][y].split(",");
		int intX = Integer.parseInt(arr[0]); 
		return intX;
	}
	
//	Gets only the Y Coordinate of the checked field
	private static int getY(String[][] board, int x, int y){
		String[] arr = board[x][y].split(",");
		int intY = Integer.parseInt(arr[1]); 
		return intY;
	}
	
//	Returns the cheapest Neighbour from the current field (avoiding obstacles)
	public static String getCheapestNeighbour(int x, int y){
		String destination  = null;
		int intStartX = getX(board,x,y); 
		int intStartY = getY(board,x,y);
		int currentX = intStartX;
		int currentY= intStartY;
		int cheapest = 999;
		String cheapestDirection = null;
		String direction = "";
		if(Settings.getBoard().fieldHasBottomNeighbour(currentX, currentY)){
			if(!getVisitedArray().contains(Settings.getBoard().bottomNeighbour(currentX, currentY))){
				direction = "bottom";
				int cost = calculateCost(currentX,currentY,direction);
				if (cost < cheapest){
					cheapest = cost;
					cheapestDirection = direction;
				}
//				System.out.println("Cost to bottom "+cost);
			}
		}
		if(Settings.getBoard().fieldHasTopNeighbour(currentX, currentY)){
			if(!getVisitedArray().contains(Settings.getBoard().topNeighbour(currentX, currentY))){
				direction = "top";
				int cost = calculateCost(currentX,currentY,direction);
				if (cost < cheapest){
					cheapest = cost;
					cheapestDirection = direction;
				}
//				System.out.println("Cost to top "+cost);
			}
		}
		if(Settings.getBoard().fieldHasRightNeighbour(currentX, currentY)){
			if(!getVisitedArray().contains(Settings.getBoard().rightNeighbour(currentX, currentY))){
				direction = "right";
				int cost = calculateCost(currentX,currentY,direction);
				if (cost < cheapest){
					cheapest = cost;
					cheapestDirection = direction;
				}
//				System.out.println("Cost to right "+cost);
			}
		}
		if(Settings.getBoard().fieldHasLeftNeighbour(currentX, currentY)){
			if(!getVisitedArray().contains(Settings.getBoard().leftNeighbour(currentX, currentY))){
				direction = "left";
				int cost = calculateCost(currentX,currentY,direction);
				if (cost < cheapest){
					cheapest = cost;
					cheapestDirection = direction;
				}
//				System.out.println("Cost to left "+cost);
			}
			
		}
		
		if(cheapestDirection != null){
			switch (cheapestDirection) {
			case "right":
			
				destination = Settings.getBoard().rightNeighbour(currentX, currentY);
//				thymio.moveRight();		
				break;
			case "top":
				destination = Settings.getBoard().topNeighbour(currentX, currentY);
//				thymio.moveUp();			
				break;
			case "left":
				destination = Settings.getBoard().leftNeighbour(currentX, currentY);
//				thymio.moveLeft();			
				break;
			case "bottom":
				destination = Settings.getBoard().bottomNeighbour(currentX, currentY);
//				thymio.moveDown();
				break;
			}
		}
//		System.out.println(cheapestDirection);
		return destination;
	}
	
//	Returns the cheapest Neighbour as Chess Coordinates
	public static String getCheapestNeighbourChess(int x, int y){
		String cheapest = getCheapestNeighbour(x,y);
		String[] arr = cheapest.split(",");
		int intX = Integer.parseInt(arr[0]); 
		int intY = Integer.parseInt(arr[1]); 
		String[][] board = Settings.getBoard().boardAsStringArray();
		return board[intX][intY];
	}

	
//	Calculates the Cost: each 90° Rotation = 1 -> Cheapest route is straight ahead
	private static int calculateCost(int currentX, int currentY, String direction) {
		int cost = 0;
		int bottom = 180;
		int top = 0;
		int left = 270;
		int right = 90;
		int orientation = thymio.getOrientation();
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

	
//	Setter and getter for the visited list
	public static List<String> getVisitedArray() {
		return visited;
	}

	public static void setVisitedArray(List<String> visited) {
		Dijkstra.visited = visited;
	}
	
	
//	Adds the position to the visited List 
	public static void addToVisited(String coordinates){
		if(!visited.contains(coordinates)){
			
			visited.add(coordinates);
		}else{
			System.out.println(coordinates+" already visited.");
		}

		
	}
}
