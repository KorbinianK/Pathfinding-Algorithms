package main;
import pathfinding.Node;

/**
 * <h1>Helper Class for the Thymio project</h1>
 * <h3> Course: Informationssysteme (SS 2016) Universitaet Regensburg</h3>
 * 
 * <div>Dozent: Prof. Dr. Bernd Ludwig</div>
 *  
 * Gruppe 6:
 * Bauer Louisa, Durry Jan, Kasberger Korbinian, Mykyttschak Lina 
 * 
 * A few useful outsourced calculations
 * 
 * @version 1.0
 * @author Korbinian Kasberger:korbinian1.kasberger@stud.uni-regensburg.de
 */

public class Helper {
	
	private static final String TOP = Settings.strTop();
	private static final String BOTTOM = Settings.strBottom();
	private static final String LEFT = Settings.strLeft();
	private static final String RIGHT = Settings.strRight();
	
	/**
	 * Calculates the ID from the given Coordinates
	 * 
	 * @param x: The X-Coordinate
	 * @param y: The X-Coordinate
	 * @return Returns the ID as int value
	 */
	public static int calculateID(int x, int y) {
		int[][] arr = new int[Settings.getBoardArrayHeight()][Settings.getBoardArrayWidth()];
		int id = 0;
		for (int i = 0; i < Settings.getBoardArrayHeight();i++) {
			for (int j = 0; j < Settings.getBoardArrayWidth(); j++) {
				
				arr[i][j]= id;
				id++;
			}
		}
		return arr[y][x];
	}
	
	
	/**
	 * Compares two nodes to each other and returns their position to each other
	 * 
	 * @param node1: First node
	 * @param node2: Second node
	 * @return Returns the direction of node2 seen from node1 (e.g. node2 is "north" of ode1)
	 */
	public static String isPositionedTo(Node node1, Node node2){
		
		if(node1.getXCoord() == node2.getXCoord()){
			if(node1.getYCoord() > node2.getYCoord()){
				return LEFT;
			}else{
				return RIGHT;
			}
		}else if(node1.getYCoord() == node2.getYCoord()){
			if(node1.getXCoord() > node2.getXCoord()){
				return TOP;
			}else{
				return BOTTOM;
			}
		}
		
		return "error";
	}
}
