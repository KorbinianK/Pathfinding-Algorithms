import java.util.ArrayList;
import java.util.List;

import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Rect;

public class Chessboard extends Rect{
	
	// Fixed, don't change, adjustments in Settings Class
	private static int CANVAS_WIDTH = Settings.getCanvasWidth();
	private static int CANVAS_HEIGHT = Settings.getCanvasHeight();
	private static int FIELD_WIDTH = Settings.getFieldWidth();
	private static int FIELD_HEIGHT = Settings.getFieldHeight();
	protected List<Node> nodeList = new ArrayList<Node>();
	private static Chessboard board;
	
	
	public Chessboard(int x, int y, int width, int height, Color color) {
		super(x,y,width,height,color);
		
	}
	
	
	// redraws the Chessboard entirely
	public void redraw(){
		super.draw();
		
		 for (int i = 0; i <= CANVAS_WIDTH; i+=FIELD_WIDTH*2) {
		       	for (int j = 0; j <= CANVAS_HEIGHT; j+=FIELD_HEIGHT*2) {     	
	       			Rect rect2 = new Rect(i, j, FIELD_HEIGHT, FIELD_WIDTH,Settings.getColorChessB()); 
		       		rect2.draw();
					}
			}
		    for (int i = FIELD_HEIGHT; i <= CANVAS_WIDTH; i+=FIELD_HEIGHT*2) {
				for (int j = FIELD_HEIGHT; j <= CANVAS_HEIGHT; j+=FIELD_HEIGHT*2) {
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
		String[][] board_array = new String[Settings.getBoardArraySize()][Settings.getBoardArraySize()];
		return fillCoordinatesString(board_array);
	}
	
// Returns the Boardas Array with Coordinates (0,0; 0,1; ... 5,8 etc)	
	public String[][] boardAsArray(){
		String[][] board_array = new String[Settings.getBoardArraySize()][Settings.getBoardArraySize()];
		return fillCoordinates(board_array);
	}
	

//	gets the field as Chess-like String 
	public String getCoordString(int x, int y){
		String array[][] = boardAsStringArray();
		String coord = array[x][y];
		return coord;
	}
	
//	gets the field as coordinates
	public String getCoord(int x, int y){
		String array[][] = boardAsArray();
		String coord = array[x][y];
		return coord;
	}

	
	// Creates an Array with Coordinates for each position
	private String[][] fillCoordinates(String[][] board) {
		
		for (int row = 0; row < board.length; row++) {
		    String[] sub = board[row];
		    for (int col = 0; col < sub.length; col++) {
		    	sub[col] = row+","+col;	
		    }
		}
//		System.out.println(Arrays.deepToString(board));
		return board;
	}
	
	// Creates an Array with Chess-like Coordinates for each position
	private String[][] fillCoordinatesString(String[][] board) {
		
		char[] letters = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board.length; col++) {
				String curr = Integer.toString(row);
				board[col][row] = letters[col]+curr;	
			}
		}
		return board;
	}
	
//	Methods to return x-Neighbour of the current field
	public String leftNeighbour(int x, int y){
		int check_x = x-1;
		int check_y = y;
		String[][] board = Settings.getBoard().boardAsArray();
		String neighbour = board[check_x][check_y];
		return neighbour;
	}
	public String rightNeighbour(int x, int y){
		int check_x = x+1;
		int check_y = y;
		String[][] board = Settings.getBoard().boardAsArray();
		String neighbour = board[check_x][check_y];
		return neighbour;
	}
	public String topNeighbour(int x, int y){
		int check_x = x;
		int check_y = y-1;
		String[][] board = Settings.getBoard().boardAsArray();
		String neighbour = board[check_x][check_y];
		return neighbour;
	}
	public String bottomNeighbour(int x, int y){
		int check_x = x;
		int check_y = y+1;
		String[][] board = Settings.getBoard().boardAsArray();
		String neighbour = board[check_x][check_y];
		return neighbour;
	}
	
	public Node asNode(String neighbour, int currentX, int currentY){
		List<Node> nodes = Settings.getBoard().getNodes();
		int x = currentX;
		int y = currentY;
		
		
		switch (neighbour) {
		case "bottom":
			
			y = currentY+1;
			break;
		case "top":
			
			y = currentY-1;
			break;
		case "left":
			x = currentX-1;
			
			break;
		case "right":
			x = currentX+1;
			
			break;
		}
		int id = 0;
		for (int i = 0; i < x; i++) {
			id++;
		}
		int mult = (int) y*Settings.getBoardArraySize();
		int index = id+mult;
		Node node = nodes.get(index);
		return node;
		
	}
	
	
//	Methods to check if a field has neighbours surrounding it
	public boolean fieldHasLeftNeighbour(int x, int y){
		int check_x = x-1;
		int check_y = y;
		if(check_x >= 0){
			return checkField(check_x,check_y);
		}
		return false;
	}
	public boolean fieldHasRightNeighbour(int x, int y){
		int check_x = x+1;
		int check_y = y;
		if(check_x < Settings.getBoardArraySize()){
			return checkField(check_x,check_y);
		}
		return false;
	}
	public boolean fieldHasBottomNeighbour(int x, int y){
		int check_x = x;
		int check_y = y+1;
		if(check_y < Settings.getBoardArraySize()){
			return checkField(check_x,check_y);
		}
		return false;
	}
	
	public boolean fieldHasTopNeighbour(int x, int y){
		int check_x = x;
		int check_y = y-1;
		
		if(check_y >= 0){
			return checkField(check_x,check_y);
		}
		return false;
	}


//	checks if the field exists and has no obstacle
	private boolean checkField(int x, int y) {
		if(x <= Settings.getBoardArraySize()&& x >= 0){
			if(y <= Settings.getBoardArraySize()&& y >= 0){
				if(!Settings.getObstacles().isObstacle(x, y)){
					return true;
				}	
			}
			return false;
		}else{
			return false;
		}
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
		for (int i = 0; i < Settings.getBoardArraySize(); i++) {
			for (int j = 0; j < Settings.getBoardArraySize(); j++) {
				String[][] board = Settings.getBoard().boardAsStringArray();
				
				Node node = new Node(nodeList.size(),i,j,board[j][i]);
				nodeList.add(node);		
			}
		}	
	}
	
	public List<Node> getNodes(){
		return nodeList;
	}
	
}
