package pathfinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import main.Controller;
import main.Settings;
import map.Chessboard;
import thymio.Thymio;

/*
 * https://www.youtube.com/watch?v=-L-WgKMFuhE
 * loop
    current = node in OPEN with the lowest f_cost
    remove current from OPEN
    add current to CLOSED

    if current is the target node //path has been found
        return

    foreach neighbour of the current node
        if neighbour is not traversable or neighbour is in CLOSED
            skip to the next neighbour

        if new path to neighbour is shorter OR neighbour is not in OPEN
            set f_cost of neighbour
            set parent of neighbour to current 
            if neighbour is not in OPEN
                add neighbour to OPEN
 */

public class AStar {
	private static Chessboard board = Settings.getBoard();
	private static Node start  = Settings.getStartNode();
	private static Node end  = Settings.getEndNode();
	private static Thymio thymio = Controller.thymio;
	private static Node currentNode = thymio.getPosAsNode();


	public static void calculate(){
		List<Edge> edges = new ArrayList<Edge>();
		List<Node> closedList = new ArrayList<Node>();
		
		currentNode = thymio.getPosAsNode();
		while(true){
			if(currentNode == null){
				currentNode = start;
			}
			if(currentNode == end){
				for(Edge e : edges){
					System.out.println("Go from: [" +e.getSource().getChessCoord()+"] to ["+e.getDestination().getChessCoord()+"]");
					thymio.move(e.getDestination().getOrientation());
				}
				System.out.println("done");
				break;
			}
			HashMap<String, Node> openList = new HashMap<>();
			openList = board.getNeighbourNodes(currentNode);
			
			HashMap<Node,Integer> tempEdges = new HashMap<>();
			for ( HashMap.Entry<String, Node> entry : openList.entrySet()) {
			    String direction = entry.getKey();
			    Node neighbour = entry.getValue();
			    if(!closedList.contains(neighbour) && !neighbour.isObstacle()){
			    	int g_cost = calculateCostG(neighbour,direction);
			    	int h_cost = calculateCostH(neighbour);
			    	int f_cost = g_cost+h_cost;
			    	
			    	neighbour.setOrientationByString(direction);
			    	tempEdges.put(neighbour, f_cost);
			    	
						
			    }
			}
			Node nextNode = getNextNode(tempEdges);
			
			closedList.add(nextNode);
			
			int cost = tempEdges.get(nextNode); // Bugged
			
			Edge edge = new Edge(edges.size(), currentNode, nextNode, cost);
			currentNode = nextNode;
			
			edges.add(edge);	
		}
		
	}

	

	private static Node getNextNode(HashMap<Node, Integer> tempEdges) {
		Node currentlyCheapest_node = null;
	    int currentlyCheapest_f = Integer.MAX_VALUE;
	    for(Node node : tempEdges.keySet()) {
	    	
	    	if(node == end){
	    		return node;
	    	}
	        int f_cost = tempEdges.get(node);
	        if(f_cost < currentlyCheapest_f) {
	            currentlyCheapest_f = f_cost;
	            currentlyCheapest_node = node;
	        }else if(f_cost == currentlyCheapest_f){

	        	if(calculateCostH(node) < calculateCostH(currentlyCheapest_node)){
	        		currentlyCheapest_node = node;
	        	}
	        }
	    }
	    
	    return currentlyCheapest_node;
		
	}


	private static int calculateCostH(Node node) {
		int x1 = node.getXCoord();
		int x2 = Settings.getStartX();
		int y1 = node.getYCoord();;
		int y2 = Settings.getStartY();
		int cost = Math.abs(x1 - x2)+Math.abs(y1-y2);
		return cost;
	}


	private static int calculateCostG(Node neighbour, String direction) {
		
		int orientation = thymio.getOrientation();
		
		int cost = 0;
		int bottom = 180;
		int top = 0;
		int left = 270;
		int right = 90;
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
