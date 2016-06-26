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
//			start.setBorder(Settings.getColorChessA(), 2);
	    	start.draw();
			Label starttext = new Label(Settings.getStartX()+20,Settings.getStartY()+5+Settings.getFieldHeight()/2,"S",Color.WHITE);
			starttext.setFontSize(Settings.getFontSizeStartpoint());
			starttext.draw();
		}
	    
	    
	    // Draws the End
	     static void drawEndPoint(int endX, int endY){
	    	Rect end = new Rect(endX,endY, FIELD_WIDTH, FIELD_HEIGHT, Settings.getEndFieldColor());
//	    	end.setBorder(Settings.getColorChessA(), 2);
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


	public static void drawBoard() {
		List<Node> nodes = Settings.getBoardNodes();
		for (int i = 0; i < Settings.getBoardArrayWidth(); i++) {
	       	for (int j = 0; j < Settings.getBoardArrayHeight(); j++) {     	
	       		Node node = nodes.get(Helper.calculateID(i,j));
	       		boolean white = (i % 2 == 0) == (j % 2 == 0);
	       		if(white && node.getColor()==null){
	       			node.setColor(Settings.getColorChessA());
	       		}else if(node.getColor()==null){
	       			node.setColor(Settings.getColorChessB());
	       		}
	       		node.draw();
				}
		}
		
	}

}


	
