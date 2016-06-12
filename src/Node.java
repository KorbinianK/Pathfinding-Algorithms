
public class Node {
	
	private int id;
	private int xCoord;
	private int yCoord;
	private String chessCoord;
	
	public Node(int id, int xCoord, int yCoord, String chessCoord){
		this.id = id;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.chessCoord = chessCoord;
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

}
