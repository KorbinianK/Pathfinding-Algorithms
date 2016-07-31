package thymio;

import main.Settings;

/**
 * <h1> Thymio Controller Class for the Thymio project</h1>
 * <h3> Course: Informationssysteme (SS 2016) Universitaet Regensburg</h3>
 * 
 * <div>Dozent: Prof. Dr. Bernd Ludwig</div>
 *   
 * Gruppe 6:
 * Bauer Louisa, Durry Jan, Kasberger Korbinian, Mykyttschak Lina 
 * 
 * Handles the Movement of the "Fake-Thymio"
 * 
 * 
 * @version 1.0
 * @author Korbinian Kasberger: korbinian1.kasberger@stud.uni-regensburg.de
 */

public class ThymioController {
	
	private static final String TOP = "north";
	private static final String BOTTOM ="south";
	private static final String LEFT = "west";
	private static final String RIGHT = "east";
	private static final int SPEED_AHEAD = Settings.getSpeedAhead();

	 public static Thymio t;
	private int CURRENT_ROTATION;
	private boolean disableDrive = true;

	public ThymioController(){
		if(Settings.useThymio()){
			t = new Thymio("192.168.10.1");
//	    	t.setSpeed("ahead", 800);
//	    	t.setSpeed("rotation", 300);
		}
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
				
//				delayedDrive();
				
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
//				delayedDrive();
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
						t.rotate(180);
					}
				}
//				delayedDrive();
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
//				delayedDrive();
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
				if(!disableDrive){
//					t.drive(SPEED_AHEAD, SPEED_AHEAD);
					t.move();
				}
			
		}
		
		

		/**
		 *  Moves Thymio right
		 */
		public void moveRight() {
		
				setOrientation(RIGHT);
				if(!disableDrive){
//					t.drive(SPEED_AHEAD, SPEED_AHEAD);
					t.move();

				}
		}
		
		/**
		 *  Moves Thymio up
		 */
		public void moveUp() {
			
				setOrientation(TOP);
				if(!disableDrive){
//					t.drive(SPEED_AHEAD, SPEED_AHEAD);
					t.move();

				}
		}



		/**
		 *  Moves Thymio down
		 */
		public void moveDown() {
			setOrientation(BOTTOM);
			if(!disableDrive ){
//				t.drive(SPEED_AHEAD, SPEED_AHEAD);
				t.move();

			}
		}
	
	
	
		/**
		 * Move Method to test without chess field
		 */
		@SuppressWarnings("unused")
		private static void delayedDrive() {
			t.drive(SPEED_AHEAD, SPEED_AHEAD);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			t.stop();
		}

	
	
	
	
	
	
	
}
