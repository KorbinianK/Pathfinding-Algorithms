
public class Node {
	
	private int id;
	private int xCoord;
	private int yCoord;
	private String chessCoord;
	private int thymioOrientation;
	
	public Node(int id, int xCoord, int yCoord, String chessCoord, int thymioOrientation){
		this.id = id;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.chessCoord = chessCoord;
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


	public int getId() {
		return id;
	}

	public String toCoordString() {
		// TODO Auto-generated method stub
		return yCoord+","+xCoord;
	}

}
