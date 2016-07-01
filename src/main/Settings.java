package main;
import java.io.IOException;
import java.util.List;

import de.ur.mi.graphics.Color;
import map.CSVData;
import map.Chessboard;
import map.Obstacles;
import pathfinding.Node;


/*
 * Settings Class with all the Getter and Setter. Adjust for custom setting
 */
public class Settings  {
	
	// General Settings
	private static final boolean OVERWRITE_MAP = true;
	private static final int ANIMATION_DELAY = 50;
	private static final boolean TILE_BORDER = true;
	private static final boolean OBSTACLES = true;
	private static final boolean SHOW_LABELS = true;

	//A* Settings
	private static final int H_MODIFIER = 2; // Best 2
	private static final int TURN_COST = 1; // Best 1
	
	//Thymio Settings
	private static final String THYMIO_IMG = "images/Robot_idle.gif"; 	//	Awesome Thymio Image
	private static final String THYMIO_ROTATION = "North";
	private static final int MAX_SPEED = 500;
	private static final short AHEAD_SPEED = 100;
	private static final short ROTATION_SPEED = 50;
	
	// Colors
	private static final Color COLOR_CHESS_A = Color.DARK_GRAY;
	private static final Color COLOR_CHESS_B = Color.LIGHT_GRAY;
	private static final Color COLOR_OBSTACLE = Color.RED;
	private static final Color COLOR_MOVEMENT = Color.YELLOW;
	private static final Color COLOR_BG = Color.WHITE;
	private static final Color COLOR_START = Color.GREEN;
	private static final Color COLOR_END = Color.BLUE;
	private static final Color COLOR_OPEN_NODE = Color.GREEN;
	private static final Color COLOR_CLOSED_NODE = Color.MAGENTA;
	private static final Color COLOR_BORDER = Color.WHITE;
	
	
	// Font Sizes
	private static final int FONT_SIZE_STARTPOINT = 22;
	private static final int FONT_SIZE_ENDPOINT = 22;
	private static final int FONT_SIZE_LABEL = 10;
	private static final int FONT_SIZE_CHESS = 15;
	
	//	Startfield (currently possible: 0-19)
	private static final int THYMIO_STARTFIELD_X = 1; 
	private static final int THYMIO_STARTFIELD_Y = 1;;
	
	//	Endfield (currently possible: 0-19)
	private static final int THYMIO_ENDFIELD_X = 16;
	private static final int THYMIO_ENDFIELD_Y = 6;
	
	/*
	 * Probability is calculated by picking a random number between 0 and RANDOM_OBSTACLE_PROBABILTY_RANGE.
	 * If it's greater than RANDOM_OBSTACLE COUNT, it is an obstacle.
	 */
	private static final int RANDOM_OBSTACLE_COUNT = 2;
	private static final int RANDOM_OBSTACLE_PROBABILITY_RANGE = 20;
	
	
/*
 * 	No changes below this line.
 */
	private static final int CANVAS_HEIGHT = 400;
	private static final int FIELD_HEIGHT = getFieldWidth();
	private static final int CANVAS_WIDTH  = 1000;
	private static final int FIELD_WIDTH = 50;
	private static final String EMPTY_MAP_SRC = "empty_map.csv";
	private static final String OBSTACLE_MAP_SRC = "obstacle_map.csv";
	

	private static CSVData csv = getReader();
	private static List<String[]> csv_list = csv.getEntries();
	private static Chessboard board = new Chessboard();

	
	

	
	
	
	
	
	
	
	private static CSVData getReader(){
		CSVData test = null;
		try {
			test = new CSVData();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return test;
	}
	


	public static int getStartX(){
		int x = getThymioStartField_X(); 
		
		if(x == 0){
		
    	}else{
    	 x *= FIELD_HEIGHT;
    	}
    	
		return x ;
	}
	public static int getStartY(){
		int y = getThymioStartField_Y();
		if(y == 0){
    		
    	}else{
    		y *= FIELD_HEIGHT;
    	}
		return y;
	}
	public static int getEndX(){
		int x = getThymioEndField_X(); 
		
		if(x == 0){
		
    	}else{
    	 x *= FIELD_HEIGHT;
    	}
    	
	
		return x;
	}
	public static int getEndY(){
	int y = getThymioEndField_Y(); 
		
		if(y == 0){
		
    	}else{
    	 y *= FIELD_HEIGHT;
    	}
		return y;
	}
	
	public static int getThymioStartField_X() {
		return THYMIO_STARTFIELD_X;
	}

	public static String getThymioImg() {
		return THYMIO_IMG;
	}
	public static int getThymioStartField_Y() {
		return THYMIO_STARTFIELD_Y;
	}

	public static int getThymioEndField_X() {
		return THYMIO_ENDFIELD_X;
	}

	public static int getThymioEndField_Y() {
		return THYMIO_ENDFIELD_Y;
	}
	
	public static int getObstacleProbability() {
		if(OBSTACLES){
			return RANDOM_OBSTACLE_COUNT;
		}
		return 0;
	}
	
	public static int getObstacleProbabilityRange() {
		return RANDOM_OBSTACLE_PROBABILITY_RANGE;
	}
	
	public static int getCanvasWidth() {
		return CANVAS_WIDTH;
	}
	public static int getFieldWidth() {
		return FIELD_WIDTH;
	}
	public static int getCanvasHeight() {
		return CANVAS_HEIGHT;
	}
	public static int getFieldHeight() {
		return FIELD_HEIGHT;
	}
	public static int getDelay() {
		return ANIMATION_DELAY;
	}

	public static Chessboard getBoard() {
		return board;
	}

	public static Color getColorChessA() {
		return COLOR_CHESS_A;
	}
	
	public static Color getColorChessB() {
		return COLOR_CHESS_B;
	}
	
	public static Color getColorObstacle() {
		return COLOR_OBSTACLE;
	}
	
	public static Color getStartFieldColor() {
		return COLOR_START;
	}
	
	public static Color getEndFieldColor() {
		return COLOR_END;
	}
	
	public static int getFontSizeEndpoint() {
		return FONT_SIZE_ENDPOINT;
	}
	public static int getFontSizeStartpoint() {
		return FONT_SIZE_STARTPOINT;
	}
	public static String getThymioRotation() {
		return THYMIO_ROTATION;
	}
	public static int getBoardArrayWidth() {
		return CANVAS_WIDTH/FIELD_HEIGHT;
	}
	public static int getBoardArrayHeight() {
		return CANVAS_HEIGHT/FIELD_HEIGHT;
	}
	public static String getEmptySrc() {
		return EMPTY_MAP_SRC;
	}
	public static String getObstacleSrc() {
		return OBSTACLE_MAP_SRC;
	}
	public static List<String[]> getCsv() {
		return csv_list;
	}

	public static Node getStartNode() {
		int id = Helper.calculateID(THYMIO_STARTFIELD_X,THYMIO_STARTFIELD_Y);
		Node node = getBoard().getNodes().get(id);
		node.setOrientation(Controller.thymio.getOrientation());
		return node;
	}

	public static Node getEndNode() {
		int id = Helper.calculateID(THYMIO_ENDFIELD_X,THYMIO_ENDFIELD_Y);
		return getBoard().getNodes().get(id);
	}

	public static List<Node> getBoardNodes() {
		return getBoard().getNodes();
	}
	public static char[][] getObstaclesArray() {
		return Obstacles.getObstaclesArray();
	}

	public static Color getColorMovement() {
		return COLOR_MOVEMENT;
	}



	public static Color getColorBackground() {
		return COLOR_BG;
	}



	public static boolean isOverwrite() {
		return OVERWRITE_MAP;
	}



	public static int getHeuristicModifier() {
		return H_MODIFIER;
	}



	public static int getTurnCost() {
		return TURN_COST;
	}



	public static int getSpeedMax() {
		return MAX_SPEED;
	}



	public static short getSpeedAhead() {
		return AHEAD_SPEED;
	}



	public static short getSpeedRotation() {
		return ROTATION_SPEED;
	}



	public static boolean getBorder() {
		return TILE_BORDER;
	}



	public static Color getBorderColor() {
		return COLOR_BORDER;
	}



	public static int getFontSizeLabel() {
		return FONT_SIZE_LABEL;
	}



	public static int getFontSizeChess() {
		return FONT_SIZE_CHESS;
	}



	public static boolean showLabels() {
		return SHOW_LABELS;
	}



	public static Color getColorOpenNode() {
		return COLOR_OPEN_NODE;
	}



	public static Color getColorClosedNode() {
		return COLOR_CLOSED_NODE;
	}



	
}