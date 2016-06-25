package main;
import java.util.List;

import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Label;
import de.ur.mi.graphics.Rect;
import map.Chessboard;
import pathfinding.Node;

public class Views {
	
	private static final int FIELD_WIDTH = Settings.getFieldWidth();;
	private static final int FIELD_HEIGHT = Settings.getFieldHeight();
	private static Chessboard board = Settings.getBoard();
	static List<Node> nodes = Settings.getBoardNodes();
		
		// Draws the Start
	    static void drawStartPoint(int startX, int startY) {
	    	Rect start = new Rect(startX,startY, FIELD_WIDTH, FIELD_HEIGHT, Settings.getStartFieldColor());
			start.setBorder(Settings.getColorChessA(), 2);
	    	start.draw();
			Label starttext = new Label(Settings.getStartX()+5,Settings.getStartY()+Settings.getFieldHeight()/2,"START",Color.WHITE);
		       
			starttext.setFontSize(Settings.getFontSizeStartpoint());
			starttext.draw();
		}
	    
	    
	    // Draws the End
	     static void drawEndPoint(int endX, int endY){
	    	Rect end = new Rect(endX,endY, FIELD_WIDTH, FIELD_HEIGHT, Settings.getEndFieldColor());
	    	end.setBorder(Settings.getColorChessA(), 2);
	    	end.draw();
	    	Label endtext = new Label(Settings.getEndX()+5,Settings.getEndY()+Settings.getFieldHeight()/2,"END",Color.WHITE);
		       
	    	endtext.setFontSize(Settings.getFontSizeEndpoint());
	    	
	    	endtext.draw();
	    }
	     
	     // Checks if a field is an obstacle and if so, draws it
	 	static void drawObstacles() {
	 		List<Node> nodes = Settings.getBoardNodes();
	 		for(Node node : nodes){
	 			if(node.isObstacle()){
	 				Rect obstacle = new Rect(node.getYCoord()*FIELD_HEIGHT,node.getXCoord()*FIELD_HEIGHT, FIELD_HEIGHT, FIELD_WIDTH,Settings.getColorObstacle());
					obstacle.draw();
	 			}
			
				}
	 	}


//	 	Draw method to draw the individual elements
		public static void draw() {
			board.redraw();
	    	drawObstacles();
	    	drawStartPoint(Settings.getStartX(),Settings.getStartY());
	    	drawEndPoint(Settings.getEndX(),Settings.getEndY());		
//	    	drawVisited(Dijkstra.getVisitedArray());
		}
		
	public static void drawVisited(List<String> list){
		for (int i = 0; i < list.size(); i++) {
			
		}
//		Rect small = new Rect((int)d, (int)e, Settings.getFieldHeight()/2, Settings.getFieldHeight()/2, Color.MAGENTA);
//		small.draw();
	}

}


	
