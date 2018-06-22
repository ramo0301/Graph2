package current;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Observable;
import javax.swing.SwingUtilities;

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
		if(newSetting.equals("edge")){
			int startX = (int)theModel.getVertexAtIndex(vertexIndex).getX();
			int startY = (int)theModel.getVertexAtIndex(vertexIndex).getY();
			System.out.println("Making line to mouse");
			theModel.makeLineToMouse(startX, startY, x, y);
			
			setEdge();	//if a vertex is already selected, it becomes one of the vertices that the edge connects.
			setChanged();
			notifyObservers();
		} else if(newSetting.equals("default")){
			theModel.disableLineToMouse();
		}
	}
	public void mouseClicked(MouseEvent e){
		System.out.println("Mouse clicked at " + e.getX() + "," + e.getY());
	}
	
	public void mouseEntered(MouseEvent e){
		System.out.println("Mouse entered at " + e.getX() + "," + e.getY()); 	//just a confirmation print

	}

	public void mouseExited(MouseEvent e){
		System.out.println("Mouse exited at " + e.getX() + "," + e.getY()); 	//just a confirmation print

	}

	public void mousePressed(MouseEvent e){
		System.out.println("Mouse pressed at " + e.getX() + "," + e.getY()); 	//just a confirmation print
		
		vertexIndex = theModel.vertexThatContainsPoint(e.getX(),e.getY());

		if(vertexIndex != -1 ){			//if a vertex was pressed
			if(SwingUtilities.isRightMouseButton(e)){
				System.out.println("RIGHT CLICK");
				thePanel.setVertexNameEditable(true);
				thePanel.setVertexName(theModel.getVertexAtIndex(vertexIndex).getName());
			}
			
			theModel.setSelectedVertexIndex(vertexIndex);
			
			x = e.getX();
			y = e.getY();
			
			if(setting.equals("edge")){	//if the mode is to draw an edge, call setEdge
				setEdge();			
			}
			
			setChanged();
			notifyObservers();
		} else {						//if a vertex was not pressed
			theModel.setSelectedVertexIndex(-1);
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
	public void mouseMoved(MouseEvent e) {
		if(setting.equals("edge")){	//if the mode is to draw an edge, call setEdge
			theModel.setLineToMouse(e.getX(),e.getY());
		}
		
	}
	
	
	/* 	INVOKED IN mousePressed WHEN THE SETTING IS: "edge".
	 *  SELECTS THE NEXT TWO VERTICES TO BE THE END OF THE EDGE, THEN SETS SETTING BACK TO DEFAULT 
	 */
	private void setEdge(){
		if(vertexIndex != -1){ 			//only do something if a vertex is selected.
			if(vertex1 == null){		//vertex1 needs yet to be initialized
				vertex1 = theModel.getVertexAtIndex(vertexIndex);
			} else {					//vertex1 already initialized
				vertex2 = theModel.getVertexAtIndex(vertexIndex);
				//now both vertex 1 and 2 are set, so we can add the edge.
				theModel.addEdge(vertex1, vertex2);
				vertex1 = null; vertex2 = null;
				setSetting("default");
			} 
		}

	}



}