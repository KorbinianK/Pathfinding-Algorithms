package main;
import java.util.List;

import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Label;
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
	

	private static final List<Node> nodes = Settings.getBoardNodes();
	private static final int start_x = Settings.getStartXCoordinate()+17;
	private static final int start_y = Settings.getStartYCoordinate()+30;
	private static final int start_fontsize = Settings.getFontSizeStartpoint();
	private static final int end_x = Settings.getEndXCoordinate()+17;
	private static final int end_y = Settings.getEndYCoordinate()+30;
	private static final int end_fontsize = Settings.getFontSizeEndpoint();
	private static final String endtext = "X";
	private static final String starttext = "O";
		/**
		 * Draws the Start Field
		 */
		private static void drawStartLabel() {
	    	
	    	
			Label start = new Label(start_x,start_y,starttext,Color.WHITE,start_fontsize);
			
	    	Label end = new Label(end_x,end_y,endtext,Color.WHITE,end_fontsize);
	    	end.draw();
			start.draw();
		}
	    
	    
	    /**
	     * Draws the End Field
	     */
	    private static void drawEndLabel(){
	    	
	    }
	     
	  
	 	/**
	 	 * Main Draw method
	 	 */
		public static void draw() {
			drawBoard();
			if(Settings.showLabels()){
				drawStartLabel();
		    	drawEndLabel();		
			}
	    	
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


	
