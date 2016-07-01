package main;
import java.util.List;

import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Label;
import de.ur.mi.graphics.Rect;
import pathfinding.Node;

/**
 * <h1> View Class for the Thymio project</h1>
 * <h3> Course: Informationssysteme (SS 2016) Universitaet Regensburg</h3>
 * 
 * <div>Dozent: Prof. Dr. Bernd Ludwig</div>
 * 
 * 
 * Handles the entire drawing of the Chessboard
 * 
 * @version 1.0
 * @author Korbinian Kasberger: korbinian1.kasberger@stud.uni-regensburg.de
 */

public class Views {
	
	private static final int FIELD_WIDTH = Settings.getFieldWidth();;
	private static final int FIELD_HEIGHT = Settings.getFieldHeight();
	private static final List<Node> nodes = Settings.getBoardNodes();

		
		/**
		 * Draws the Start Field
		 * 
		 * @param startX: X-Coordinate
		 * @param startY: Y-Coordinate
		 */
		private static void drawStartPoint(int startX, int startY) {
	    	Rect start = new Rect(startX,startY, FIELD_WIDTH, FIELD_HEIGHT, Settings.getStartFieldColor());
	    	start.draw();
			Label starttext = new Label(Settings.getStartXCoordinate()+20,Settings.getStartYCoordinate()+5+Settings.getFieldHeight()/2,"S",Color.WHITE);
			starttext.setFontSize(Settings.getFontSizeStartpoint());
			starttext.draw();
		}
	    
	    
	    /**
	     * Draws the End Field
	     * @param endX: X-Coordinate
	     * @param endY: Y-Coordinate
	     */
	    private static void drawEndPoint(int endX, int endY){
	    	Rect end = new Rect(endX,endY, FIELD_WIDTH, FIELD_HEIGHT, Settings.getEndFieldColor());
	    	end.draw();
	    	Label endtext = new Label(Settings.getEndXCoordinate()+20,Settings.getEndYCoordinate()+5+Settings.getFieldHeight()/2,"E",Color.WHITE);
		       
	    	endtext.setFontSize(Settings.getFontSizeEndpoint());
	    	
	    	endtext.draw();
	    }
	     
	  
	 	/**
	 	 * Main Draw method
	 	 */
		public static void draw() {
			drawBoard();
	    	drawStartPoint(Settings.getStartXCoordinate(),Settings.getStartYCoordinate());
	    	drawEndPoint(Settings.getEndXCoordinate(),Settings.getEndYCoordinate());		
		}
		
	

		/**
		 * Draws the individual tiles of the Chessboard
		 */
		private static void drawBoard() {
			for(Node node : nodes){
		       		node.draw();
				}
			}

}


	
