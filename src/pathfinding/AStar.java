package pathfinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import main.Settings;
import map.Chessboard;

/*
 * loop
    current = node in OPEN with the lowest f_cost
    remove current from OPEN
    add current to CLOSED

    if current is the target node //path has been found
        return

    foreach neighbour of the current node
        if neighbour is not traversable or neighbour is in CLOSED
            skip to the next neighbour

        if new path to neighbour is shorter OR neighbour is not in OPEN
            set f_cost of neighbour
            set parent of neighbour to current 
            if neighbour is not in OPEN
                add neighbour to OPEN
 */

public class AStar {
	
	private static List<Node> open = new ArrayList<Node>();
	private static List<Node> closed = new ArrayList<Node>();
	private static List<Node> map = Settings.getBoardNodes();
	private static Node start  = Settings.getStartNode();
	private static Node end  = Settings.getEndNode();
	private static Node current;
	private static Chessboard board = Settings.getBoard();
	
	public static void main(String[] args) {
		while(true){
			if(current == end){
				System.out.println("done");
				break;
			}
			
			HashMap<String, Node> neighbours = board.getNeighbourNodes(current);
			
		}
		
	}
}
