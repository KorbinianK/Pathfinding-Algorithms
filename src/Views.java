import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Label;
import de.ur.mi.graphics.Rect;

public class Views {
	
	private static final int FIELD_WIDTH = Settings.getFieldWidth();;
	private static final int FIELD_HEIGHT = Settings.getFieldHeight();
	private static final int CANVAS_WIDTH = Settings.getCanvasWidth();
	private static Obstacles obstacles = Settings.getObstacles();   
		
		// Draws the Start
	    static void drawStartPoint(int startX, int startY) {
	    	Rect start = new Rect(startX,startY, FIELD_WIDTH, FIELD_HEIGHT, Color.GREEN);
			start.draw();
			Label starttext = new Label(Settings.getStartX()+5,Settings.getStartY()+Settings.getFieldHeight()/2,"Start",Color.WHITE);
		       
			starttext.setFontSize(20);
			starttext.draw();
		}
	    
	    
	    // Draws the End
	     static void drawEndPoint(int endX, int endY){
	    	Rect end = new Rect(endX,endY, FIELD_WIDTH, FIELD_HEIGHT, Color.BLUE);
	    	end.draw();
	    	Label endtext = new Label(Settings.getEndX()+5,Settings.getEndY()+Settings.getFieldHeight()/2,"Goal",Color.WHITE);
		       
	    	endtext.setFontSize(20);
	    	endtext.draw();
	    }
	     
	     // Checks if a field is an obstacle and if so, draws it
	 	static void drawObstacles() {
	 		
	 		for (int i = 0; i <= CANVAS_WIDTH; i+= FIELD_WIDTH) {
	 			int x = 0; int y = 0;
	 			for (int j = 0; j <= CANVAS_WIDTH; j+= FIELD_WIDTH) {
	 					x = i/FIELD_WIDTH;
	 					y = j/FIELD_HEIGHT;
	 				if(Settings.getObstacles().isObstacle(x, y)){
	 					Rect obstacle = new Rect(i,j, FIELD_HEIGHT, FIELD_WIDTH,Color.RED);
	 					obstacle.draw();
	 				}
	 			}
	 		}
	 	}


//	 	Draw method to draw the individual elements
		public static void draw() {
			Chessboard.get_board().redraw();
	    	drawObstacles();
	    	drawStartPoint(Settings.getStartX(),Settings.getStartY());
	    	drawEndPoint(Settings.getEndX(),Settings.getEndY());	
	    	
		}

}


	
