package pathfinding;

import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Rect;
import main.Settings;

public class Node {
	
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
	private int originalOrientation;
	private Node parent;
	private boolean closed;
	
	public Node(int id, int xCoord, int yCoord, String chessCoord, int orientation){
		this.id = id;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.chessCoord = chessCoord;
		this.isObstacle = false;
		this.f_cost = Integer.MAX_VALUE;
		this.g_cost = Integer.MAX_VALUE;

		this.parent = null;
		this.closed = false;
	}
	
	
	public void setOrientation(int orientation){
		
			
			this.orientation = orientation;
			
	}
	
	public void close(){
		this.closed = true;
		
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
		if(g_cost < this.g_cost){
			this.g_cost = g_cost;
		}
		
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
		if(c != color){
			originalColor = color;	
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
		Rect rect = new Rect(getYCoord()*50, getXCoord()*50, 50, 50,getColor());
		rect.draw();
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


	public void setParentNode(Node currentNode) {
		this.parent=currentNode;	
	}
	
	public Node getParent(){
		return parent;
	}
}
