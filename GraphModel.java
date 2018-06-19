package current;

import java.util.ArrayList;
import java.util.Observable;

public class GraphModel extends Observable {
	
	private ArrayList<GraphVertex> vertexList = new ArrayList<GraphVertex>();
	private ArrayList<GraphEdge> edgeList = new ArrayList<GraphEdge>();

	//EMPTY CONSTRUCTOR GRAPHMODEL
	public GraphModel(){ }
	
	
	/*FIVE DIFFERENT METHODS ADDVERTEX, EACH CORRESPONDING TO A DIFFERENT CONSTRUCTOR OF GraphVertex.
	 */
	public void addVertex(){
		vertexList.add(new GraphVertex());
	}
	
	public void addVertex(String inputName){
		vertexList.add(new GraphVertex(inputName));
	}
	
	public void addVertex(int width, int height){
		vertexList.add(new GraphVertex(width, height));
		setChanged();
		notifyObservers();
	}
	
	public void addVertex(int x, int y, int width, int height){
		vertexList.add(new GraphVertex(x, y, width, height));
	}
	
	public void addVertex(String string, int x, int y){
		vertexList.add(new GraphVertex(string, x, y));		
	}
	
	
	/*TWO DIFFERENT METHODS ADDVERTEX, ONE WITH GraphEdge ARGUMENTS,
	 * THE OTHER WITH INDEXES OF THOSE GraphEdges.
	 */
	public void addEdge(GraphVertex  connectedVertex1, GraphVertex  connectedVertex2){
		edgeList.add(new GraphEdge(connectedVertex1, connectedVertex2));
	}
	
	public void addEdge(int connectedVertexIndex1, int  connectedVertexIndex2){
		edgeList.add(new GraphEdge(vertexList.get(connectedVertexIndex1), vertexList.get(connectedVertexIndex2)));
	}
	
	
	/* REMOVE VERTEX OR EDGE
	*/
	public void removeVertex(int index){
		//sets the vertex at the index to null, without shifting the rest left
		vertexList.set(index, null);
	}
	
	public void removeEdge(int index){
		//sets the edge at the index to null, without shifting the rest left
		edgeList.set(index, null);
	}
	
	
	/* USED TO ACCESS THE ENTIRE LIST, WHICH PaintComponent IN GraphPanel USES.
	 */
	public ArrayList<GraphVertex> getVertexList(){
		return vertexList;
	}
	
	public ArrayList<GraphEdge> getEdgeList(){
		return edgeList;
	}
	
	/*THE NEXT LINES ARE TO CHECK IF THE VERTEX LIST AND EDGE LIST ARE INITIALIZED CORRECTLY
	 * 
	 * 
	 * 
	public void printVertexList(){
		System.out.println("PRINTING VERTEX LIST");
		int i = 0;
		for(GraphVertex vertex : vertexList){
			System.out.println("VERTEX " + i + ": " + vertex.toString() );
			i++;
		}
		System.out.println();
	}
	
	public void printEdgeList(){
		System.out.println("PRINTING EDGE LIST");
		int i = 0;
		for(GraphEdge edge : edgeList){
			System.out.println("EDGE " + i + ": ");
			System.out.println(edge.toString());
			i++;
		}
		System.out.println();
	}
	*/
}
