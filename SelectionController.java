package grapheditor;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.SwingUtilities;

public class SelectionController implements MouseListener, MouseMotionListener{
	private GraphPanel thePanel = null;
	private GraphModel theModel = null;		//might want to get rid of this, if possible elegantly
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
	}
	
	public void setSetting(String newSetting){
		System.out.println("SETTING MOUSE SETTING TO " + newSetting);
		this.setting = newSetting;
		if(newSetting.equals("edge")){
			System.out.println("VertexIndex: " + vertexIndex);
			int startX = (int)theModel.getVertexAtIndex(vertexIndex).getX();
			int startY = (int)theModel.getVertexAtIndex(vertexIndex).getY();
			System.out.println("Making line to mouse, " + startX + ", " + startY + ", " + x + ", " +y + ", ");
			theModel.makeLineToMouse(startX, startY, x, y);
			
			setEdge();	//a vertex is already selected, and it becomes one of the vertices that the edge connects.
		} else if(newSetting.equals("deleteEdge")){
			deleteEdge();
		} else if(newSetting.equals("default")){
			theModel.disableLineToMouse();
		}
		theModel.setUpdated();
	}
	public void mouseClicked(MouseEvent e){
		//System.out.println("Mouse clicked at " + e.getX() + "," + e.getY());
	}
	
	public void mouseEntered(MouseEvent e){
		//System.out.println("Mouse entered at " + e.getX() + "," + e.getY()); 	//just a confirmation print

	}

	public void mouseExited(MouseEvent e){
		//System.out.println("Mouse exited at " + e.getX() + "," + e.getY()); 	//just a confirmation print

	}

	public void mousePressed(MouseEvent e){
		//System.out.println("Mouse pressed at " + e.getX() + "," + e.getY()); 	//just a confirmation print
		
		vertexIndex = theModel.vertexThatContainsPoint(e.getX(),e.getY());

		if(vertexIndex != -1 ){			//if a vertex was pressed
			if(SwingUtilities.isRightMouseButton(e)){
				System.out.println("RIGHT CLICK");
				thePanel.setVertexNameEditable(true);
			}
			thePanel.setVertexName(theModel.getVertexAtIndex(vertexIndex).getName());
			
			theModel.setSelectedVertexIndex(vertexIndex);
			
			x = e.getX();
			y = e.getY();
			
			if(setting.equals("edge")){	//if the mode is to draw an edge, call setEdge
				setEdge();			
			} else if(setting.equals("deleteEdge")){	//if the mode is to delete an edge, call deleteEdge
				deleteEdge();			
			}
			
			theModel.setUpdated();
			
		} else {									//if a a point that is not a vertex is pressed,
			if(setting.equals("edge")){	
				setSetting("default");				//but currently an edge is being drawn, it stops drawing it
				vertex2 = vertex1 = null;
			}
			theModel.setSelectedVertexIndex(-1);	//deselect the currently selected vertex
		}

	}

	public void mouseReleased(MouseEvent e){
		//System.out.println("Mouse released at " + e.getX() + "," + e.getY());	//just a confirmation print

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
			if(setting.equals("edge")){	//if an edge is being drawn, draw a line from the selected vertex to the mouse
				System.out.println("MOUSEMOVED, " + vertexIndex);
				theModel.setLineToMouse(x, y, e.getX(),e.getY());
			}
			theModel.setUpdated();
		}

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if(setting.equals("edge")){	//if an edge is being drawn, draw a line from the selected vertex to the mouse
			System.out.println("EDGE, " + vertexIndex);
			int newStartX = (int)theModel.getVertexAtIndex(theModel.getSelectedVertexIndex()).getX();
			int newStartY = (int)theModel.getVertexAtIndex(theModel.getSelectedVertexIndex()).getY();
			theModel.setLineToMouse(newStartX, newStartY, e.getX(),e.getY());
		}
		theModel.setUpdated();
	}


	/* 	INVOKED IN mousePressed WHEN THE SETTING IS: "edge".
	 *  SELECTS THE NEXT TWO VERTICES TO BE THE END OF THE EDGE, THEN SETS SETTING BACK TO DEFAULT 
	 */
	private void setEdge(){
		if(vertexIndex != -1){ 			//only do something if a vertex is selected.
			if(vertex1 == null){		//vertex1 needs yet to be initialized
				vertex1 = theModel.getVertexAtIndex(vertexIndex);
			} else if(theModel.getVertexAtIndex(vertexIndex)!=vertex1){
				vertex2 = theModel.getVertexAtIndex(vertexIndex);
				//now both vertex 1 and 2 are set, so we can add the edge.
				theModel.addEdge(vertex1, vertex2);
				vertex1 = null; vertex2 = null;
				setSetting("default");
			} 
		}

	}
	
	private void deleteEdge(){
		System.out.println("DELETING EDGE (SUPPOSEDLY)");
		if(vertexIndex != -1){ 			//only do something if a vertex is selected.
			if(vertex1 == null){		//vertex1 needs yet to be initialized
				vertex1 = theModel.getVertexAtIndex(vertexIndex);
			} else if(theModel.getVertexAtIndex(vertexIndex)!=vertex1){
				vertex2 = theModel.getVertexAtIndex(vertexIndex);
				//now both vertex 1 and 2 are set, so we can add the edge.
				theModel.removeEdgeBetween(vertex1, vertex2);
				vertex1 = null; vertex2 = null;
				setSetting("default");
			} 
		}

	}



}