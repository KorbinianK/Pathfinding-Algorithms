package main;
import java.util.List;

import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Label;
import de.ur.mi.graphics.Rect;
import pathfinding.Node;

public class Views {
	
	private static final int FIELD_WIDTH = Settings.getFieldWidth();;
	private static final int FIELD_HEIGHT = Settings.getFieldHeight();
	private static final List<Node> nodes = Settings.getBoardNodes();

		
		// Draws the Start
	    static void drawStartPoint(int startX, int startY) {
	    	Rect start = new Rect(startX,startY, FIELD_WIDTH, FIELD_HEIGHT, Settings.getStartFieldColor());
	    	start.draw();
			Label starttext = new Label(Settings.getStartX()+20,Settings.getStartY()+5+Settings.getFieldHeight()/2,"S",Color.WHITE);
			starttext.setFontSize(Settings.getFontSizeStartpoint());
			starttext.draw();
		}
	    
	    
	    // Draws the End
	     static void drawEndPoint(int endX, int endY){
	    	Rect end = new Rect(endX,endY, FIELD_WIDTH, FIELD_HEIGHT, Settings.getEndFieldColor());
	    	end.draw();
	    	Label endtext = new Label(Settings.getEndX()+20,Settings.getEndY()+5+Settings.getFieldHeight()/2,"E",Color.WHITE);
		       
	    	endtext.setFontSize(Settings.getFontSizeEndpoint());
	    	
	    	endtext.draw();
	    }
	     
	     // Checks if a field is an obstacle and if so, draws it
	     
	 	static void drawObstacles() {
	 		List<Node> nodes = Settings.getBoardNodes();
	 		for(Node node : nodes){
	 			if(node.isObstacle()){
	 				node.setColor(Settings.getColorObstacle());
	 			}
			
			}
	 	}


//	 	Draw method to draw the individual elements
		public static void draw() {
			drawBoard();
	    	drawStartPoint(Settings.getStartX(),Settings.getStartY());
	    	drawEndPoint(Settings.getEndX(),Settings.getEndY());		
		}
		
	


	public static void drawBoard() {
		for(Node node : nodes){
	       		node.draw();
			}
		}

}


	
