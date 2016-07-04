package main;

import iw.ur.thymio.Thymio.Thymio;


public class ThymioController {
	
	private static final String TOP = "north";
	private static final String BOTTOM ="south";
	private static final String LEFT = "west";
	private static final String RIGHT = "east";
	private static final int SPEED_AHEAD = Settings.getSpeedAhead();

	 public static Thymio t;
	private int CURRENT_ROTATION;

	public ThymioController(){
		t = new Thymio("192.168.10.1");
    	t.setSpeed("ahead", 1000);
    	
	}

	public Thymio getThymio() {
		return t;
	}
	
	
	
	/**
	 * Sets the Orientation of Thymio
	 * 
	 * 	North / Up = 0
	 *	East / Right = 90
	 *	South / Down = 180
	 *	West / Left = 270
	 *
	 * @param movement
	 */
		public void setOrientation(String movement) {

			switch (movement) {
			case LEFT:
				if(CURRENT_ROTATION == 0){
					if(Settings.useThymio()){
						t.rotate(-90);
					}
					
				}else if(CURRENT_ROTATION == 90){
					if(Settings.useThymio()){
						t.rotate(180);
					}
					
				}else if(CURRENT_ROTATION == 180){
					if(Settings.useThymio()){
						t.rotate(90);
					}
				}
				CURRENT_ROTATION = 270 ;
				
				break;
			case RIGHT:
				if(CURRENT_ROTATION == 0){
					if(Settings.useThymio()){
						t.rotate(90);
					}
				}else if(CURRENT_ROTATION == 270){
					if(Settings.useThymio()){
						t.rotate(180);
					}
				}else if(CURRENT_ROTATION == 180){
					if(Settings.useThymio()){
						t.rotate(-90);
					}
				}
				CURRENT_ROTATION = 90;
				break;
			case "north":
				if(CURRENT_ROTATION == 90){
					if(Settings.useThymio()){
						t.rotate(-90);
					}
				}else if(CURRENT_ROTATION == 270){
					if(Settings.useThymio()){
						t.rotate(90);
					}
				}else if(CURRENT_ROTATION == 180){
					if(Settings.useThymio()){
						t.rotate(270);
					}
				}
				CURRENT_ROTATION = 0;
				break;
			case "south":
				if(CURRENT_ROTATION == 90){
					if(Settings.useThymio()){
						t.rotate(90);
					}
				}else if(CURRENT_ROTATION == 270){
					if(Settings.useThymio()){
						t.rotate(-90);
					}
				}else if(CURRENT_ROTATION == 0){
					if(Settings.useThymio()){
						t.rotate(180);
					}
				}
				CURRENT_ROTATION = 180;
				break;
			default:
				break;
			}
			
		}
		
		
		/**
		 * Mpves Thymio
		 * @param direction
		 */
		public void move(String direction){
			
			switch (direction) {
			case TOP:
				moveUp();
				break;
			case BOTTOM:
				moveDown();
				break;
			case LEFT:
				moveLeft();
				break;
			case RIGHT:
				moveRight();
				break;
			default:
				break;
			}
		}
		
		/**
		 *  Moves Thymio left
		 */
		public void moveLeft() {	
			
				setOrientation(LEFT);	
				t.drive(SPEED_AHEAD, SPEED_AHEAD);
		}
		
		

		/**
		 *  Moves Thymio right
		 */
		public void moveRight() {
		
				setOrientation(RIGHT);
				t.drive(SPEED_AHEAD, SPEED_AHEAD);
		}
		
		/**
		 *  Moves Thymio up
		 */
		public void moveUp() {
			
				setOrientation(TOP);
				t.drive(SPEED_AHEAD, SPEED_AHEAD);
		}



		/**
		 *  Moves Thymio down
		 */
		public void moveDown() {
			setOrientation(BOTTOM);
			t.drive(SPEED_AHEAD, SPEED_AHEAD);
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
