package pathfinding;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import main.Controller;
import main.Settings;
import thymio.Thymio;

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
	
	
//	Returns the cheapest Neighbour from the current field (avoiding obstacles)
	public static Node getCheapestNeighbour(Node current){
		
		int cheapest = 999;
		String cheapestDirection = null;
		HashMap<String, Node> neighbours = Settings.getBoard().getNeighbourNodes(current);
		for (Map.Entry<String, Node> entry : neighbours.entrySet()) {
		    String direction = entry.getKey();
		    Node neighbour = entry.getValue();
		   if(!getVisitedList().contains(neighbour)){
			   int cost = calculateCost(direction);
				Edge edge = new Edge(edges.size(), current, Settings.getBoard().getNeighbourNodes(current).get(direction), cost);
				edges.add(edge);
				if (cost < cheapest){
					cheapest = cost;
					cheapestDirection = direction;
//				}
		   }
		}
		}
		if(cheapestDirection != null){
			destinationNode = neighbours.get(cheapestDirection);
		}
		
		return destinationNode;
	}
	
//	Returns the cheapest Neighbour as Chess Coordinates
	public static String getCheapestNeighbourChess(Node current){
		Node cheapest = getCheapestNeighbour(current);
		if(cheapest == null){
			return "no unvisited node in reach from this position";
		}
		
		return cheapest.getChessCoord();
	}

	
//	Calculates the Cost: each 90° Rotation = 1 -> Cheapest route is straight ahead
	private static int calculateCost(String direction) {
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


	public static List<Node> getVisitedList() {
		return visited;
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
