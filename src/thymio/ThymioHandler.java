package thymio;

import pathfinding.Node;
import java.util.HashMap;


import de.ur.mi.graphics.Image;
import iw.ur.thymio.Thymio.Thymio;
import main.Helper;
import main.Settings;




public class ThymioHandler extends Image{
	
	// Fixed, don't change, adjustments in Settings Class
	private static final int DISTANCE = Settings.getFieldWidth();
	private static final int CANVAS_HEIGHT = Settings.getCanvasHeight();
	private static final int CANVAS_WIDTH = Settings.getCanvasWidth();
	private static final short SPEED_AHEAD = Settings.getSpeedAhead();
	private static final short SPEED_ROTATION = Settings.getSpeedRotation();
	private static final int MAX_SPEED = Settings.getSpeedMax();
	private static final String TOP = "north";
	private static final String BOTTOM ="south";
	private static final String LEFT = "west";
	private static final String RIGHT = "east";
	
	private static int CURRENT_ROTATION = 0;
//	private static Thymio t;
	
	
//	Constructor
	public ThymioHandler(double xPos, double yPos,int width, int height, String src, String orientation){
		super(xPos,yPos,width,height,src);
		
//		thymioSpeeds(t);
		setOrientation(orientation);
	}
	


@SuppressWarnings("unused")
private void thymioSpeeds(Thymio t) {
	 t = new Thymio("192.168.10.1");
	t.setSpeed("max", MAX_SPEED);
	t.setSpeed("rotation", SPEED_ROTATION);
	t.setSpeed("ahead", SPEED_AHEAD);
}

/* 	Sets rotation based on command sent to Thymio
* 	getPixelArray turns the Image into an array, flips it X-times and setPixelArray turns it back into an image
*	North / Up = 0
*	East / Right = 90
*	South / Down = 180
*	West / Left = 270
*/
	public void setOrientation(String movement) {

		switch (movement) {
		case LEFT:
			
			if(CURRENT_ROTATION == 0){
				super.setPixelArray(rotateMatrixLeft(super.getPixelArray()));
			}else if(CURRENT_ROTATION == 90){
				super.setPixelArray(rotateMatrixLeft(rotateMatrixLeft(super.getPixelArray())));
			}else if(CURRENT_ROTATION == 180){
				super.setPixelArray(rotateMatrixRight(super.getPixelArray()));
			}
			CURRENT_ROTATION = 270 ;
			break;
		case RIGHT:
			if(CURRENT_ROTATION == 0){
				super.setPixelArray(rotateMatrixRight(super.getPixelArray()));
			}else if(CURRENT_ROTATION == 270){
				super.setPixelArray(rotateMatrixRight(rotateMatrixRight(super.getPixelArray())));
			}else if(CURRENT_ROTATION == 180){
				super.setPixelArray(rotateMatrixLeft(super.getPixelArray()));
			}
			CURRENT_ROTATION = 90;
			break;
		case "north":
			if(CURRENT_ROTATION == 90){
				super.setPixelArray(rotateMatrixLeft(super.getPixelArray()));
			}else if(CURRENT_ROTATION == 270){
				super.setPixelArray(rotateMatrixRight(super.getPixelArray()));
			}else if(CURRENT_ROTATION == 180){
				super.setPixelArray(rotateMatrixLeft(rotateMatrixLeft(super.getPixelArray())));
			}
			CURRENT_ROTATION = 0;
			break;
		case "south":
			if(CURRENT_ROTATION == 90){
				super.setPixelArray(rotateMatrixRight(super.getPixelArray()));
			}else if(CURRENT_ROTATION == 270){
				super.setPixelArray(rotateMatrixLeft(super.getPixelArray()));
			}else if(CURRENT_ROTATION == 0){
				super.setPixelArray(rotateMatrixLeft(rotateMatrixLeft(super.getPixelArray())));
			}
			CURRENT_ROTATION = 180;
			break;
		default:
			break;
		}
		
	}
	
	public void move(String direction){
		switch (direction) {
		case TOP:
			moveUp();
			break;
		case BOTTOM:
			moveDown();
			break;
		case LEFT:
			moveLeft();
			break;
		case RIGHT:
			moveRight();
			break;
		default:
			break;
		}
	}
	
	// Moves Thymio left
	public void moveLeft() {	
	
		if(super.getX()>0  && collision(getPosAsNode(),"left") == false) {
			super.move(-DISTANCE, 0);
			setOrientation("left");	
			System.out.println("Thymio is at ["+getPosAsNode().getChessCoord()+"]");
			Node currentNode = Settings.getBoardNodes().get(getPosAsID());
			currentNode.updateNode(currentNode,270);
			try {
				Thread.sleep(Settings.getDelay());
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	

	// Moves Thymio right
	public void moveRight() {
		if(super.getX() < CANVAS_WIDTH-DISTANCE && collision(getPosAsNode(),"right") == false){
			super.move(DISTANCE, 0);
			setOrientation("right");
			System.out.println("Thymio is at ["+getPosAsNode().getChessCoord()+"]");
			Node currentNode = Settings.getBoardNodes().get(getPosAsID());
			currentNode.updateNode(currentNode,90);
			try {
				Thread.sleep(Settings.getDelay());
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	
	// Moves Thymio up
	public void moveUp() {
		if(super.getY()>0 && collision(getPosAsNode(),"top") == false){
			super.move(0, -DISTANCE);
			setOrientation("up");
			System.out.println("Thymio at "+getPosAsNode().getChessCoord());
			Node currentNode = Settings.getBoardNodes().get(getPosAsID());
			currentNode.updateNode(currentNode,0);
			try {
				Thread.sleep(Settings.getDelay());
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	// Moves Thymio down
	public void moveDown() {
		if(super.getY() < CANVAS_HEIGHT-DISTANCE && collision(getPosAsNode(),"bottom") == false){
			super.move(0, DISTANCE);
			setOrientation("down");
			System.out.println("Thymio is at ["+getPosAsNode().getChessCoord()+"]");
			Node currentNode = Settings.getBoardNodes().get(getPosAsID());
			currentNode.updateNode(currentNode,180);
			try {
				Thread.sleep(Settings.getDelay());
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	

	
	// check for Collisions/Obstacles
	private boolean collision(Node current, String direction) {
		HashMap<String, Node> neighbours = Settings.getBoard().getNeighbourNodes(current);
		
			
			//  Check if surrounding contains an obstacle
			switch (direction) {
				case "top":
					if(neighbours.get(direction).isObstacle()){
						System.out.println("Collision");
						return true;
					}else{
						return false;
					}
				case "bottom":
					if(neighbours.get(direction).isObstacle()){
						System.out.println("Collision");
						return true;
					}else{
						return false;
					}
					
				case "left":
					if(neighbours.get(direction).isObstacle()){
						System.out.println("Collision");
						return true;
					}else{
						return false;
					}
							
					
				case "right":
					if(neighbours.get(direction).isObstacle()){
						System.out.println("Collision");
						return true;
					}else{
						return false;
					}
					
				default:
					return false;
			}

	
		
	}
	
	
	// rotate Image to the right
	public int[][] rotateMatrixRight(int[][] matrix)
	{

	    int w = matrix.length;
	    int h = matrix[0].length;
	    int[][] ret = new int[h][w];
	    for (int i = 0; i < h; ++i) {
	        for (int j = 0; j < w; ++j) {
	            ret[i][j] = matrix[w - j - 1][i];
	        }
	    }
	    return ret;
	}


	// rotate Image to the left
	public int[][] rotateMatrixLeft(int[][] matrix)
	{
	    int w = matrix.length;
	    int h = matrix[0].length;   
	    int[][] ret = new int[h][w];
	    for (int i = 0; i < h; ++i) {
	        for (int j = 0; j < w; ++j) {
	            ret[i][j] = matrix[j][h - i - 1];
	        }
	    }
	    return ret;
	}
	
	
	public int getPosAsID(){
		
		return Helper.calculateID(getXPosAsField(), getYPosAsField());
	}
	
	public Node getPosAsNode(){
		return Settings.getBoard().getNodes().get(getPosAsID());
	}
	
	// Draws Thymio
	public void draw() {
		super.draw();
	}
	
	// Return Thymios X Coord as Field  (0, 1, 2 etc)
	public int getXPosAsField(){
		return (int)super.getX()/Settings.getFieldHeight();
	}
	// Return Thymios Y Coord as Field
	public int getYPosAsField(){
		return (int)super.getY()/Settings.getFieldHeight();
	}
	
//	Returns Orientation
	public int getOrientation(){
		return CURRENT_ROTATION;
	}
}
