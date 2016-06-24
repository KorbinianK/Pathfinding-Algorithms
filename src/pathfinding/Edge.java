package pathfinding;


public class Edge  {
	private final int id; 
	private final Node source;
	private final Node destination;
	private final int cost; 
	
	public Edge(int id, Node source, Node destination, int cost) {
		this.id = id;
		this.source = source;
		this.destination = destination;
		this.cost = cost;
	}
	
	public int getId() {
		return id;
	}
	public Node getDestination() {
		return destination;
	}

	public Node getSource() {
		return source;
	}
	public int getCost() {
		return cost;
	}
	
	@Override
	public String toString() {
		return source + " " + destination;
	}
	
	
}
