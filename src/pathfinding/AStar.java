package pathfinding;

import java.awt.Container;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import main.Controller;
import main.Helper;
import main.Settings;
import map.Chessboard;
import thymio.ThymioHandler;
/**
 * <h1> A* Reader Class for the Thymio project</h1>
 * <h3> Course: Informationssysteme (SS 2016) Universitaet Regensburg</h3>
 * 
 * <div>Dozent: Prof. Dr. Bernd Ludwig</div>
 * 
 * 
 * Calculates the shortes Path between two Tiles using A* and the Manhattan Heuristic
 * 
 * @version 1.0
 * @author Jan Durry: jan.durry@stud.uni-regensburg.de
 */


public class AStar {

	private static final int COST_TURN = Settings.getTurnCost();

	private static final Chessboard board = Settings.getBoard();
	private static final List<Node> boardNodes = Settings.getBoardNodes();

	private static final Node end  = Settings.getEndNode();
	private static final ThymioHandler thymio = Controller.thymio;

	private static final String TOP = "north";
	private static final String BOTTOM ="south";
	private static final String LEFT = "west";
	private static final String RIGHT = "east";

	private static Node currentNode;
	private static Node start;
	private boolean finished = false;
	private int timeout = 0;

	private static List<Edge> edges;
	private static List<Integer> openList;
	private static List<Integer> closedList;
	
	/**
	 * Calculation of A*
	 */
	public void calculate(){
		
		
		
		initAStar();
		while(true){
			
			if(currentNode == null){
				currentNode = start;
				
			}
			if(currentNode == end){
			
				while(true){
					if(currentNode.getParent() == null){
						break;
					}
					calculatePath();
					if(currentNode  == start){
							finished = true;
							break;
						}
					}
				
				moveThymio();
				break;
			}
			if(openList.isEmpty() && timeout > 1){
				break;
			}else if(openList.isEmpty() == false){
				closedList.add(currentNode.getId());
				if(Settings.showClosedList()){
					currentNode.close();
				}
				currentNode = getNextNode(openList);
				openList.remove((Integer)currentNode.getId());
			}
			
			
			
			HashMap<Node, String> directions = board.getNeighbourDirection(currentNode);
			List<Integer> neighbourIDs = board.getNeighbourIDs(currentNode);
			for(int id : neighbourIDs){
				
				
			  Node neighbour = board.getNodeByID(id);
			  String direction = directions.get(neighbour);
			   if(neighbour.getId() == end.getId()){
			    	end.setParentNode(currentNode);
				   currentNode = end;
			   } else if(!closedList.contains(neighbour.getId()) && !neighbour.isObstacle()){
				  
				   int h_cost = neighbour.getHCost();
				   int curr = currentNode.getGCost();
				   int g_cost = calculateCostG(neighbour, direction)+curr;
				   int f_cost = g_cost+h_cost;
				   
				   if(openList.contains(neighbour.getId())){
					   //already open -> check if cheaper
					   int previousF = neighbour.getFCost();
					   if(f_cost < previousF){
						   updateNeighbour(neighbour,direction, f_cost,g_cost);
						   
					   }
				   }else{
					   // not in open list
					   updateNeighbour(neighbour,direction, f_cost,g_cost);
					   addTopOpen(neighbour);
				   }
				  
			   }
			   			   
			}
			
			
			timeout++;
			if(Settings.delayed()){
				delay();
			}
		}
		
			if(!finished){
				System.out.println("No route possible");
			}

	}

	

	

	/**
	 * Delays the Thread
	 */
	private void delay() {
		try {
			Thread.sleep(Settings.getDelay());
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}


	/**
	 * Takes a node and adds its ID to the open List
	 * @param node
	 */
	private void addTopOpen(Node node) {
		if(Settings.showOpenList()){
			node.open();
		}		
	    openList.add(node.getId());
	}


	/**
	 * Updates the attributes of a Node
	 * 
	 * @param node: The node
	 * @param direction: Direction of Thymio on this Node
	 * @param f_cost: The total Cost to get here
	 * @param g_cost: The movement cos to get here
	 */
	private void updateNeighbour(Node node,String direction, int f_cost, int g_cost) {
		node.setOrientationByString(direction);
		node.setParentNode(currentNode);
		node.setGCost(g_cost);
		node.setFCost(f_cost);
		   
	}


	/**
	 * If A* successfully finished, this method moves Thymio along the route
	 */
	private void moveThymio() {
		Collections.reverse(edges);
		for(Edge e : edges){
			System.out.println(e.print());
			thymio.move(Helper.isPositionedTo(e.getSource(), e.getDestination()));
		}
		System.out.println("done");
	}



	/**
	 * A* finished, creates the Path by checking the parent Nodes
	 */
	private void calculatePath() {
	
		int parent_id = currentNode.getParent().getId();
		Node parent = boardNodes.get(parent_id);
		Edge e = new Edge(edges.size(), parent, currentNode);
		edges.add(e);
		currentNode = parent;
	}


	/**
	 * Initialization for A*, emtpies all Lists
	 */
	private void initAStar() {
		edges = new ArrayList<Edge>();
		openList = new ArrayList<Integer>();
		closedList = new ArrayList<Integer>();
		start = thymio.getPosAsNode();
		start.setOrientation(thymio.getOrientation());
		currentNode = start;
		start.setParentNode(currentNode);
		System.out.println("##### Calculating Route from "+start.getChessCoord()+" to "+end.getChessCoord()+" #####");
		System.out.println("____________________________________________");
		
	}



	/**
	 * Checks the open List for the node with the cheapest F-Cost
	 * @param open: Open list
	 * @return cheapest Node
	 */
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



	/**
	 * Calculates the Manhattan Heuristic for the Node
	 * @param node
	 * @return distance
	 */
	public static int calculateCostH(Node node) {
		
		int mult = Settings.getHeuristicModifier();
		int x1 = node.getXCoord();
		int y1 = node.getYCoord();
		int x2 = Settings.getThymioEndField_X();
		int y2 = Settings.getThymioEndField_Y();
		int cost = Math.abs((x1 - x2) + (y1-y2));
		return mult*cost;
	}


	/**
	 * Calculates the Movement Cost to get to the Node 
	 * 
	 * Uses the direction Thymio is facing for calculations
	 * 
	 * @param node
	 * @param direction
	 * @return G-Cost
	 */
	private static int calculateCostG(Node node, String direction) {
		int orientation = currentNode.getOrientation();
		int cost = 0;
		int bottom = 180;
		int top = 0;
		int left = 270;
		int right = 90;
		switch (direction) {
		case BOTTOM:
			if((orientation > bottom || orientation < bottom)  && orientation != top){
				cost = COST_TURN+5;
			}else if(orientation == top){
				cost = COST_TURN+8;
			}else{
				cost =COST_TURN+2;
			}
			return cost;
		case TOP:
			if(orientation > top && orientation != bottom){
				cost = COST_TURN+5;
			}else if(orientation == bottom){
				cost = COST_TURN+8;
			}else{
				cost =COST_TURN+2;
			}
		return cost;
		case LEFT:
			if((orientation == top || orientation == bottom)){
				cost = COST_TURN+5;
			}else if(orientation == right){
				cost = COST_TURN+8;
			}else{
				cost = COST_TURN+2;
			}
		return cost;
		case RIGHT:
			if((orientation == top || orientation == bottom)){
				cost = COST_TURN+5;
			}else if(orientation == left){
				cost = COST_TURN+8;
			}else{
				cost =COST_TURN+2;
			}
			
		return cost;
		default:
			return cost;
		}
		
	}
}
