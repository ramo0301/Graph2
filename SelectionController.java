package current;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;

public class SelectionController extends Observable implements MouseListener{
	private GraphPanel thePanel = null;
	private GraphModel theModel = null;		//might want to get rid of this, if possible elegantly/
	
	public SelectionController(GraphPanel inputPanel, GraphModel inputModel){
		this.thePanel = inputPanel;
		this.theModel = inputModel;
		thePanel.addMouseListener(this);
		addObserver(thePanel);
	}

	public void mouseClicked(MouseEvent e){
		System.out.println("Mouse clicked at " + e.getX() + "," + e.getY());
		//index of the vertex that contains the point x,y, or -1 if no vertex contains the point.
		int vertexIndex = theModel.vertexThatContainsPoint(e.getX(),e.getY());
		
		if(vertexIndex != -1 ){			//if a vertex contains the point
			System.out.println("CLICKED VERTEX " + vertexIndex);
			theModel.setSelectedVertexIndex(vertexIndex);
			setChanged();
			notifyObservers();
		}
	}

	public void mouseEntered(MouseEvent e){
		System.out.println("Mouse entered at " + e.getX() + "," + e.getY());

	}

	public void mouseExited(MouseEvent e){
		System.out.println("Mouse exited at " + e.getX() + "," + e.getY());

	}

	public void mousePressed(MouseEvent e){
		System.out.println("Mouse pressed at " + e.getX() + "," + e.getY());

	}

	public void mouseReleased(MouseEvent e){
		System.out.println("Mouse released at " + e.getX() + "," + e.getY());

	}


}
