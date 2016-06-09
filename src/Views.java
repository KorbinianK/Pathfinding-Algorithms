import de.ur.mi.graphics.Color;
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
		}

	    
	    // Draws the End
	     static void drawEndPoint(int endX, int endY){
	    	Rect end = new Rect(endX,endY, FIELD_WIDTH, FIELD_HEIGHT, Color.BLUE);
	    	end.draw();
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


	
