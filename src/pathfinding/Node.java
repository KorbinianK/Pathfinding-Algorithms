package pathfinding;

import acmx.export.java.util.ArrayList;
import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Label;
import de.ur.mi.graphics.Rect;
import main.Settings;


/**
 * <h1> Node Class for the Thymio project</h1>
 * <h3> Course: Informationssysteme (SS 2016) Universitaet Regensburg</h3>
 * 
 * <div>Dozent: Prof. Dr. Bernd Ludwig</div>
 *   
 * Gruppe 6:
 * Bauer Louisa, Durry Jan, Kasberger Korbinian, Kocher Sarah, Mykyttschak Lina 
 * 
 * Node Object representing a Tile on the Chessboard
 * 
 * 
 * 
 * @version 1.0
 * @author Korbinian Kasberger: korbinian1.kasberger@stud.uni-regensburg.de
 */

public class Node {
	
	private static final int FONT_CHESS = Settings.getFontSizeChess();
	private static final int FONT_LABEL = Settings.getFontSizeLabel();
	private static final int FIELD_SIZE = Settings.getFieldHeight();
	private static final boolean hasBorder = Settings.getBorder();
	private static final Color BORDER_COLOR = Settings.getBorderColor();
	private static final Color COLOR_A = Settings.getColorChessA();
	private static final Color COLOR_B = Settings.getColorChessB();
	private static final Color COLOR_MOVE = Settings.getColorMovement();
	private static final String TOP = "north";
	private static final String BOTTOM = "south";
	private static final String LEFT = "west";
	private static final String RIGHT = "east";
	private static ArrayList fixedColors = new ArrayList();
	
	private int h_cost;
	private int id;
	private int xCoord;
	private int yCoord;
	private String chessCoord;
	private int orientation;
	private boolean isObstacle;
	private int f_cost;
	private int g_cost;
	private Color color;
	private Color originalColor;
	private Node parent;
	private boolean showLabels;
	private int label_x;
	private int x;
	private int y;
	private boolean isStart;
	private boolean isEnd;

	/**
	 * Constructor
	 * 
	 * @param id
	 * @param xCoord
	 * @param yCoord
	 * @param chessCoord: Chess like Coordinate of this Node (e.g. A2, B4 etc.)
	 * @param orientation: Orientation of Thymio on this Node
	 */
	public Node(int id, int xCoord, int yCoord, String chessCoord, int orientation){
		this.id = id;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.chessCoord = chessCoord;
		if(chessCoord == null){
			this.chessCoord = "x";
		}
		this.isObstacle = false;
		this.f_cost = Integer.MAX_VALUE;
		this.g_cost = 0;
		this.h_cost = 0;
		this.showLabels = Settings.showLabels();
		this.parent = null;
		this.isStart = false;
		this.isEnd = false;
		this.y = getYCoord(); 
		this.x = getXCoord();
		 if(y != 0){
			 y *= Settings.getFieldHeight();
		 }
		 if(x != 0){
			 x *= Settings.getFieldHeight();
		 }
	    label_x = x+Settings.getFieldHeight();
	    fixedColors.add(Settings.getColorObstacle());
		fixedColors.add(Settings.getStartFieldColor());
		fixedColors.add(Settings.getEndFieldColor());		
	}
	
	
	/**
	 * Sets the Orientation of Thymio on this field
	 * @param orientation
	 */
	public void setOrientation(int orientation){
		this.orientation = orientation;		
	}
	
	
	
	public void updateLabelVision(boolean visible){
		if(visible){
			showLabels = true;
		}else{
			showLabels= false;
		}
	}
	
	/**
	 * Getter for the Orientation
	 * @return orientation of thymio on this node
	 */
	public int getOrientation(){
		return orientation;
	}

	/**
	 * Returns the Chess Coordinates of this Node
	 * @return String
	 */
	public String getChessCoord() {
		return chessCoord;
	}
	
	/**
	 * The Y Coordinate (not pixels)
	 * @return y-coordinate
	 */
	public int getYCoord() {
		return yCoord;
	}

	/**
	 * The X Coordinate (not pixels)
	 * @return x-coordinate
	 */
	public int getXCoord() {
		return xCoord;
	}

	/**
	 * Turns the Node into an obstacle or not
	 * @param b: boolean
	 */
	public void setObstacle(boolean b){
		isObstacle = b;
	}
	
	/**
	 * Returns whether the Node is an obstalce or not
	 * @return boolean
	 */
	public boolean isObstacle(){
		return isObstacle;
	}
	
	/**
	 * Returns the ID of the Node
	 * @return id
	 */
	public int getId() {
		return id;
	}

	
	/**
	 * Sets the F-Cost for the Node, but only if the current F-Cost are higher
	 * @param f_cost
	 */
	public void setFCost(int f_cost){
		if(f_cost < this.f_cost){
			this.f_cost = f_cost;
		}
	}
	
	/**
	 * Sets the G-Cost of the Node
	 * @param g_cost
	 */
	public void setGCost(int g_cost){
		this.g_cost = g_cost;
	}
	
	/**
	 * Returns the F-Cost of the Node
	 * @return f-cost
	 */
	public int getFCost(){
		return f_cost;
	}
	
	/**
	 * Returns the G-Cost of the Node
	 * @return g-cost
	 */
	public int getGCost(){
		return g_cost;
	}
	
	/**
	 * Returns the H-Cost of the Node
	 * @return h-cost
	 */
	public int getHCost() {
		
		return h_cost;
	}
	
	/**
	 * Sets the H-Cost of the Node
	 * @param cost
	 */
	public void setHCost(int cost) {
		this.h_cost = cost;
	}
	
	/**
	 * Sets the direction of Thymio on this node by using a String
	 * @param direction
	 */
	public void setOrientationByString(String direction) {
		switch (direction) {
		case TOP:
			setOrientation(0);
			break;
		case BOTTOM:
			setOrientation(180);
			break;
		case LEFT:
			setOrientation(270);
			break;
		case RIGHT:
			setOrientation(90);
			break;
		default:
			break;
		}
	}
	
	
	/**
	 * Sets the color of the Node/Tile
	 * 
	 * Saves the original color
	 * @param c: Color
	 */
	public void setColor(Color c){
		if(color == null || fixedColors.contains(c)){
			originalColor = c;
		}
		color = c;
	}
	
	/**
	 * Returns the Color of the Node/Tile
	 * @return
	 */
	public Color getColor(){
		return color;
	}
	
	/**
	 * Resets the color to the original Color
	 */
	public void resetColor(){
		if(!fixedColors.contains(color)){
			color = originalColor;
		}
		
	}
	
	/**
	 * Draws the Node
	 */
	public void draw(){
		if(h_cost == 0){
			this.h_cost = PathCalculation.calculateCostH(this);
		}
		 drawTile(x,y);
		 if(showLabels && x != Settings.getEndXCoordinate()*FIELD_SIZE && y != Settings.getEndYCoordinate()*FIELD_SIZE){
			 drawLabels(label_x,y); 
		 }

	}
	
	/**
	 * Draws the Labels on the node, showing various Infos
	 * 
	 * Prone to NPE, currently unstable
	 * @param x
	 * @param y
	 */
	private void drawLabels(int x, int y) {
		Color labelColor;
		if(color ==COLOR_A && !isObstacle){
			labelColor = COLOR_B;
		}else if(color == COLOR_B && !isObstacle){
			labelColor = COLOR_A;
		}else if(color == COLOR_MOVE){
			labelColor = Color.BLACK;
		}else if(isObstacle){
			labelColor = Color.ORANGE;
		}else{
			labelColor = Color.WHITE;
		}
	 	Label chess = new Label(y+2,x-2, chessCoord,labelColor );
		chess.setFontSize(FONT_CHESS);
		
		Label h = new Label(y+35,x-35, Integer.toString(h_cost), labelColor);
		h.setFontSize(FONT_LABEL);
		
		if(f_cost < Integer.MAX_VALUE){
			Label f = new Label(y+16,x-20, Integer.toString(f_cost), Color.BLACK);
			f.setFontSize(FONT_LABEL);
			f.draw();
		}
		if(g_cost >0){
			Label g = new Label(y+2,x-35,Integer.toString(g_cost), labelColor);
			g.setFontSize(FONT_LABEL);
			g.draw();
		}
		chess.draw();
		h.draw();
	}


	/**
	 * Draws the Tile itself
	 * @param x
	 * @param y
	 */
	private void drawTile(int x, int y) {
		Rect rect = new Rect(y, x, FIELD_SIZE, FIELD_SIZE,color);
		if(hasBorder){
			rect.setBorder(BORDER_COLOR, 1);
		}	
		rect.draw();
	}


	
	/**
	 * Updates the Node if Thymio moves onto it with Orientation and sets the color
	 * @param currentNode
	 * @param orientation
	 */
	public void updateNode(int orientation) {
		setOrientation(orientation);
		setColor(Settings.getColorMovement());	
	}

	
	/**
	 * Sets the Parent node of the current Node
	 * @param node: Parent node to set
	 */
	public void setParentNode(Node node) {
		this.parent=node;	
	}
	
	/**
	 * Returns the Parent Node
	 * @return Node
	 */
	public Node getParent(){
		return parent;
	}


	/**
	 * Closes the Node (A*) and sets the color
	 */
	public void close() {
		if(!isEnd && !isStart){
			setColor(Settings.getColorClosedNode());
		}
	}


	/**
	 * Opens the Node (A*) and sets the color
	 */
	public void open() {
		if(!isEnd && !isStart){
			setColor(Settings.getColorOpenNode());
		}
	}



	/**
	 * Sets the Node as Start
	 * @param isStart
	 */
	public void setStart() {
		isStart = true;
	}


	/**
	 * Sets the node as end
	 * @param isEnd
	 */
	public void setEnd() {
		isEnd = true;
	}


}
