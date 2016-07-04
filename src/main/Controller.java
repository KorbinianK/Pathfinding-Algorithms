package main;
import de.ur.mi.graphicsapp.*;
import iw.ur.thymio.Thymio.Thymio;
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
		private static final short SPEED_AHEAD = Settings.getSpeedAhead();
		private static final short SPEED_ROTATION = Settings.getSpeedRotation();
		private static final int MAX_SPEED = Settings.getSpeedMax();
		private static Chessboard board = Settings.getBoard();
		public static ThymioHandler thymioHandler ;
		public static Thymio thymio;
	
	/**
	 * Basic setup method 
	 * 
	 * This method sets the Canvas size and initializes the ThymioHandler
	 */
    public void setup() {
    	size(CANVAS_WIDTH,CANVAS_HEIGHT);   
      	background(Settings.getColorBackground());
      	thymioHandler = new ThymioHandler(THYMIO_STARTFIELD_X, THYMIO_STARTFIELD_Y, FIELD_HEIGHT, FIELD_HEIGHT, Settings.getThymioImg(),Settings.getThymioStartRotation());
      	if(Settings.useThymio()){
      		thymio = new Thymio("192.168.10.1");
      		setThymioSpeed(thymio);
      	}
    }
    
    /**
     * Sets the Speeds of the Real Thymio
     * @param t
     */
    private void setThymioSpeed(Thymio t) {
    	
    	t.setSpeed("max", MAX_SPEED);
    	t.setSpeed("rotation", SPEED_ROTATION);
    	t.setSpeed("ahead", SPEED_AHEAD);
    }
    
   /**
    * Draws the views
    */
	public void draw(){
		
		Views.draw();
		thymioHandler.draw();
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
			thymioHandler.moveRight();		
			System.out.println("Reached destination: "+reachedDest());
			break;
		case 'w':
			thymioHandler.moveUp();	
			System.out.println("Reached destination: "+reachedDest());
			break;
		case 'a':
			thymioHandler.moveLeft();	
			System.out.println("Reached destination: "+reachedDest());
			break;
		case 's':
			thymioHandler.moveDown();
			System.out.println("Reached destination: "+reachedDest());
			break;
		
		case 't': // Only for testing/debugging purposes
			Node current = board.getNodes().get(thymioHandler.getPosAsID());
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
			thymioHandler.setPosition(THYMIO_STARTFIELD_X, THYMIO_STARTFIELD_Y);
			thymioHandler.setOrientation(Settings.getThymioStartRotation());
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
		if(thymioHandler.getPosAsNode() == Settings.getEndNode()) {
			return true;
		}
		return false;
	}

}
