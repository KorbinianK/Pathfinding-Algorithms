import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Rect;

public class Chessboard extends Rect{
	
	// Fixed, don't change, adjustments in Settings Class
	private static int CANVAS_WIDTH = Settings.getCanvasWidth();
	private static int CANVAS_HEIGHT = Settings.getCanvasHeight();
	private static int FIELD_WIDTH = Settings.getFieldWidth();
	private static int FIELD_HEIGHT = Settings.getFieldHeight();

	public Chessboard(int x, int y, int width, int height, Color color) {
		super(x,y,width,height,color);
	}
	
	
	// redraws the Chessboard entirely
	public void redraw(){
		super.draw();
		
		 for (int i = 0; i <= CANVAS_WIDTH; i+=FIELD_WIDTH*2) {
		       	for (int j = 0; j <= CANVAS_HEIGHT; j+=FIELD_HEIGHT*2) {     	
	       			Rect rect2 = new Rect(i, j, FIELD_HEIGHT, FIELD_WIDTH,Color.WHITE); 
		       		rect2.draw();
					}
			}
		    for (int i = FIELD_HEIGHT; i <= CANVAS_WIDTH; i+=FIELD_HEIGHT*2) {
				for (int j = FIELD_HEIGHT; j <= CANVAS_HEIGHT; j+=FIELD_HEIGHT*2) {
					Rect rect3 = new Rect(i, j, FIELD_HEIGHT, FIELD_WIDTH,Color.WHITE);
					rect3.draw();
				}
		    }
	}
	
	// Creates the basic Chessboard
	public static Chessboard get_board(){
		Chessboard board = new Chessboard(0, 0, CANVAS_HEIGHT+FIELD_HEIGHT, CANVAS_WIDTH+FIELD_HEIGHT, Color.GRAY);
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
	

	
	public String getCoordString(int x, int y){
		String array[][] = boardAsStringArray();
		String coord = array[x][y];
		return coord;
	}
	
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
		    	sub[col] = col+","+row;	
		    }
		}
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
	
	public boolean fieldHasLeftNeighbour(int x, int y){
		int check_x = x-1;
		int check_y = y;
		return checkField(check_x,check_y);
	}
	
	public boolean fieldHasRightNeighbour(int x, int y){
		int check_x = x+1;
		int check_y = y;
		return checkField(check_x,check_y);
	}
	public boolean fieldHasBottomNeighbour(int x, int y){
		int check_x = x;
		int check_y = y+1;
		return checkField(check_x,check_y);
	}
	
	public boolean fieldHasTopNeighbour(int x, int y){
		int check_x = x;
		int check_y = y-1;
		return checkField(check_x,check_y);
	}


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
}
