package map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import main.Helper;
import main.Settings;
import pathfinding.Node;
/**
 * <h1> Chessboard Class for the Thymio project</h1>
 * <h3> Course: Informationssysteme (SS 2016) Universitaet Regensburg</h3>
 * 
 * <div>Dozent: Prof. Dr. Bernd Ludwig</div>
 * 
 * 
 * Handles the Creation of the Chessboard 
 * Has some Getter for the Nodes
 * 
 * @version 1.0
 * @author Korbinian Kasberger: korbinian1.kasberger@stud.uni-regensburg.de
 */
public class Chessboard {
	
	private static final String TOP = Settings.strTop();
	private static final String BOTTOM = Settings.strBottom();
	private static final String LEFT = Settings.strLeft();
	private static final String RIGHT = Settings.strRight();

	private static List<Node> nodeList = new ArrayList<Node>();
	private static char[][] boardArray = new char[Settings.getBoardArrayWidth()][Settings.getBoardArrayHeight()];
	private static String[][] chessArray = boardAsChessArray();
	private static Chessboard board;
	private static List<Node> obstacleList = new ArrayList<Node>();
	
	
	
	/**
	 * Constructor
	 * 
	 * Initializes the creation of the individual Nodes and Obstacles
	 */
	public Chessboard() {
		
		createNodes();
		createObstaclesArray();
	}


	/**
	 * 
	 */
	private void createObstaclesArray() {
		char[][] obs = createArray();
		for (int i = 0; i < Settings.getBoardArrayHeight(); i++) {
			for (int j = 0; j < Settings.getBoardArrayWidth(); j++) {
				int id = Helper.calculateID(j, i);
				Node node = nodeList.get(id);
				if(obs[j][i]== '1'){
					int start_id = Helper.calculateID(Settings.getThymioStartField_X(), Settings.getThymioStartField_Y());
					int end_id = Helper.calculateID(Settings.getThymioEndField_X(), Settings.getThymioEndField_Y());
					if(		node.getId() != start_id &&
							node.getId() != end_id
						){
						node.setObstacle(true);
						node.setColor(Settings.getColorObstacle());
					}
					
				}
			}
		}	
		
	}
	
	/**
	 * Reads the CSV file and turns it into a 2D array
	 * @return returns the array into a method to remove the start and end node
	 */
	public static char[][] createArray() {
		int x = 0;
		List<String[]> csv = Settings.getCsv();
		for(String[] row : csv){
			
			for (int i = 0; i < row.length; i++) {
				String t = row[i];
				char[] arr = t.toCharArray();
				for (int j = 0; j < arr.length; j++) {
					
					boardArray[i][x] = arr[j];	
				}
			}
			x++;	
		}
		if(boardArray.length <1){
			return boardArray;
		}
		return freeImportantFields(boardArray);
	}


	
	
	/**
	 * Checks if the Start or End Field have been set as obstacle, if so clears them
	 * @param boardArray: uncleared board array
	 * @return cleared board array
	 */
	private static char[][] freeImportantFields(char[][] boardArray) {
		
		
		for(Node node : obstacleList){
			if(node == Settings.getStartNode()){
				node.setObstacle(false);
				obstacleList.remove(node);
				boardArray[node.getXCoord()][node.getYCoord()] = 0;
			}
			if(node == Settings.getEndNode()){
				node.setObstacle(false);
				obstacleList.remove(node);
				boardArray[node.getXCoord()][node.getYCoord()] = 0;
			}
		}
		
		return boardArray;
	}
	
	/**
	 * Returns the Chessboard
	 * @return chessboard
	 */
	public static Chessboard get_board(){
		return board;
	}
   
	
	
	/**
	 * Generates the Board as Array with Chess-like Coordinates (A1,A2....B6 etc.)
	 * @return 2D String Array
	 */
	private static String[][] boardAsChessArray() {
		chessArray = new String[Settings.getBoardArrayWidth()][Settings.getBoardArrayHeight()];
		char[] letters = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		for (int i = 0; i < chessArray[0].length; i++) {
			
			for (int j = 0; j < chessArray.length; j++) {
				String curr = Integer.toString(i);
				chessArray[j][i]=letters[j]+curr;
			}
		}
		return chessArray;
	}
	
	
	/**
	 * Takes a node and returns possible Neighbour nodes
	 * @param current: Node to check
	 * @return HashMap with Nodes and their position
	 */
	public HashMap<String,Node> getNeighbourNodes(Node current) {
		HashMap<String,Node> nodes = new HashMap<>();
		if(leftNeighbourNode(current)!=null){
			nodes.put(LEFT,leftNeighbourNode(current));
		}
		if(rightNeighbourNode(current) != null){
			nodes.put(RIGHT,rightNeighbourNode(current));
		}
		if(topNeighbourNode(current) != null){
			nodes.put(TOP,topNeighbourNode(current));
		}
		if(bottomNeighbourNode(current) != null){
			nodes.put(BOTTOM,bottomNeighbourNode(current));
		}
		return nodes;	
	}
	
	/**
	 * Takes a node and returns the Direction of possible Neighbours (Reverse of getNeighbourNodes())
	 * @param current: Node to check
	 * @return HashMap with the direction and the Node
	 */
	public HashMap<Node,String> getNeighbourDirection(Node current) {
		HashMap<Node,String> nodes = new HashMap<>();
		if(leftNeighbourNode(current)!=null){
			nodes.put(leftNeighbourNode(current),LEFT);
		}
		if(rightNeighbourNode(current) != null){
			nodes.put(rightNeighbourNode(current),RIGHT);
		}
		if(topNeighbourNode(current) != null){
			nodes.put(topNeighbourNode(current),TOP);
		}
		if(bottomNeighbourNode(current) != null){
			nodes.put(bottomNeighbourNode(current), BOTTOM);
		}
		return nodes;	
	}
	
	
	/**
	 * Takes a node and checks if it has a left neighbour
	 * @param current:  Node to check
	 * @return If a node exists, returns the node
	 */
	private Node leftNeighbourNode(Node current){
		if(current.getYCoord() == 0){
			return null;
		}
		int currentID = current.getId();
		int neighbourID = currentID-1;
		return getNodeByID(neighbourID);
	}

	/**
	 * Takes a node and checks if it has a right neighbour
	 * @param current:  Node to check
	 * @return If a node exists, returns the node
	 */
	private Node rightNeighbourNode(Node current){
		if(current.getYCoord() >= Settings.getBoardArrayWidth()-1){
			return null;
		}
		int currentID = current.getId();
		int neighbourID = currentID+1;
		return getNodeByID(neighbourID);
	}
	
	/**
	 * Takes a node and checks if it has a top neighbour
	 * @param current:  Node to check
	 * @return If a node exists, returns the node
	 */
	private Node topNeighbourNode(Node current){
		if( current.getXCoord() == 0){
			return null;
		}
		int currentID = current.getId();
		int neighbourID = currentID-Settings.getBoardArrayWidth();	
		return getNodeByID(neighbourID);
	}
	
	/**
	 * Takes a node and checks if it has a bottom neighbour
	 * @param current:  Node to check
	 * @return If a node exists, returns the node
	 */
	private Node bottomNeighbourNode(Node current){
		if(current.getXCoord() >= Settings.getBoardArrayHeight()-1){
			return null;
		}
		int currentID = current.getId();
		int neighbourID = currentID+Settings.getBoardArrayWidth();
		return getNodeByID(neighbourID);
	}
	
	
	/**
	 * Takes a node and returns only the IDs of the neighbour nodes
	 * @param current: Node to check
	 * @return List<Integer> ids
	 */
	public List<Integer> getNeighbourIDs(Node current) {
		List<Integer> ids = new ArrayList<Integer>();
		HashMap<String, Node> neighbours = getNeighbourNodes(current);
		for(Node node : neighbours.values()){
			ids.add(node.getId());
		}
		return ids;
	}
	
	/**
	 * Creates a Node Object representing each Tile of the Chessboard
	 */
	private void createNodes(){
		for (int i = 0; i < Settings.getBoardArrayHeight(); i++) {
			for (int j = 0; j < Settings.getBoardArrayWidth(); j++) {
				int id = Helper.calculateID(j, i);
				boolean white = (i % 2 == 0) == (j % 2 == 0);
				Node node = new Node(id,i,j,chessArray[j][i],0);
	       		if(white && node.getColor()==null){
	       			node.setColor(Settings.getColorChessA());
	       		}else if(node.getColor()==null){
	       			node.setColor(Settings.getColorChessB());
	       		}		
				nodeList.add(node);			
			}
		}	
	
	}
	
	/**
	 * Returns the Nodes 
	 * @return List<Node>
	 */
	public List<Node> getNodes(){
		return nodeList;
	}



	/**
	 * Takes an ID and returns the Node that belongs to it
	 * @param id
	 * @return Node
	 */
	public Node getNodeByID(int id){
          List<Node> nodes = getNodes();
         for(Node node : nodes){
              if(node.getId()==id){
                  return node;
             }
          }
          return null;
      }	
}
