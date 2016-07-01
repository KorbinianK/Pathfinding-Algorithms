package pathfinding;

import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Label;
import de.ur.mi.graphics.Rect;
import main.Settings;

public class Node {
	
	private static final int FONT_CHESS = Settings.getFontSizeChess();
	private static final int FONT_LABEL = Settings.getFontSizeLabel();
	private static final int FIELD_SIZE = Settings.getFieldHeight();
	private static final boolean hasBorder = Settings.getBorder();
	private static final Color BORDER_COLOR = Settings.getBorderColor();
	private static final Color COLOR_A = Settings.getColorChessA();
	private static final Color COLOR_B = Settings.getColorChessB();
	private static final Color COLOR_MOVE = Settings.getColorMovement();
	
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
		
		 y = getYCoord(); 
		 x = getXCoord();
		 if(y != 0){
			 y *= Settings.getFieldHeight();
		 }
		 if(x != 0){
			 x *= Settings.getFieldHeight();
		 }
		  label_x = x+Settings.getFieldHeight();
	}
	
	
	public void setOrientation(int orientation){
		
			
			this.orientation = orientation;
			
	}
	
	
	public int getOrientation(){
		return orientation;
	}


	
	public String getChessCoord() {
		return chessCoord;
	}
	
	public int getYCoord() {
		return yCoord;
	}

	public int getXCoord() {
		return xCoord;
	}

	
	public void setObstacle(boolean b){
		isObstacle = true;
	}
	public boolean isObstacle(){
		return isObstacle;
	}
	public int getId() {
		return id;
	}

	public String toCoordString() {
		return yCoord+","+xCoord;
	}
	
	public void setFCost(int f_cost){
		if(f_cost < this.f_cost){
			this.f_cost = f_cost;
		}
	}
	public void setGCost(int g_cost){
		
			this.g_cost = g_cost;
		
		
	}
	
	public int getFCost(){
		return f_cost;
	}
	
	public int getGCost(){
		return g_cost;
	}
	
	public void setOrientationByString(String direction) {
		switch (direction) {
		case "top":
			setOrientation(0);
			break;
		case "bottom":
			setOrientation(180);
			break;
		case "left":
			setOrientation(270);
			break;
		case "right":
			setOrientation(90);
			break;
		default:
			break;
		}
	}
	
	public void setColor(Color c){
		if(color == null){
			originalColor = c;
		}
		
		
		color = c;
	}
	public Color getColor(){
		return color;
	}
	
	public void resetColor(){
		if(color != Settings.getColorObstacle()){
			color = originalColor;
		}
		
	}
	
	public void draw(){
		if(h_cost == 0){
			this.h_cost = AStar.calculateCostH(this);
		}
		 drawTile(x,y);
		 if(showLabels && x != Settings.getEndX()*FIELD_SIZE && y != Settings.getEndY()*FIELD_SIZE){
			 drawLabels(label_x,y); 
		 }
		

		
		
	}
	
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


	private void drawTile(int x, int y) {
		Rect rect = new Rect(y, x, FIELD_SIZE, FIELD_SIZE,color);
		if(hasBorder){
			rect.setBorder(BORDER_COLOR, 1);
		}
	
		rect.draw();
	}


	public int getHCost() {
		
		return h_cost;
	}
	public void setHCost(int cost) {
		this.h_cost = cost;
	}

	public void updateNode(Node currentNode, int orientation) {
		currentNode.setOrientation(orientation);
		currentNode.setColor(Settings.getColorMovement());
		
	}

	public String getOrientationString() {
		switch (getOrientation()) {
		case 0:
			return "top";
		case 90:
			return "right";
		case 180:
			return "bottom";
		case 270:
			return "left";
		default:
			return "top";
		}
	}


	public void setParentNode(Node node) {
		this.parent=node;	
	}
	
	public Node getParent(){
		return parent;
	}
}
