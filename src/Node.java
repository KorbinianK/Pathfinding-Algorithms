
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
//	public void setChessCoord(String chessCoord) {
//		this.chessCoord = chessCoord;
//	}
	public int getYCoord() {
		return yCoord;
	}
//	public void setYCoord(int yCoord) {
//		this.yCoord = yCoord;
//	}
	public int getXCoord() {
		return xCoord;
	}
//	public void setXCoord(int xCoord) {
//		this.xCoord = xCoord;
//	}
	public int getId() {
		return id;
	}
//	public void setId(String id) {
//		this.id = id;
//	}

}
