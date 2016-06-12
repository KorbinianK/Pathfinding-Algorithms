import de.ur.mi.graphics.Color;

/*
 * Settings Class with all the Getter and Setter. Adjust for custom setting
 */
public class Settings  {
	
	//	Awesome Thymio Image
	private static final String thymio_img = "images/thymio.gif";

	//	Startfield (currently possible: 0-9)
	private static int THYMIO_STARTFIELD_X = 0; 
	private static int THYMIO_STARTFIELD_Y = 0;;
	
	//	Endfield (currently possible: 0-9)
	private static int THYMIO_ENDFIELD_X = 3;
	private static int THYMIO_ENDFIELD_Y = 3;
	
	/*
	 * Probability is calculated by picking a random number between 0 and OBSTACLE_PROBABILTY_RANGE.
	 * If it's greater than OBSTACLE COUNT, it is an obstacle.
	 */
	private static int OBSTACLE_COUNT = 3;
	private static int OBSTACLE_PROBABILITY_RANGE = 20;
	private static final int DELAY = 100;
	
	
/*
 * 	No changes needed below this comment. Canvas width could be increased in 50pixel steps.
 */
	private static final int CANVAS_HEIGHT = getCanvasWidth();
	private static final int FIELD_HEIGHT = getFieldWidth();
	private static final int CANVAS_WIDTH  = 450;
	private static final int FIELD_WIDTH = 50;
	private static final Obstacles obstacles = new Obstacles();	
	private static Chessboard board = new Chessboard(0, 0, CANVAS_HEIGHT+FIELD_HEIGHT, CANVAS_WIDTH+FIELD_HEIGHT, Color.GRAY);
	private static int BOARD_ARRAY_SIZE = CANVAS_HEIGHT/FIELD_HEIGHT+1;

	
	
	
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
		return thymio_img;
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
		return OBSTACLE_COUNT;
	}
	public static void setObstacleProbability(int prob) {
		OBSTACLE_COUNT = prob;
	}
	public static int getObstacleProbabilityRange() {
		return OBSTACLE_PROBABILITY_RANGE;
	}
	public static void setObstacleProbabilityRange(int range) {
		OBSTACLE_PROBABILITY_RANGE = range;
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
		return obstacles.getObstacles(Settings.OBSTACLE_COUNT);
	}
	public static Chessboard getBoard() {
		
		return board;
	}
	public static void setBoard(Chessboard board) {
		Settings.board = board;
	}
	public static int getBoardArraySize() {
		return BOARD_ARRAY_SIZE;
	}
	public static void setBoardArraySize(int boardArraySize) {
		BOARD_ARRAY_SIZE = boardArraySize;
	}
}