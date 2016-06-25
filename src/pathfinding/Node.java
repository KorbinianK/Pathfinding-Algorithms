package pathfinding;

public class Node {
	
	private int id;
	private int xCoord;
	private int yCoord;
	private String chessCoord;
	private int thymioOrientation;
	private boolean isObstacle;
	
	public Node(int id, int xCoord, int yCoord, String chessCoord, int thymioOrientation){
		this.id = id;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.chessCoord = chessCoord;
		this.isObstacle = false;
	}
	
	public void setOrientation(int thymioOrientation){
		this.thymioOrientation = thymioOrientation;
	}
	public int getOrientation(){
		return thymioOrientation;
	}
	public String getChessCoord() {
		return chessCoord;
	}
	
	public int getYCoord() {
		return yCoord;
	}

	public int getXCoord() {
		return xCoord;
	}

	
	public void setObstacle(boolean b){
		isObstacle = true;
	}
	public boolean isObstacle(){
		return isObstacle;
	}
	public int getId() {
		return id;
	}

	public String toCoordString() {
		return yCoord+","+xCoord;
	}

}
