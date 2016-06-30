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
	private static final int COST_TURN = 1;
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
		start.setOrientation(thymio.getOrientation());
		System.out.println("##### Calculating Route from "+start.getChessCoord()+" to "+end.getChessCoord()+" #####");
			System.out.println("____________________________________________");
		while(true){
			
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
				currentNode.setColor(Color.MAGENTA);
				closedList.add(currentNode.getId());
				openList.remove((Integer)currentNode.getId());
				
			}
			HashMap<String, Node> neighbours = board.getNeighbourNodes(currentNode);
			List<Integer> neighbourIDs = board.getNeighbourIDs(currentNode);
//			for ( Entry<String, Node> entry : neighbours.entrySet()) {
			for(int id : neighbourIDs){
				
//			   String direction = entry.getKey();
			   Node neighbour = board.getNodeByID(id);
//			   neighbour.setOrientationByString(direction);
			   
			   if(neighbour.getId() == end.getId()){
			    	end.setParentNode(currentNode);
				   currentNode = end;
				   
			   }
			   
			   else if(!closedList.contains(neighbour.getId()) && !neighbour.isObstacle()){
				   int g_cost = 0;
				   
				   int h_cost = 0;
				   int f_cost = g_cost+h_cost;
				   
				   neighbour.setFCost(f_cost);
				   
				   neighbour.setHCost(h_cost);
				   neighbour.setColor(Color.GREEN);
				   openList.add(neighbour.getId());
			   }
			   			   
			}
			timeout++;
			try {
				Thread.sleep(0);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
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
	    		if(node.getHCost() < currentlyCheapest_node.getHCost()){
	        		currentlyCheapest_node = node;
	        	}
	    	}
	    }
	 
		return currentlyCheapest_node;
	}




	public static int calculateCostH(Node node) {
		
		int mult = Settings.getHeuristicModifier();
		int x1 = node.getXCoord();
		int y1 = node.getYCoord();
		int x2 = Settings.getThymioEndField_X();
		int y2 = Settings.getThymioEndField_Y();
		int cost = Math.abs((x1 - x2) + (y1-y2));
//		System.out.println("x1:"+x1+" x2:"+x2+" y1:"+y1+" y2:"+y2);
		return mult*cost;
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
				cost = COST_TURN*2;
			}else if(orientation == top){
				cost = COST_TURN*3;
			}else{
				cost =COST_TURN;
			}
			return cost;
		case "top":
			if(orientation > top && orientation != bottom){
				cost = COST_TURN*2;
			}else if(orientation == bottom){
				cost = COST_TURN*3;
			}else{
				cost =COST_TURN;
			}
		return cost;
		case "left":
			if((orientation == top || orientation == bottom)){
				cost = COST_TURN*2;
			}else if(orientation == right){
				cost = COST_TURN*3;
			}else{
				cost =COST_TURN;
			}
		return cost;
		case "right":
			if((orientation == top || orientation == bottom)){
				cost = COST_TURN*2;
			}else if(orientation == left){
				cost = COST_TURN*3;
			}else{
				cost =COST_TURN;
			}
			
		return cost;
		default:
			return cost;
		}
		
	}
}
