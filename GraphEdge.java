package current;

public class GraphEdge {
	private GraphVertex connected1 = null;
	private GraphVertex connected2 = null;
	
	
	//CONSTRUCTOR
	public GraphEdge(GraphVertex inputVertex1, GraphVertex inputVertex2) {
		connected1 = inputVertex1;
		connected2 = inputVertex2;
	}
	
	
	/*
	 * 2 SETTERS, 2 GETTERS */
	
	public GraphVertex getConnected1() {
		return connected1;
	}
	
	public void setConnected1(GraphVertex connected1) {
		this.connected1 = connected1;
	}
	
	public GraphVertex getConnected2() {
		return connected2;
	}
	
	public void setConnected2(GraphVertex connected2) {
		this.connected2 = connected2;
	}
	
	@Override
	public String toString(){
		String returnString = "connected1: " + connected1.getName();
		returnString += "\nconnected2: " + connected2.getName();
		return returnString;
	}


}