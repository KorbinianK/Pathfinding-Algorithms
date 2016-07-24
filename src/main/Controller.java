package main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import de.ur.mi.graphicsapp.GraphicsApp;
//import iw.ur.thymio.Thymio2.Thymio;
import map.Chessboard;
import pathfinding.AStar;
import pathfinding.Node;
import thymio.Thymio;
import thymio.ThymioController;
import thymio.ThymioVisualController;

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
		private static final long MOVE_BIAS = Settings.getMoveBias();
		private static Chessboard board = Settings.getBoard();
		public static ThymioVisualController thymioHandler ;
		public static Thymio thymio =  Settings.getThymio();
		public static ThymioController tc = Settings.getThymioController();
	
	/**
	 * Basic setup method 
	 * 
	 * This method sets the Canvas size and initializes the ThymioHandler
	 */
    public void setup() {
    	size(CANVAS_WIDTH,CANVAS_HEIGHT);   
      	background(Settings.getColorBackground());
    	thymioHandler = new ThymioVisualController(THYMIO_STARTFIELD_X, THYMIO_STARTFIELD_Y, FIELD_HEIGHT, FIELD_HEIGHT, Settings.getThymioImg(),Settings.getThymioStartRotation());
      	if(Settings.useThymio()){
      		setupThymio(thymio);
      	}

    }
    
    /**
     * Sets the Speeds of the Real Thymio
     * @param t
     */
    private void setupThymio(Thymio t) {
    	
//    	t.setSpeed("max", MAX_SPEED);
//    	t.setSpeed("rotation", SPEED_ROTATION);
//    	t.setSpeed("ahead", SPEED_AHEAD);
    	t.setMoveSensitivity(MOVE_BIAS);
    	
    	if(Settings.getStartFieldColor() == Settings.getColorChessA()){
        	t.setStartField(1);
    	}else{
    		t.setStartField(0);
    	}

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
			if(Settings.useThymio()){
				tc.moveRight();
			}
			System.out.println("Reached destination: "+reachedDest());
			break;
		case 'w':
			thymioHandler.moveUp();	
			if(Settings.useThymio()){
				tc.moveUp();
			}
			System.out.println("Reached destination: "+reachedDest());
			break;
		case 'a':
			thymioHandler.moveLeft();	
			if(Settings.useThymio()){
				tc.moveLeft();
			}
			System.out.println("Reached destination: "+reachedDest());
			break;
		case 's':
			thymioHandler.moveDown();
			if(Settings.useThymio()){
				tc.moveDown();
			}
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
			thymio.drive(1000, 1000);
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
