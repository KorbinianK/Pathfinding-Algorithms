package main;
import java.io.IOException;
import java.util.List;

import de.ur.mi.graphics.Color;
import iw.ur.thymio.Thymio.Thymio;
import map.CSVData;
import map.Chessboard;
import pathfinding.Node;


/**
 * <h1>Settings Class for the Thymio project</h1>
 * <h3> Course: Informationssysteme (SS 2016) Universitaet Regensburg</h3>
 * 
 * <div>Dozent: Prof. Dr. Bernd Ludwig</div>
 * 
 * 
 * Collection of all the Constants and variables used throughout the project
 * 
 * A lot of the Settings can be adjusted without causing any problems
 * 
 * @version 1.0
 * @author Korbinian Kasberger: korbinian1.kasberger@stud.uni-regensburg.de
 */
public class Settings  {
	
	// General Settings
	private static final boolean USE_THYMIO =true;

	private static final boolean OVERWRITE_MAP = false;
	private static final int ANIMATION_DELAY = 50;
	private static final boolean DELAY_ANIMATIONS = false;
	private static final boolean TILE_BORDER = true;
	private static final boolean SHOW_OBSTACLES = true;
	private static boolean SHOW_LABELS = false;
	private static final boolean SHOW_OPEN_LIST = true;
	private static final boolean SHOW_CLOSED_LIST = true;
	
	//A* Settings
	private static final int H_MODIFIER = 2; // Best 2
	private static final int TURN_COST = 1; // Best 1
	
	//Thymio Settings
	private static final String THYMIO_IMG = "images/thymio.gif"; 	//	Awesome Thymio Image
	private static final String THYMIO_START_ROTATION = "north";
	// Real Thymio
	private static final int MAX_SPEED = 500;
	private static final short AHEAD_SPEED = 1000;
	private static final short ROTATION_SPEED = 50;
	private static final long MOVE_BIAS = 3000;
	
	// Colors
	private static final Color COLOR_CHESS_A = Color.LIGHT_GRAY; // Black
	private static final Color COLOR_CHESS_B = Color.DARK_GRAY; // White
	private static final Color COLOR_OBSTACLE = Color.RED;
	private static final Color COLOR_MOVEMENT = Color.YELLOW;
	private static final Color COLOR_BG = Color.WHITE;
	private static final Color COLOR_START = Color.CYAN;
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
	private static final int THYMIO_ENDFIELD_Y = 5;
	
	/*
	 * Probability is calculated by picking a random number between 0 and RANDOM_OBSTACLE_PROBABILTY_RANGE.
	 * If it's greater than RANDOM_OBSTACLE COUNT, it is an obstacle.
	 */
	private static final int RANDOM_OBSTACLE_COUNT = 3;
	private static final int RANDOM_OBSTACLE_PROBABILITY_RANGE = 20;
	

 // No changes below this line.
	private static final int CANVAS_HEIGHT = 400; // 400 / 900
	private static final int FIELD_HEIGHT = getFieldWidth();
	private static final int CANVAS_WIDTH  = 1000; // 1000 / 1170
	private static final int FIELD_WIDTH = 50; //50 / 30

	private static final String OBSTACLE_MAP_SRC = "obstacle_map.csv"; // maze.csv
	private static final String TOP = "north";
	private static final String BOTTOM = "south";
	private static final String LEFT = "west";
	private static final String RIGHT = "east";


	private static CSVData csv = getReader();
	private static List<String[]> csv_list = csv.getEntries();
	private static Chessboard board = new Chessboard();
	private static ThymioController tc = new ThymioController();
	private static Thymio t = tc.getThymio();

	
	
	/**
	 * Initalizes the CSV Reader
	 * 
	 * @return CSV Reader
	 */
	private static CSVData getReader(){
		CSVData csv = null;
		try {
			csv = new CSVData();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return csv;
	}
	

/**
 * Get the X-Coordinate for the Start Field
 * @return x-coordinate
 */
	public static int getStartXCoordinate(){
		int x = getThymioStartField_X(); 
		
		if(x == 0){
		
    	}else{
    	 x *= FIELD_HEIGHT;
    	}
    	
		return x ;
	}
	
	/**
	 * Get the Y-Coordinate for the Start Field
	 * @return y-coordinate
	 */
	public static int getStartYCoordinate(){
		int y = getThymioStartField_Y();
		if(y == 0){
    		
    	}else{
    		y *= FIELD_HEIGHT;
    	}
		return y;
	}
	
	/**
	 * Get the X-Coordinate for the End Field
	 * @return x-coordinate
	 */
	public static int getEndXCoordinate(){
		int x = getThymioEndField_X(); 
		
		if(x == 0){
		
    	}else{
    	 x *= FIELD_HEIGHT;
    	}

		return x;
	}
	
	/**
	 * Get the Y-Coordinate for the End Field
	 * @return y-coordinate
	 */
	public static int getEndYCoordinate(){
	int y = getThymioEndField_Y(); 
		
		if(y == 0){
		
    	}else{
    	 y *= FIELD_HEIGHT;
    	}
		return y;
	}
	
	/**
	 * Returns the Row number of the Start Field
	 * @return row (x axis)
	 */
	public static int getThymioStartField_X() {
		return THYMIO_STARTFIELD_X;
	}

	/**
	 * 
	 * @return the source used for the Thymio visualization
	 */
	public static String getThymioImg() {
		return THYMIO_IMG;
	}
	
	/**
	 * Returns the column number of the End Field
	 * @return column (y axis)
	 */
	public static int getThymioStartField_Y() {
		return THYMIO_STARTFIELD_Y;
	}

	/**
	 * Returns the Row number of the End Field
	 * @return row (x axis)
	 */
	public static int getThymioEndField_X() {
		return THYMIO_ENDFIELD_X;
	}
	
	/**
	 * Returns the column number of the Start Field
	 * @return column (y axis)
	 */
	public static int getThymioEndField_Y() {
		return THYMIO_ENDFIELD_Y;
	}
	
	/**
	 * Returns the Probability of which obstacles should be created 
	 * @return probability
	 */
	public static int getObstacleProbability() {
		if(SHOW_OBSTACLES){
			return RANDOM_OBSTACLE_COUNT;
		}
		return 0;
	}
	
	/**
	 * Returns the ranges used to calculate random obstacles
	 * @return probability range
	 */
	public static int getObstacleProbabilityRange() {
		return RANDOM_OBSTACLE_PROBABILITY_RANGE;
	}
	
	
	/**
	 * The Canvas Width
	 * @return canvas width in pixel
	 */
	public static int getCanvasWidth() {
		return CANVAS_WIDTH;
	}
	
	/**
	 * The Field Width
	 * @return field width in pixel
	 */
	public static int getFieldWidth() {
		return FIELD_WIDTH;
	}
	
	/**
	 * The Canvas Height
	 * @return canvas height in pixel
	 */
	public static int getCanvasHeight() {
		return CANVAS_HEIGHT;
	}
	
	/**
	 * The Field Width
	 * @return field width in pixel
	 */
	public static int getFieldHeight() {
		return FIELD_HEIGHT;
	}
	
	/**
	 * Returns the delay used to slow down the drawing
	 * @return delay in ms
	 */
	public static int getDelay() {
		return ANIMATION_DELAY;
	}

	/**
	 * 
	 * @return
	 */
	public static Chessboard getBoard() {
		return board;
	}
	
	/**
	 * 
	 * @return
	 */
	public static Color getColorChessA() {
		return COLOR_CHESS_A;
	}

	/**
	 * 
	 * @return
	 */
	public static Color getColorChessB() {
		return COLOR_CHESS_B;
	}

	/**
	 * 
	 * @return
	 */
	public static Color getColorObstacle() {
		return COLOR_OBSTACLE;
	}

	/**
	 * 
	 * @return
	 */
	public static Color getStartFieldColor() {
		return COLOR_START;
	}

	/**
	 * 
	 * @return
	 */
	public static Color getEndFieldColor() {
		return COLOR_END;
	}

	/**
	 * 
	 * @return
	 */
	public static int getFontSizeEndpoint() {
		return FONT_SIZE_ENDPOINT;
	}
	
	/**
	 * 
	 * @return
	 */
	public static int getFontSizeStartpoint() {
		return FONT_SIZE_STARTPOINT;
	}
	
	/**
	 * 
	 * @return
	 */
	public static String getThymioStartRotation() {
		return THYMIO_START_ROTATION;
	}

	/**
	 * 
	 * @return
	 */
	public static int getBoardArrayWidth() {
		return CANVAS_WIDTH/FIELD_HEIGHT;
	}

	/**
	 * 
	 * @return
	 */
	public static int getBoardArrayHeight() {
		return CANVAS_HEIGHT/FIELD_HEIGHT;
	}



	/**
	 * 
	 * @return
	 */
	public static String getObstacleSrc() {
		return OBSTACLE_MAP_SRC;
	}

	/**
	 * 
	 * @return
	 */
	public static List<String[]> getCsv() {
		return csv_list;
	}


	/**
	 * 
	 * @return
	 */
	public static Node getStartNode() {
		int id = Helper.calculateID(THYMIO_STARTFIELD_X,THYMIO_STARTFIELD_Y);
		Node node = getBoard().getNodes().get(id);
		node.setOrientationByString(getThymioStartRotation());
		return node;
	}


	/**
	 * 
	 * @return
	 */
	public static Node getEndNode() {
		int id = Helper.calculateID(THYMIO_ENDFIELD_X,THYMIO_ENDFIELD_Y);
		return getBoard().getNodes().get(id);
	}

	public static List<Node> getBoardNodes() {
		return getBoard().getNodes();
	}


	/**
	 * 
	 * @return
	 */
	public static Color getColorMovement() {
		return COLOR_MOVEMENT;
	}


	/**
	 * 
	 * @return
	 */
	public static Color getColorBackground() {
		return COLOR_BG;
	}


	/**
	 * 
	 * @return
	 */
	public static boolean isOverwrite() {
		return OVERWRITE_MAP;
	}


	/**
	 * 
	 * @return
	 */
	public static int getHeuristicModifier() {
		return H_MODIFIER;
	}


	/**
	 * 
	 * @return
	 */
	public static int getTurnCost() {
		return TURN_COST;
	}


	/**
	 * 
	 * @return
	 */
	public static int getSpeedMax() {
		return MAX_SPEED;
	}


	/**
	 * 
	 * @return
	 */
	public static short getSpeedAhead() {
		return AHEAD_SPEED;
	}


	/**
	 * 
	 * @return
	 */
	public static short getSpeedRotation() {
		return ROTATION_SPEED;
	}


	/**
	 * 
	 * @return
	 */
	public static boolean getBorder() {
		return TILE_BORDER;
	}


	/**
	 * 
	 * @return
	 */
	public static Color getBorderColor() {
		return COLOR_BORDER;
	}


	/**
	 * 
	 * @return
	 */
	public static int getFontSizeLabel() {
		return FONT_SIZE_LABEL;
	}


	/**
	 * 
	 * @return
	 */
	public static int getFontSizeChess() {
		return FONT_SIZE_CHESS;
	}


	/**
	 * 
	 * @return
	 */
	public static boolean showLabels() {
		return SHOW_LABELS;
	}


	/**
	 * 
	 * @return
	 */
	public static Color getColorOpenNode() {
		return COLOR_OPEN_NODE;
	}


	/**
	 * 
	 * @return
	 */
	public static Color getColorClosedNode() {
		return COLOR_CLOSED_NODE;
	}


	/**
	 * 
	 * @return
	 */
	public static void disableLabels() {
		SHOW_LABELS = false;
		Views.draw();
		
	}


	/**
	 * 
	 * @return
	 */
	public static String strTop() {
		return TOP;
	}

	/**
	 * 
	 * @return
	 */
	public static String strBottom() {
		return BOTTOM;
	}

	/**
	 * 
	 * @return
	 */
	public static String strLeft() {
		return LEFT;
	}

	/**
	 * 
	 * @return
	 */
	public static String strRight() {
		return RIGHT;
	}

	/**
	 * 
	 * @return
	 */
	public static boolean delayed() {
		return DELAY_ANIMATIONS;
	}

	/**
	 * 
	 * @return
	 */
	public static boolean showOpenList() {
		return SHOW_OPEN_LIST;
	}

	/**
	 * 
	 * @return
	 */
	public static boolean showClosedList() {
		return SHOW_CLOSED_LIST;
	}

	/**
	 * 
	 * @return
	 */
	public static boolean showObstacle() {
		return SHOW_OBSTACLES;
	}


	public static boolean useThymio() {
		return USE_THYMIO;
	}

	/**
	 * 
	 * @return
	 */
	public static Thymio getThymio() {
		return t;
	}

	/**
	 * 
	 * @return
	 */
	public static ThymioController getThymioController() {
		return tc;
	}

	/**
	 * 
	 * @return
	 */
	public static long getMoveBias() {
		return MOVE_BIAS;
	}

	
}