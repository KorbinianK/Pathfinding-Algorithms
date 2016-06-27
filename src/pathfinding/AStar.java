package pathfinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import de.ur.mi.graphics.Color;
import main.Controller;
import main.Helper;
import main.Settings;
import map.Chessboard;
import thymio.Thymio;

/*
 * x
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
	private static List<Node> boardNodes = Settings.getBoardNodes();
	private static Node start;
	private static Node end  = Settings.getEndNode();
	private static Thymio thymio = Controller.thymio;
	private static Node currentNode = thymio.getPosAsNode();


	public static void calculate(){
		List<Edge> edges = new ArrayList<Edge>();
		List<Integer> openList = new ArrayList<Integer>();
		List<Integer> closedList = new ArrayList<Integer>();
		
		int timeout = 0;
		start = thymio.getPosAsNode();
	
		System.out.println("##### Calculating Route from "+start.getChessCoord()+" to "+end.getChessCoord()+" #####");
			System.out.println("____________________________________________");
		while(timeout < 200){
			
			if(currentNode == null){
				currentNode = start;
				start.setParentNode(currentNode);
			}
			if(currentNode == end){
			
				while(true){
					
					if(currentNode.getParent() == null){
						break;
					}
					int parent_id = currentNode.getParent().getId();
					Node parent = boardNodes.get(parent_id);
					parent.setColor(Settings.getColorMovement());

					Edge e = new Edge(edges.size(), parent, currentNode, 0);
					edges.add(e);
					
					currentNode = parent;
					if(currentNode  == start){
						break;
					}
				
				}
				Collections.reverse(edges);
				for(Edge e : edges){
					System.out.println("Go from "
							+e.getSource().getChessCoord()
							+" "
							+Helper.isPositionedTo(e.getSource(), e.getDestination())
							+" to "
							+e.getDestination().getChessCoord());
					thymio.move(Helper.isPositionedTo(e.getSource(), e.getDestination()));
				}
				System.out.println("done");
				break;
			}
			if(openList.isEmpty() == false){
				currentNode = getNextNode(openList);
				
				closedList.add(currentNode.getId());
				openList.remove((Integer)currentNode.getId());
				
			}
			HashMap<String, Node> neighbours = board.getNeighbourNodes(currentNode);
			
			for ( Entry<String, Node> entry : neighbours.entrySet()) {
				
			   String direction = entry.getKey();
			   int id =  entry.getValue().getId();
			   Node neighbour = boardNodes.get(id);
			   neighbour.setOrientationByString(direction);
			   int g_cost = calculateCostG(neighbour,direction);
			   int h_cost = calculateCostH(neighbour);
			   int f_cost = g_cost+h_cost;
			  
			   
			   Node parent = neighbour.getParent();
			   if(parent != null){
				   neighbour.setGCost(parent.getGCost()+g_cost);
			   }else{
				   neighbour.setGCost(g_cost);
			   }
			  
			    neighbour.setFCost(f_cost);
			   
			    if(neighbour.getId() == end.getId()){
			    	end.setParentNode(currentNode);
				   currentNode = end;
				   
			   }
			   else if(!closedList.contains(id) && !neighbour.isObstacle()){
				   neighbour.setParentNode(currentNode);
				   
				   if(openList.contains(id)){
					  Node fromOpen = boardNodes.get(id);
					  if(fromOpen.getGCost() < g_cost){
						  boardNodes.get(neighbour.getId()).setParentNode(currentNode);
						  boardNodes.get(neighbour.getId()).setGCost(g_cost);
					  }
				   }else{
//					   Node fromOpen = boardNodes.get(id);
//					   fromOpen.setColor(Color.WHITE);
					   
					   openList.add(id);
   
				   }
			   }
			   			   
			}
			timeout++;
		}
		if(timeout == 200){
			System.out.println("No Route possible");
		}
		
	}

	

	


	private static Node getNextNode(List<Integer> open) {
		Node currentlyCheapest_node = new Node(999, 999, 999, "", 999);
		
	    int currentlyCheapest_f = Integer.MAX_VALUE;
	    for(int id : open){
	    	
	    	Node node = boardNodes.get(id);
	    	if(node == end){
	    		return node;
	    	}
	    	
	    	int f_cost = node.getFCost();
	    	
	    	if(f_cost < currentlyCheapest_node.getFCost() ){
	    		
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
		int x1 = node.getXCoord()/Settings.getFieldHeight();
		int x2 = Settings.getEndX()/Settings.getFieldHeight();
		int y1 = node.getYCoord()/Settings.getFieldHeight();
		int y2 = Settings.getEndY()/Settings.getFieldHeight();
		int cost = Math.abs(x1 - x2)+Math.abs(y1-y2);
		return cost;
	}


	private static int calculateCostG(Node neighbour, String direction) {
		
		int orientation = currentNode.getOrientation();
		
		int cost = 0;
		int bottom = 180;
		int top = 0;
		int left = 270;
		int right = 90;
		switch (direction) {
		case "bottom":
			if((orientation > bottom || orientation < bottom)  && orientation != top){
				cost = 3;
			}else if(orientation == top){
				cost = 3;
			}else{
				cost =1;
			}
			return cost;
		case "top":
			if(orientation > top && orientation != bottom){
				cost = 3;
			}else if(orientation == bottom){
				cost = 3;
			}else{
				cost =1;
			}
		return cost;
		case "left":
			if((orientation == top || orientation == bottom)){
				cost = 3;
			}else if(orientation == right){
				cost = 3;
			}else{
				cost =1;
			}
		return cost;
		case "right":
			if((orientation == top || orientation == bottom)){
				cost = 3;
			}else if(orientation == left){
				cost = 3;
			}else{
				cost =1;
			}
		return cost;
		default:
			return cost;
		}
		
	}
}
