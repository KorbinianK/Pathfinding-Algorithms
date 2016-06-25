package map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Rect;
import main.Helper;
import main.Settings;
import pathfinding.Node;

public class Chessboard extends Rect{
	
	// Fixed, don't change, adjustments in Settings Class
	private static int CANVAS_WIDTH = Settings.getCanvasWidth();
	private static int CANVAS_HEIGHT = Settings.getCanvasHeight();
	private static int FIELD_WIDTH = Settings.getFieldWidth();
	private static int FIELD_HEIGHT = Settings.getFieldHeight();
	protected List<Node> nodeList = new ArrayList<Node>();
	private String[][] chessArray = boardAsStringArray();
	private static Chessboard board;
	
	
	public Chessboard(int x, int y, int width, int height, Color color) {
		super(x,y,width,height,color);
		createNodes();
		createObstacles();
	}
	
	
	private void createObstacles() {
		char[][] obs = Settings.getObstaclesArray();
		for (int i = 0; i < Settings.getBoardArrayHeight(); i++) {
			for (int j = 0; j < Settings.getBoardArrayWidth(); j++) {
				int id = Helper.calculateID(j, i);
				Node node = nodeList.get(id);
				
				if(obs[j][i]== '1'){

					System.out.println(node.getChessCoord());

					node.setObstacle(true);
				}
			}
		}	
		
	}


	// redraws the Chessboard entirely
	public void redraw(){
		super.draw();
		 for (int i = 0; i < CANVAS_WIDTH; i+=FIELD_WIDTH*2) {
		       	for (int j = 0; j < CANVAS_HEIGHT; j+=FIELD_HEIGHT*2) {     	
	       			Rect rect2 = new Rect(i, j, FIELD_HEIGHT, FIELD_WIDTH,Settings.getColorChessB()); 
		       		rect2.draw();
					}
			}
		    for (int i = FIELD_HEIGHT; i < CANVAS_WIDTH; i+=FIELD_HEIGHT*2) {
				for (int j = FIELD_HEIGHT; j <CANVAS_HEIGHT; j+=FIELD_HEIGHT*2) {
					Rect rect3 = new Rect(i, j, FIELD_HEIGHT, FIELD_WIDTH,Settings.getColorChessB());
					rect3.draw();
				}
		    }
	}
	
	// Creates the basic Chessboard
	public static Chessboard get_board(){
		return board;
	}
   
//	Returns the Board as Array with Chess-like Coordinates (A1,A2....B6 etc.)
	public String[][] boardAsStringArray(){
		chessArray = new String[Settings.getBoardArrayWidth()][Settings.getBoardArrayHeight()];
		return fillCoordinatesString(chessArray);
	}
	
// Returns the Boardas Array with Coordinates (0,0; 0,1; ... 5,8 etc)	
	public String[][] boardAsArray(){
		String[][] board_array = new String[Settings.getBoardArrayWidth()][Settings.getBoardArrayHeight()];
		return fillCoordinates(board_array);
	}
	


	
	// Creates an Array with Coordinates for each position
	private String[][] fillCoordinates(String[][] board) {
		
		for (int row = 0; row < board.length; row++) {
		    String[] sub = board[row];
		    for (int col = 0; col < sub.length; col++) {
		    	sub[col] = row+","+col;	
		    }
		}
		return board;
	}
	
	// Creates an Array with Chess-like Coordinates for each position
	private String[][] fillCoordinatesString(String[][] board) {
		
		char[] letters = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		for (int i = 0; i < board[0].length; i++) {
			
			for (int j = 0; j < board.length; j++) {
				String curr = Integer.toString(i);
				board[j][i]=letters[j]+curr;
			}
		}
		return board;
	}
	
	
	public HashMap<String,Node> getNeighbourNodes(Node current) {
		
		HashMap<String,Node> nodes = new HashMap<>();
		if(leftNeighbourNode(current)!=null){
			nodes.put("left",leftNeighbourNode(current));
		}
		if(rightNeighbourNode(current) != null){
			nodes.put("right",rightNeighbourNode(current));
		}
		if(topNeighbourNode(current) != null){
			nodes.put("top",topNeighbourNode(current));
		}
		if(bottomNeighbourNode(current) != null){
			nodes.put( "bottom",bottomNeighbourNode(current));
		}
		return nodes;
		
	}
	
	
	private Node leftNeighbourNode(Node current){
		if(current.getYCoord() == 0){
			return null;
		}
		int currentID = current.getId();
		int neighbourID = currentID-1;
		return getNeighbourNode(neighbourID);
	}


	private Node rightNeighbourNode(Node current){
		if(current.getYCoord() >= Settings.getBoardArrayWidth()-1){
			return null;
		}
		int currentID = current.getId();
		int neighbourID = currentID+1;
		return getNeighbourNode(neighbourID);
	}
	
	private Node topNeighbourNode(Node current){
		if( current.getXCoord() == 0){
			return null;
		}
		int currentID = current.getId();
		int neighbourID = currentID-Settings.getBoardArrayWidth();
		return getNeighbourNode(neighbourID);
	}
	
	private Node bottomNeighbourNode(Node current){
		if(current.getXCoord() > Settings.getBoardArrayHeight()){
			return null;
		}
		int currentID = current.getId();
		int neighbourID = currentID+Settings.getBoardArrayWidth();
		return getNeighbourNode(neighbourID);
	}
	
	
	private Node getNeighbourNode(int neighbourID) {
		Node neighbour;
		if (neighbourID < Settings.getBoard().getNodes().size() && neighbourID >= 0) {
			neighbour = Settings.getBoard().getNodes().get(neighbourID);
		}else{
			neighbour = null;
		}
		
		return neighbour;
	}
	
	

	
	public static Node posAsNode(int x, int y){
		List<Node> nodes = Settings.getBoard().getNodes();
		int id = 0;
		int[][] arr = new int[Settings.getBoardArrayHeight()][Settings.getBoardArrayWidth()];
		
		for (int i = 0; i < Settings.getBoardArrayHeight();i++) {
			for (int j = 0; j < Settings.getBoardArrayWidth(); j++) {
				arr[i][j]= id;
				id++;
			}
		}
		int index = arr[y][x];
		Node node = nodes.get(index);
		
		return node;
	}
	

//	Calculates the distance between two fields, ignoring obstacles (Heuristic for A*)
	public int calculateAirDistance(int start_x, int start_y, int end_x, int end_y){
		int cost = 0;
		if(start_x < end_x){
			while(start_x < end_x){
				cost++;
				start_x++;
			}
		}else if(start_x > end_x){
			while(start_x > end_x){
				cost++;
				start_x--;
			}
		}
		
		if(start_y  < end_y){
			while(start_y < end_y){
				cost++;
				start_y++;
			}
		}else if(start_y > end_y){
			while(start_y > end_y){
				cost++;
				start_y--;
			}
		}
		return cost;
	}

	
//	Creates a node representing each field
	public void createNodes(){
		
		for (int i = 0; i < Settings.getBoardArrayHeight(); i++) {
			for (int j = 0; j < Settings.getBoardArrayWidth(); j++) {
				
				
				int id = Helper.calculateID(j, i);
				Node node = new Node(id,i,j,chessArray[j][i],0);
				nodeList.add(node);	
				
			}
		}	
	
	}
	
	public List<Node> getNodes(){
		return nodeList;
	}
	
}
