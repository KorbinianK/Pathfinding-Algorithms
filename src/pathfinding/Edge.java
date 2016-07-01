package pathfinding;

import main.Helper;

/**
 * <h1>Edge Class for the Thymio project</h1>
 * <h3> Course: Informationssysteme (SS 2016) Universitaet Regensburg</h3>
 * 
 * <div>Dozent: Prof. Dr. Bernd Ludwig</div>
 * 
 * 
 * Edge Object that saves the Start and End Node of the Vertex
 * 
 * 
 * 
 * @version 1.0
 * @author Korbinian Kasberger: korbinian1.kasberger@stud.uni-regensburg.de
 */
public class Edge  {
	private final int id; 
	private final Node source;
	private final Node destination;
	
	
	/**
	 * Constructor
	 * 
	 * @param id
	 * @param source: Node
	 * @param destination: Node
	 */
	public Edge(int id, Node source, Node destination) {
		this.id = id;
		this.source = source;
		this.destination = destination;
		
	}

	/**
	 * Returns the ID
	 * @return id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Returns the Destination Node
	 * @return Node
	 */
	public Node getDestination() {
		return destination;
	}

	/**
	 * Returns the Start Node
	 * @return Node
	 */
	public Node getSource() {
		return source;
	}
	
	
	/**
	 * String describing the path from source to destination (Chess Coordinates)
	 * @return String
	 */
	public String print() {
		return "Go from "
		+source.getChessCoord()
		+" "
		+Helper.isPositionedTo(source, destination)
		+" to "
		+destination.getChessCoord();
	}
	
	
}
