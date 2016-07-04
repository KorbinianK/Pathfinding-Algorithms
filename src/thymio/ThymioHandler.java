package thymio;

import pathfinding.Node;
import java.util.HashMap;


import de.ur.mi.graphics.Image;
import iw.ur.thymio.Thymio.Thymio;
import main.Controller;
import main.Helper;
import main.Settings;
import main.ThymioController;

/**
 * <h1> ThymioHandler Class for the Thymio project</h1>
 * <h3> Course: Informationssysteme (SS 2016) Universitaet Regensburg</h3>
 * 
 * <div>Dozent: Prof. Dr. Bernd Ludwig</div>
 * 
 * 
 * Handles the Movement of the "Fake-Thymio"
 * 
 * 
 * @version 1.0
 * @author Korbinian Kasberger: korbinian1.kasberger@stud.uni-regensburg.de
 */


public class ThymioHandler extends Image{
	
	// Fixed, don't change, adjustments in Settings Class
	private static final int DISTANCE = Settings.getFieldWidth();
	private static final int CANVAS_HEIGHT = Settings.getCanvasHeight();
	private static final int CANVAS_WIDTH = Settings.getCanvasWidth();
	
	private static final String TOP = "north";
	private static final String BOTTOM ="south";
	private static final String LEFT = "west";
	private static final String RIGHT = "east";
	ThymioController tc = Settings.tc;
	Thymio t = Settings.t;
	private static int CURRENT_ROTATION = 0;
	
	
/**
 * Constructor
 * 
 * @param xPos
 * @param yPos
 * @param width
 * @param height
 * @param src: Image
 * @param orientation
 */
	public ThymioHandler(double xPos, double yPos,int width, int height, String src, String orientation){
		super(xPos,yPos,width,height,src);
		setOrientation(orientation);
	}
	

/**
 * Sets the Orientation of Thymio and flips the Image
 * 
 * 	North / Up = 0
 *	East / Right = 90
 *	South / Down = 180
 *	West / Left = 270
 *
 * @param movement
 */
	public void setOrientation(String movement) {

		switch (movement) {
		case LEFT:
			
			if(CURRENT_ROTATION == 0){
				if(Settings.useThymio()){
					t.rotate(-90);
				}
				super.setPixelArray(rotateMatrixLeft(super.getPixelArray()));
			}else if(CURRENT_ROTATION == 90){
				if(Settings.useThymio()){
					t.rotate(180);
				}
				super.setPixelArray(rotateMatrixLeft(rotateMatrixLeft(super.getPixelArray())));
			}else if(CURRENT_ROTATION == 180){
				if(Settings.useThymio()){
					t.rotate(90);
				}
				super.setPixelArray(rotateMatrixRight(super.getPixelArray()));
			}
			CURRENT_ROTATION = 270 ;
			break;
		case RIGHT:
			if(CURRENT_ROTATION == 0){
				if(Settings.useThymio()){
					t.rotate(90);
				}else{
					super.setPixelArray(rotateMatrixRight(super.getPixelArray()));

				}
			}else if(CURRENT_ROTATION == 270){
				if(Settings.useThymio()){
					t.rotate(180);
				}else{
					super.setPixelArray(rotateMatrixRight(rotateMatrixRight(super.getPixelArray())));

				}
			}else if(CURRENT_ROTATION == 180){
				if(Settings.useThymio()){
					t.rotate(-90);
				}else{
					super.setPixelArray(rotateMatrixLeft(super.getPixelArray()));
				}
			}
			CURRENT_ROTATION = 90;
			break;
		case "north":
			if(CURRENT_ROTATION == 90){
				if(Settings.useThymio()){
					t.rotate(-90);
				}else{
					super.setPixelArray(rotateMatrixLeft(super.getPixelArray()));
				}
			}else if(CURRENT_ROTATION == 270){
				if(Settings.useThymio()){
					t.rotate(90);
				}else{
					super.setPixelArray(rotateMatrixRight(super.getPixelArray()));
				}
			}else if(CURRENT_ROTATION == 180){
				if(Settings.useThymio()){
					t.rotate(270);
				}else{
					super.setPixelArray(rotateMatrixLeft(rotateMatrixLeft(super.getPixelArray())));
				}
			}
			CURRENT_ROTATION = 0;
			break;
		case "south":
			if(CURRENT_ROTATION == 90){
				if(Settings.useThymio()){
					t.rotate(90);
				}else{
					super.setPixelArray(rotateMatrixRight(super.getPixelArray()));
				}
			}else if(CURRENT_ROTATION == 270){
				if(Settings.useThymio()){
					t.rotate(-90);
				}else{
					super.setPixelArray(rotateMatrixLeft(super.getPixelArray()));
				}
			}else if(CURRENT_ROTATION == 0){
				if(Settings.useThymio()){
					t.rotate(180);
				}else{
					super.setPixelArray(rotateMatrixLeft(rotateMatrixLeft(super.getPixelArray())));
				}
			}
			CURRENT_ROTATION = 180;
			break;
		default:
			break;
		}
		
	}
	
	
	/**
	 * Mpves Thymio
	 * @param direction
	 */
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
	
	/**
	 *  Moves Thymio left
	 */
	public void moveLeft() {	
		if(super.getX()>0  && collision(getPosAsNode(),LEFT) == false) {
			super.move(-DISTANCE, 0);
			setOrientation(LEFT);	
			System.out.println("Thymio is at ["+getPosAsNode().getChessCoord()+"]");
			Node currentNode = Settings.getBoardNodes().get(getPosAsID());
			currentNode.updateNode(270);
			
			if(Settings.delayed()){
				delay();
			}
		}
	}
	
	

	/**
	 *  Moves Thymio right
	 */
	public void moveRight() {
		if(super.getX() < CANVAS_WIDTH-DISTANCE && collision(getPosAsNode(),RIGHT) == false){
			super.move(DISTANCE, 0);
			setOrientation(RIGHT);
			System.out.println("Thymio is at ["+getPosAsNode().getChessCoord()+"]");
			Node currentNode = Settings.getBoardNodes().get(getPosAsID());
			currentNode.updateNode(90);
			if(Settings.delayed()){
				delay();
			}
		}
		
	}
	
	/**
	 *  Moves Thymio up
	 */
	public void moveUp() {
		if(super.getY()>0 && collision(getPosAsNode(),TOP) == false){
			super.move(0, -DISTANCE);
			setOrientation(TOP);
			System.out.println("Thymio at "+getPosAsNode().getChessCoord());
			Node currentNode = Settings.getBoardNodes().get(getPosAsID());
			currentNode.updateNode(0);
			
			if(Settings.delayed()){
				delay();
			}
		}
	}



	/**
	 *  Moves Thymio down
	 */
	public void moveDown() {
		if(super.getY() < CANVAS_HEIGHT-DISTANCE && collision(getPosAsNode(),BOTTOM) == false){
			super.move(0, DISTANCE);
			setOrientation(BOTTOM);
			System.out.println("Thymio is at ["+getPosAsNode().getChessCoord()+"]");
			Node currentNode = Settings.getBoardNodes().get(getPosAsID());
			currentNode.updateNode(180);
			if(Settings.delayed()){
				delay();
			}
		}
	}
	

	
	/**
	 *  Check for Collisions/Obstacles
	 *  
	 * @param currentNode
	 * @param direction: Direction to check for obstacle
	 * @return boolean
	 */
	private boolean collision(Node current, String direction) {
		HashMap<String, Node> neighbours = Settings.getBoard().getNeighbourNodes(current);
			switch (direction) {
				case TOP:
					if(!neighbours.get(direction).isObstacle()){	
						return false;
					}
				case BOTTOM:
					if(!neighbours.get(direction).isObstacle()){
						return false;
					}
				case LEFT:
					if(!neighbours.get(direction).isObstacle()){
						return false;
					}				
				case RIGHT:
					if(!neighbours.get(direction).isObstacle()){
						return false;
					}				
				default:
					System.out.println("Collision");
					return true;
			}
	}
	
	
	/**
	 * Delays the Thread
	 */
	private void delay() {
		try {
			Thread.sleep(Settings.getDelay());
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	/**
	 *  rotate Image to the right
	 * @param matrix of the Image
	 * @return flipped Matrix
	 */
	public int[][] rotateMatrixRight(int[][] matrix){
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


	/**
	 *  rotate Image to the left
	 * @param matrix of the Image
	 * @return flipped Matrix
	 */
	public int[][] rotateMatrixLeft(int[][] matrix){
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
	
	
	/**
	 * Returns the current Position as ID
	 * 
	 * @return ID
	 */
	public int getPosAsID(){	
		return Helper.calculateID(getXPosAsField(), getYPosAsField());
	}
	
	
	/**
	 * Returns current Position as Node
	 * 
	 * @return Node
	 */
	public Node getPosAsNode(){
		return Settings.getBoard().getNodes().get(getPosAsID());
	}
	
	/**
	 * Draws Thymio
	 */
	public void draw() {
		super.draw();
	}
	
	/**
	 * Return Thymios X Coord as Field  (0, 1, 2 etc)
	 * @return x-coordinate
	 */
	public int getXPosAsField(){
		return (int)super.getX()/Settings.getFieldHeight();
	}
	
	
	/**
	 * Return Thymios Y Coord as Field  (0, 1, 2 etc)
	 * @return y-coordinate
	 */
	public int getYPosAsField(){
		return (int)super.getY()/Settings.getFieldHeight();
	}
	
	/**
	 * Returns the current orientation
	 * 
	 * @return orientation
	 */
	public int getOrientation(){
		return CURRENT_ROTATION;
	}
}
