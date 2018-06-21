package current;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Observable;

public class SelectionController extends Observable implements MouseListener, MouseMotionListener{
	private GraphPanel thePanel = null;
	private GraphModel theModel = null;		//might want to get rid of this, if possible elegantly/
	private GraphVertex vertex1 = null, vertex2 = null;
	int x=-1, y=-1, vertexIndex = -1 ;
	private String setting = "default";
	
	
	/* CONSTRUCTOR, SETS THE PANEL AND THE MODEL. 
	 */
	public SelectionController( GraphPanel inputPanel, GraphModel inputModel){
		this.thePanel = inputPanel;
		this.theModel = inputModel;
		thePanel.addMouseListener(this);
		thePanel.addMouseMotionListener(this);
		addObserver(thePanel);
	}
	
	public void setSetting(String newSetting){
		System.out.println("SETTING MOUSE SETTING TO " + newSetting);
		this.setting = newSetting;
	}
	public void mouseClicked(MouseEvent e){
		System.out.println("Mouse clicked at " + e.getX() + "," + e.getY()); //just a confirmation print
		
		//index of the vertex that contains the point x,y, or -1 if no vertex contains the point.
		int vertexIndex = theModel.vertexThatContainsPoint(e.getX(),e.getY());

		if(vertexIndex != -1 ){			//if a vertex contains the point
			theModel.setSelectedVertexIndex(vertexIndex);

			setChanged();
			notifyObservers();
		}
	}
	
	/*
	public void drawEdge(Graphics g){
		System.out.println("AT START OF DRAWEDGE");
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3));
		
		//following lines need to be edited
		int x1 = (int)vertex1.getX();
		int y1 = (int)vertex1.getY();
		int x2 = (int)vertex2.getX();
		int y2 = (int)vertex2.getY();
		
		System.out.println("Drawing line between " +x1 +","+y2 + " and " +x2 +","+y2);
		g.drawLine(x1, y1, x2, y2);
		//setChanged();
		//notifyObservers("null");
	}
	*/
	public void mouseEntered(MouseEvent e){
		System.out.println("Mouse entered at " + e.getX() + "," + e.getY()); 	//just a confirmation print

	}

	public void mouseExited(MouseEvent e){
		System.out.println("Mouse exited at " + e.getX() + "," + e.getY()); 	//just a confirmation print

	}

	public void mousePressed(MouseEvent e){
		System.out.println("Mouse pressed at " + e.getX() + "," + e.getY()); 	//just a confirmation print
		
		vertexIndex = theModel.vertexThatContainsPoint(e.getX(),e.getY());

		if(vertexIndex != -1 ){			//if a vertex contains the point
			theModel.setSelectedVertexIndex(vertexIndex);
			
			if(setting == "edge"){	
				System.out.println("PRESSED vertex1 = " + vertex1 + ", vertex2 = " + vertex2);
				setEdge();			
			}
			
			System.out.println("You selected the Vertex with index " + vertexIndex);
			x = e.getX();
			y = e.getY();
			setChanged();
			notifyObservers();
		}

	}

	public void mouseReleased(MouseEvent e){
		System.out.println("Mouse released at " + e.getX() + "," + e.getY());	//just a confirmation print

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		System.out.println("Mouse being dragged");
		vertexIndex = theModel.getSelectedVertexIndex();
		if(vertexIndex != -1){				/*if, at the moment, a vertex is selected
		* (as determined in mousePressed), then change the location of that vertex. Otherwise, do nothing.*/
			GraphVertex currentVertex = theModel.getVertexAtIndex(vertexIndex);
			currentVertex.setLocation((int)currentVertex.getX()+e.getX()-x,(int)currentVertex.getY()+e.getY()-y);
			x = e.getX();
			y = e.getY();
			setChanged();
			notifyObservers();
		}

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	/* 	INVOKED IN mousePressed WHEN THE SETTING IS: "edge".
	 *  SELECTS THE NEXT TWO VERTICES TO BE THE END OF THE EDGE, THEN SETS SETTING BACK TO DEFAULT 
	 */
	private void setEdge(){
		System.out.println("PRINTEDGE");
		if(vertex1 == null){
			vertex1 = theModel.getVertexAtIndex(vertexIndex);
			System.out.println("DRAWING LINE BETWEEN VERTEX " + vertex1);
		} else {
			vertex2 = theModel.getVertexAtIndex(vertexIndex);
			System.out.println("AND VERTEX " + vertex2);
			//now both vertex 1 and 2 are set, so we can add the edge.
			theModel.addEdge(vertex1, vertex2);
			vertex1 = null; vertex2 = null;
			setSetting("default");
		}
	}
	


}