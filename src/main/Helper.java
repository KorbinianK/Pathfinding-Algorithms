package main;

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
}
