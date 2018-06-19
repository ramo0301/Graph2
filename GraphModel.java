package current;

import java.util.ArrayList;

public class GraphModel {
	
	private ArrayList<GraphVertex> vertexList = new ArrayList<GraphVertex>();
	private ArrayList<GraphEdge> edgeList = new ArrayList<GraphEdge>();

	public GraphModel(){ }
	
	
	public void addVertex(){
		/*should have multiple versions with different parameters*/
		vertexList.add(new GraphVertex());
	}
	
	public void addVertex(String inputName){
		vertexList.add(new GraphVertex(inputName));
	}
	
	public void addEdge(GraphVertex  connectedVertex1, GraphVertex  connectedVertex2){
		/*should have multiple versions with different parameters*/
		edgeList.add(new GraphEdge(connectedVertex1, connectedVertex2));
	}
	
	public void addEdge(int connectedVertexIndex1, int  connectedVertexIndex2){
		/*should have multiple versions with different parameters*/
		edgeList.add(new GraphEdge(vertexList.get(connectedVertexIndex1), vertexList.get(connectedVertexIndex2)));
	}
	
	public void removeVertex(int index){
		/*sets the vertex at the index to null, without shifting the rest left*/
		vertexList.set(index, null);
	}
	
	public void removeEdge(int index){
		/*sets the edge at the index to null, without shifting the rest left*/
		edgeList.set(index, null);
	}
	
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
}
