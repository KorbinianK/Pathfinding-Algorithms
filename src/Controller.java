import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Label;
import de.ur.mi.graphicsapp.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Controller extends GraphicsApp implements KeyListener {

	// Fixed, don't change, adjustments in Settings Class
		private static final int FIELD_HEIGHT = Settings.getFieldHeight();
		private static final int CANVAS_HEIGHT = Settings.getCanvasHeight();
		private static final int CANVAS_WIDTH = Settings.getCanvasWidth();
		private static int THYMIO_STARTFIELD_X = Settings.getStartX();
		private static int THYMIO_STARTFIELD_Y = Settings.getStartY();;
		public static Thymio thymio ; 
	   
	// Basic setup
    public void setup() {
      	size(CANVAS_HEIGHT+FIELD_HEIGHT,CANVAS_WIDTH+FIELD_HEIGHT);   	 	
      	thymio = new Thymio(THYMIO_STARTFIELD_X, THYMIO_STARTFIELD_Y, FIELD_HEIGHT, FIELD_HEIGHT, Settings.getThymioImg());
    }
    
    
   // Draws the Views 
	public void draw(){
		Views.draw();
		thymio.draw();
    }
    
	
	
	 
//	Very basic test to automate Thymios movement. Moves Thymio to a given coordinate. See @keyPressed case't' to see an example. 
	
	private void moveToPos(Thymio t, int x, int y){
		int current_x = (int) thymio.getXPosAsField();
		int current_y = (int) thymio.getYPosAsField();
		
		if(current_y < y){
			while(current_y < y){
				int old = current_y;
				thymio.moveDown();
				current_y = (int) thymio.getYPosAsField();
				if(current_y == old){
					break;
				}
			}
		}else if(current_y > y){
			while(current_y > y){
				int old = current_y;
				thymio.moveUp();
				current_y = (int) thymio.getYPosAsField();
				if(current_y == old){
					break;
				}
			}
		}
		if(current_x > x){
			while(current_x > x){
				int old = current_x;
				thymio.moveLeft();
				current_x = (int) thymio.getXPosAsField();
				if(current_x == old){
					break;
				}
			}
		}else if(current_x < x){
			while(current_x < x){
				int old = current_x;
				thymio.moveRight();
				current_x = (int) thymio.getXPosAsField();
				if(current_x == old){
					break;
				}
			}
		}
	}
    
    // Handles the keypresses to move Thymio accordingly
	@Override
	public void keyPressed(KeyEvent e) {
    	
    	switch (e.getKeyChar()) {
		case 'd':
			thymio.moveRight();		
			break;
		case 'w':
			thymio.moveUp();			
			break;
		case 'a':
			thymio.moveLeft();			
			break;
		case 's':
			thymio.moveDown();
			break;
		case 'g':
			moveToPos(thymio,Settings.getThymioStartField_X(),Settings.getThymioStartField_Y());
			break;
		case 't':
			moveToPos(thymio,Settings.getThymioEndField_X(),Settings.getThymioEndField_Y());
			break;
		case '1':
			int cost = Settings.getBoard().calculateAirDistance(Settings.getThymioStartField_X(), Settings.getThymioStartField_Y(), Settings.getThymioEndField_X(), Settings.getThymioEndField_Y());
			System.out.println(cost);
			break;
		case 'c':
//			Dijkstra.print();
//			Dijkstra.calculateDistance(Settings.getThymioStartField_X(), Settings.getThymioStartField_Y(), Settings.getThymioEndField_X(), Settings.getThymioEndField_Y());
			Dijkstra.calcCostToSurrounding(thymio.getXPosAsField(),thymio.getYPosAsField());
    	}
    	Views.draw();
//		System.out.println("Left Neighbour: ["+Settings.getBoard().fieldHasLeftNeighbour(thymio.getXPosAsField(),thymio.getYPosAsField())+"]");
//		System.out.println("Right Neighbour: ["+Settings.getBoard().fieldHasRightNeighbour(thymio.getXPosAsField(),thymio.getYPosAsField())+"]");
//		System.out.println("Top Neighbour: ["+Settings.getBoard().fieldHasTopNeighbour(thymio.getXPosAsField(),thymio.getYPosAsField())+"]");
//		System.out.println("Bottom Neighbour: ["+Settings.getBoard().fieldHasBottomNeighbour(thymio.getXPosAsField(),thymio.getYPosAsField())+"]");

//		System.out.println("Position Chess: ["+Settings.getBoard().getCoordString(thymio.getXPosAsField(),thymio.getYPosAsField())+"]");
//		System.out.println("Coordinates: ["+Settings.getBoard().getCoord(thymio.getXPosAsField(),thymio.getYPosAsField())+"]");
	
    }

}
