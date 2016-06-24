package main;
import java.io.IOException;
import java.util.List;

import de.ur.mi.graphics.Color;
import map.CSVData;
import map.Chessboard;
import map.MapGenerator;
import map.Obstacles;
import pathfinding.Node;


/*
 * Settings Class with all the Getter and Setter. Adjust for custom setting
 */
public class Settings  {
	
	//	Awesome Thymio Image
	private static final String THYMIO_IMG = "images/thymio.gif";
	private static final String THYMIO_ROTATION = "north";
	// Colors
	private static Color COLOR_CHESS_A = Color.DARK_GRAY;
	private static Color COLOR_CHESS_B = Color.LIGHT_GRAY;
	private static Color COLOR_OBSTACLE = Color.RED;
	private static Color START_COLOR = Color.GREEN;
	private static Color END_COLOR = Color.BLUE;
	
	
	// Fonts
	private static int FONT_SIZE_STARTPOINT = 13;
	private static int FONT_SIZE_ENDPOINT = 19;
	
	//	Startfield (currently possible: 0-19)
	private static int THYMIO_STARTFIELD_X = 4; 
	private static int THYMIO_STARTFIELD_Y = 1;;
	
	//	Endfield (currently possible: 0-19)
	private static int THYMIO_ENDFIELD_X = 17;
	private static int THYMIO_ENDFIELD_Y = 3;
	
	/*
	 * Probability is calculated by picking a random number between 0 and RANDOM_OBSTACLE_PROBABILTY_RANGE.
	 * If it's greater than RANDOM_OBSTACLE COUNT, it is an obstacle.
	 */
	private static boolean RANDOM_OBSTACLES = false; // if this is set to false, it will read the obstacle_map.csv. If it doesn't exist, a new one with random obstacles gets created
	
	private static int RANDOM_OBSTACLE_COUNT = 2;
	private static int RANDOM_OBSTACLE_PROBABILITY_RANGE = 20;
	private static final int DELAY = 100;
	
	
/*
 * 	No changes needed below this line. Canvas_Width could be increased in 50pixel steps.
 */
	private static final int CANVAS_HEIGHT = 400;
	private static final int FIELD_HEIGHT = getFieldWidth();
	private static final int CANVAS_WIDTH  = 1000;
	private static final int FIELD_WIDTH = 50;
	private static Chessboard board = new Chessboard(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT, COLOR_CHESS_A);
	
//	private static final MapGenerator mapGen = new MapGenerator();
//	private static List<Node> mapNodes = mapGen.getNodes();
	private static final String EMPTY_MAP_SRC = "empty_map.csv";
	private static final String OBSTACLE_MAP_SRC = "obstacle_map.csv";
	private static CSVData csv = getReader();
	private static List<String[]> csv_list = csv.getEntries();
	private static final Obstacles obstacles = new Obstacles();	
	private static Helper h = new Helper();
	
	// Maps
	
	
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
		return getThymioStartField_X() * getFieldHeight();
	}
	public static int getStartY(){
		return getThymioStartField_Y() * getFieldHeight();
	}
	public static int getEndX(){
		return getThymioEndField_X() * getFieldHeight();
	}
	public static int getEndY(){
		return getThymioEndField_Y() * getFieldHeight();
	}
	
	public static int getThymioStartField_X() {
		return THYMIO_STARTFIELD_X;
	}
	public static int setThymioStartField_X(int x) {
		THYMIO_STARTFIELD_X = x;
		return THYMIO_STARTFIELD_X;
	}
	public static String getThymioImg() {
		return THYMIO_IMG;
	}
	public static int getThymioStartField_Y() {
		return THYMIO_STARTFIELD_Y;
	}
	public static int setThymioStartField_Y(int y) {
		THYMIO_STARTFIELD_Y = y;
		return THYMIO_STARTFIELD_Y;
	}
	public static int getThymioEndField_X() {
		return THYMIO_ENDFIELD_X;
	}
	public static int setThymioEndField_X(int x) {
		THYMIO_ENDFIELD_X = x;
		return x;
	}
	public static int getThymioEndField_Y() {
		return THYMIO_ENDFIELD_Y;
	}
	public static int setThymioEndField_Y(int y) {
		THYMIO_ENDFIELD_Y = y;
		return y;
	}
	public static int getObstacleProbability() {
		return RANDOM_OBSTACLE_COUNT;
	}
	public static void setObstacleProbability(int prob) {
		RANDOM_OBSTACLE_COUNT = prob;
	}
	public static int getObstacleProbabilityRange() {
		return RANDOM_OBSTACLE_PROBABILITY_RANGE;
	}
	public static void setObstacleProbabilityRange(int range) {
		RANDOM_OBSTACLE_PROBABILITY_RANGE = range;
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
		return DELAY;
	}
	public static Obstacles getObstacles() {
		return obstacles;
	}
	public static char[][] getObstacleArray(){
		return obstacles.getObstacles(Settings.RANDOM_OBSTACLE_COUNT);
	}
	public static Chessboard getBoard() {
		return board;
	}
	public static void setBoard(Chessboard board) {
		Settings.board = board;
	}
	public static Color getColorChessA() {
		return COLOR_CHESS_A;
	}
	public static void setColorChessA(Color color) {
		COLOR_CHESS_A = color;
	}
	public static Color getColorChessB() {
		return COLOR_CHESS_B;
	}
	public static void setColorChessB(Color color) {
		COLOR_CHESS_B = color;
	}
	public static Color getColorObstacle() {
		return COLOR_OBSTACLE;
	}
	public static void setColorObstacle(Color cOLOR_OBSTACLE) {
		COLOR_OBSTACLE = cOLOR_OBSTACLE;
	}
	public static Color getStartFieldColor() {
		return START_COLOR;
	}
	public static void setStartFieldColor(Color startFieldColor) {
		START_COLOR = startFieldColor;
	}
	public static Color getEndFieldColor() {
		return END_COLOR;
	}
	public static void setEndFieldColor(Color endFieldColor) {
		END_COLOR = endFieldColor;
	}
	public static boolean randomObstacles() {
		return RANDOM_OBSTACLES;
	}

	public static int getFontSizeEndpoint() {
		return FONT_SIZE_ENDPOINT;
	}
	public static void setFontSizeEndpoint(int size) {
		FONT_SIZE_ENDPOINT = size;
	}
	public static int getFontSizeStartpoint() {
		return FONT_SIZE_STARTPOINT;
	}
	public static void setFontSizeStartpoint(int size) {
		FONT_SIZE_STARTPOINT = size;
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
		int id = h.calculateID(THYMIO_STARTFIELD_X,THYMIO_STARTFIELD_Y);
		return getBoard().getNodes().get(id);
	}

	public static Node getEndNode() {
		int id = h.calculateID(THYMIO_ENDFIELD_X,THYMIO_ENDFIELD_Y);
		return getBoard().getNodes().get(id);
	}

	public static List<Node> getBoardNodes() {
		getBoard().createNodes();
		
		return getBoard().getNodes();
	}


	
}