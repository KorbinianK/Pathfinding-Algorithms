import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;






/* ToDo:
 * Costs Formula: min(infinite,current+destination)
 * calc cost to all other nodes that are connected to the current node (getCheapestNeighbour Method?)
 * Save them in hashmap
 * Remember/save orientation on every node
 * Pick lowest cost and continue
 * 
 */

public class Dijkstra {
	
	
	


	private static String[][] board = Settings.getBoard().boardAsArray();
	private static Thymio thymio =  Controller.thymio;
	
	private static int currentCost = 0;
	protected static List<Node> visited = new ArrayList<Node>();
	protected static List<Edge> edges = new ArrayList<Edge>();
	private static Node destinationNode;
	
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
	public static Node getCheapestNeighbour(int x, int y){
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
				Edge edge = new Edge(edges.size(), Settings.getBoard().getNodes().get(Controller.thymio.getPosAsID()), Settings.getBoard().asNode(direction, currentX, currentY), cost);
				edges.add(edge);
				if (cost < cheapest){
					cheapest = cost;
					cheapestDirection = direction;
				}
//				System.out.println("Cost to bottom "+edge.getCost());
			
			}
		}
		if(Settings.getBoard().fieldHasTopNeighbour(currentX, currentY)){
			if(!getVisitedArray().contains(Settings.getBoard().topNeighbour(currentX, currentY))){
				direction = "top";
				int cost = calculateCost(currentX,currentY,direction);
				Edge edge = new Edge(edges.size(), Settings.getBoard().getNodes().get(currentX+currentY), Settings.getBoard().asNode(direction, currentX, currentY), cost);
				edges.add(edge);
				if (cost < cheapest){
					cheapest = cost;
					cheapestDirection = direction;
				}
//				System.out.println("Cost to top "+edge.getCost());
			}
		}
		if(Settings.getBoard().fieldHasRightNeighbour(currentX, currentY)){
			if(!getVisitedArray().contains(Settings.getBoard().rightNeighbour(currentX, currentY))){
				direction = "right";
				int cost = calculateCost(currentX,currentY,direction);
				Edge edge = new Edge(edges.size(), Settings.getBoard().getNodes().get(currentX+currentY), Settings.getBoard().asNode(direction, currentX, currentY), cost);
				edges.add(edge);
				if (cost < cheapest){
					cheapest = cost;
					cheapestDirection = direction;
				}
//				System.out.println("Cost to right "+edge.getCost());
			}
		}
		if(Settings.getBoard().fieldHasLeftNeighbour(currentX, currentY)){
			if(!getVisitedArray().contains(Settings.getBoard().leftNeighbour(currentX, currentY))){
				direction = "left";
				int cost = calculateCost(currentX,currentY,direction);
				Edge edge = new Edge(edges.size(), Settings.getBoard().getNodes().get(currentX+currentY), Settings.getBoard().asNode(direction, currentX, currentY), cost);
				edges.add(edge);
				if (cost < cheapest){
					cheapest = cost;
					cheapestDirection = direction;
				}
//				System.out.println("Cost to left "+edge.getCost());
			}
		}
		
		if(cheapestDirection != null){
			destinationNode = Settings.getBoard().asNode(cheapestDirection, currentX, currentY);
		}
//		System.out.println(cheapestDirection);
		
		return destinationNode;
	}
	
//	Returns the cheapest Neighbour as Chess Coordinates
	public static String getCheapestNeighbourChess(int x, int y){
		Node cheapest = getCheapestNeighbour(x,y);
		if(cheapest == null){
			return "no unvisited node in reach from this position";
		}
		
		return cheapest.getChessCoord();
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
	public static List<Node> getVisitedArray() {
		return visited;
	}

	public static void setVisitedArray(List<Node> visited) {
		Dijkstra.visited = visited;
	}
	
	
	
//	Adds the position to the visited List 
	public static void addToVisited(Node node, int orientation){
		if(!visited.contains(node)){
			visited.add(node);
			node.setOrientation(orientation);
		}else{
			System.out.println(node.getChessCoord()+" already visited.");
		}
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
