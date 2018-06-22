package current;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.AbstractAction;

public class GraphModel extends Observable {
	
	private ArrayList<GraphVertex> vertexList = new ArrayList<GraphVertex>();
	private ArrayList<GraphEdge> edgeList = new ArrayList<GraphEdge>();
	
	private int selectedVertexIndex = -1;		//-1 means: still uninitialized
	private EdgeLine lineToMouse = null; //used to draw line to mouse when making edge
	
	public void makeLineToMouse(int startX, int startY, int endX, int endY) {
		lineToMouse = new EdgeLine(startX, startY, endX, endY);
	}
	
	public void disableLineToMouse(){
		lineToMouse = null;
	}
	
	public void setLineToMouse(int startX, int startY, int endX, int endY) {
		lineToMouse.setCoordinates(startX, startY, endX, endY);
		setChanged();
		notifyObservers();
	}

	public int getSelectedVertexIndex() {
		return selectedVertexIndex;
	}

	public void setSelectedVertexIndex(int selectedVertexIndex) {
		this.selectedVertexIndex = selectedVertexIndex;
		setChanged();
		notifyObservers();
	}
	
	
	public GraphVertex getVertexAtIndex(int index){
		return vertexList.get(index);
	}
	
	public void setVertexAtIndex(int index, GraphVertex vertex){
		vertexList.add(index, vertex);
	}
	
	/*FIVE DIFFERENT METHODS ADDVERTEX, WE USE NUMBER 1.
	 */
	
	
	/* ADDS A VERTEX TO THE LIST OF VERTICES, THEN NOTIFIES OBSERVERS 
	 * (AT THE MOMENT ONLY THE PANEL), WHICH THEN CALLS REPAINT.
	 */
	public void addVertex(){						//addVertex 1
		/* USE INDEX TO DETERMINE LOCATION, WHICH IS DONE 
		 * EITHER BY GETTING THE SIZE AND ADDING A NEW ELEMENT TO THE END OF vertexList, 
		 * OR, IF A VERTEX HAS BEEN DELETED BEFORE, BY GETTING THE INDEX OF THE FIRST FREE SPOT IN THE LIST.
		 */		
		int index;
		
		if(vertexList.indexOf(null) != -1){	//if there is any hole (null element) in the list
			index = vertexList.indexOf(null);
			vertexList.add(index, new GraphVertex(index));	//here the null element is shifted to index+1
			vertexList.remove(index+1);						//removes the null element
			
		} else {											//if there is NO hole (null element) in the list
			index = vertexList.size();		
			vertexList.add(new GraphVertex(index)); 		//just add to the end of the list.
		}
		
		setChanged();
		notifyObservers();
	}
	
	
	public void addVertex(String inputName){		//addVertex 2
		vertexList.add(new GraphVertex(inputName));
	}
	

	public void addVertex(int width, int height){	//addVertex 3
		vertexList.add(new GraphVertex(width, height));
	}
	
	public void addVertex(int x, int y, int width, int height){ //addVertex 4
		vertexList.add(new GraphVertex(x, y, width, height));
	}
	
	public void addVertex(String string, int x, int y){			//addVertex 5
		vertexList.add(new GraphVertex(string, x, y));		
	}
	
	
	/*TWO DIFFERENT METHODS addEdge, ONE WITH GraphEdge ARGUMENTS,
	 * THE OTHER WITH INDEXES OF THOSE GraphEdges.
	 */
	public void addEdge(GraphVertex  connectedVertex1, GraphVertex  connectedVertex2){
		System.out.println("DRAWING EDGE");
		edgeList.add(new GraphEdge(connectedVertex1, connectedVertex2));
	}
	
	public void addEdge(int connectedVertexIndex1, int  connectedVertexIndex2){
		edgeList.add(new GraphEdge(vertexList.get(connectedVertexIndex1), vertexList.get(connectedVertexIndex2)));
		setChanged();
		notifyObservers();
	}
	

	public void removeVertex(int index){
		
		// FIRST REMOVE ALL EDGES CONNECTED TO THE VERTEX
		for(int i = 0; i<edgeList.size() ; i++){
			if(edgeList.get(i).getConnected1() == vertexList.get(index) 
					|| edgeList.get(i).getConnected2() == vertexList.get(index)){
				removeEdge(i);
				i--;
			}
		}
		
		/*sets the vertex at the index to null, without shifting the rest left
		 * (reason we don't just delete it from the list: we want to leave a spot for
		 * the vertex that is generated next to take, with respect to the seven default locations*/ 
		vertexList.set(index, null);
		selectedVertexIndex = -1;
		
		setChanged();
		notifyObservers();
	}
	
	public void removeEdge(int index){
		edgeList.remove(index);
	}
	
	public void removeEdgeBetween(GraphVertex vertex1, GraphVertex vertex2){
		for(GraphEdge edge : edgeList){
			if( (edge.getConnected1() == vertex1 && edge.getConnected2() == vertex2)
					|| (edge.getConnected1() == vertex2 && edge.getConnected2() == vertex1)){
				removeEdge(edgeList.indexOf(edge));
				break;
			}
		}
	}
	
	
	/* USED TO ACCESS THE ENTIRE LIST, WHICH PaintComponent IN GraphPanel USES.
	 */
	public ArrayList<GraphVertex> getVertexList(){
		return vertexList;
	}
	
	public ArrayList<GraphEdge> getEdgeList(){
		return edgeList;
	}
	
	
	/* METHOD TO DRAW ALL VERTICES. USED IN PaintComponent OF THE PANEL.
	 */
	public void drawAllVertices(Graphics g){
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3));
		
		/* GO THROUGH EVER VERTEX IN THE LIST, GET COORDINATIONS, DIMENSIONS, NAME, AND PRINT IT ALL
		 */
		for(GraphVertex vertex : vertexList){
			if(vertex == null || vertexList.indexOf(vertex) == selectedVertexIndex)
				continue;	/* skip holes in the list (null elements), and leave selected vertex for last.
							 * (so that its not behind any other vertices) */
			drawVertex(vertex, g);
		}
		if(selectedVertexIndex != -1)			//if any vertex is selected
			drawVertex(vertexList.get(selectedVertexIndex), g);
	}
	
	//used in drawAllVertices(above)
	private void drawVertex(GraphVertex vertex, Graphics g){

		int x = (int)vertex.getX(), y = (int)vertex.getY(), width = (int)vertex.getWidth(), height = (int)vertex.getHeight();
		String name = vertex.getName();
		Color fillColor = Color.WHITE, borderColor = Color.BLACK;	//set default colors, writing is also in border color
		
		if(vertexList.indexOf(vertex) == selectedVertexIndex){
			fillColor = Color.GREEN;
			borderColor = Color.DARK_GRAY;
		}
		g.setColor(fillColor);
		g.fillRect(x, y, width, height);
		g.setColor(borderColor);
		g.drawRect(x, y, width, height);
		g.drawString(name, x+width/2-3*name.length(), y+height/2+3);
	}
	
	
	/* METHOD TO DRAW ALL EDGES. USED IN PaintComponent OF THE PANEL.
	 */
	public void drawAllEdges(Graphics g){
		
		for(GraphEdge edge : edgeList){
			drawEdge(g, edge.getConnected1(), edge.getConnected2());
		}
	}
	
	//USED IN drawAllEdges (above)
	public void drawEdge(Graphics g, GraphVertex vertex1, GraphVertex vertex2){
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3));
		
		//following lines need to be edited
		int x1 = (int)vertex1.getX();
		int y1 = (int)vertex1.getY();
		int x2 = (int)vertex2.getX();
		int y2 = (int)vertex2.getY();
		
		g.drawLine(x1, y1, x2, y2);
	}
	
	public void drawLine(Graphics g){
		if(lineToMouse!=null){
			System.out.println("SUPPOSEDLY NOT NULL");
			lineToMouse.drawLine(g);
		}
	}
	
	
	/* METHOD THAT GOES THROUGH THE LIST OF VERTICES TO SEE WHETER ANY OF THEM 
	 * CONTAIN THE POINT X,Y. IF ONE DOES, IT RETURNS THE INDEX OF THAT VERTEX. OTHERWISE IT RETURNS -1
	 */
	public int vertexThatContainsPoint(int x, int y){
		
		for(GraphVertex vertex : vertexList){
			if(vertex == null)
				continue;	//skip  holes in the list (deleted vertices)
			
			if(vertex.contains(x, y)){
				return vertexList.indexOf(vertex);
			}
		}
		return -1;		//returned if none of the vertices contain the point
	}
	
	
	/*THE NEXT TWO METHODS ARE TO CHECK IF THE VERTEX LIST AND EDGE LIST ARE INITIALIZED CORRECTLY
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