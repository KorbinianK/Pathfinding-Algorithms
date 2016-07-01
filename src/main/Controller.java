package main;
import de.ur.mi.graphicsapp.*;
import map.Chessboard;
import thymio.ThymioHandler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import pathfinding.AStar;
import pathfinding.Node;

/**
 * <h1>Controller Class for the Thymio project</h1>
 * <h3> Course: Informationssysteme (SS 2016) Universitaet Regensburg</h3>
 * 
 * <div>Dozent: Prof. Dr. Bernd Ludwig</div>
 * 
 * 
 * This Class handles the Interaction between the User and the "Game"
 * 
 * @version 1.0
 * @author Korbinian Kasberger:korbinian1.kasberger@stud.uni-regensburg.de
 */
public class Controller extends GraphicsApp implements KeyListener {


		private static final long serialVersionUID = -8745424889380898909L;
		private static final int FIELD_HEIGHT = Settings.getFieldHeight();
		private static final int CANVAS_HEIGHT = Settings.getCanvasHeight();
		private static final int CANVAS_WIDTH = Settings.getCanvasWidth();
		private static int THYMIO_STARTFIELD_X = Settings.getStartXCoordinate();
		private static int THYMIO_STARTFIELD_Y = Settings.getStartYCoordinate();;
		private static Chessboard board = Settings.getBoard();
		public static ThymioHandler thymio ; 
	
	/**
	 * Basic setup method 
	 * 
	 * This method sets the Canvas size and initializes the ThymioHandler
	 */
    public void setup() {
    	 size(CANVAS_WIDTH,CANVAS_HEIGHT);   
      	background(Settings.getColorBackground());
      	thymio = new ThymioHandler(THYMIO_STARTFIELD_X, THYMIO_STARTFIELD_Y, FIELD_HEIGHT, FIELD_HEIGHT, Settings.getThymioImg(),Settings.getThymioStartRotation());
    
    }
    
    
   /**
    * Draws the views
    */
	public void draw(){
		Views.draw();
		thymio.draw();
    }


    
    /**
     * Key Listener to move the Thymio by Hand or start the Calculation
     * 
     * @param e: KeyEvent
     */
	@Override
	public void keyPressed(KeyEvent e) {
    	
    	switch (e.getKeyChar()) {
		case 'd':
			thymio.moveRight();		
			System.out.println("Reached destination: "+reachedDest());
			break;
		case 'w':
			thymio.moveUp();	
			System.out.println("Reached destination: "+reachedDest());
			break;
		case 'a':
			thymio.moveLeft();	
			System.out.println("Reached destination: "+reachedDest());
			break;
		case 's':
			thymio.moveDown();
			System.out.println("Reached destination: "+reachedDest());
			break;
		
		case 't': // Only for testing/debugging purposes
			Node current = board.getNodes().get(thymio.getPosAsID());
			HashMap<String,Node> neighbours = board.getNeighbourNodes(current);
			
			System.out.print("Possible Destinations:");
			for(Node neighbour : neighbours.values()){
				if(!neighbour.isObstacle()){
					System.out.print("["+neighbour.getChessCoord()+"]");
				}
			}
			System.out.println("");
			break;

		case'c':
			AStar a = new AStar();
			a.calculate();
			break;	
		case 'r':
			thymio.setPosition(THYMIO_STARTFIELD_X, THYMIO_STARTFIELD_Y);
			thymio.setOrientation(Settings.getThymioStartRotation());
			for(Node node : Settings.getBoardNodes()){
				node.resetColor();
			}
			break;
		case'n':
			Settings.disableLabels();
			break;
    	}
    	
    	Views.draw();
	
		
		
		
	}


/*
 * Checks if Thymio has reached his destination
 */
	private boolean reachedDest() {
		if(thymio.getPosAsNode() == Settings.getEndNode()) {
			return true;
		}
		return false;
	}

}
