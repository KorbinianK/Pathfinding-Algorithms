package main;

import pathfinding.Node;

public class Helper {
	
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
	
	
	public static String isPositionedTo(Node node1, Node node2){
		
		if(node1.getXCoord() == node2.getXCoord()){
			if(node1.getYCoord() > node2.getYCoord()){
				return "left";
			}else{
				return "right";
			}
		}else if(node1.getYCoord() == node2.getYCoord()){
			if(node1.getXCoord() > node2.getXCoord()){
				return "top";
			}else{
				return "bottom";
			}
		}
		
		return "error";
	}
}
