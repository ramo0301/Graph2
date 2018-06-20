package current;

import java.awt.BasicStroke;
import java.util.Observable;
import java.util.Observer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JPanel;

public class GraphPanel extends JPanel implements Observer {
	
	/*CONSTRUCTOR, WITH theModel AS ARGUMENT SO THAT WE CAN ACCESS THE ARRAYLISTS vertexList AND graphList.
	 */
	private GraphModel theModel = null;
	
	public GraphPanel(GraphModel theModel){
		super();
		setBackground(Color.LIGHT_GRAY);
		
		/* include the model so we can access it, and make it an observer
		 */
		this.theModel = theModel;
		theModel.addObserver(this);
		
		SelectionController mouse = new SelectionController(this);
	}
	
	public void setModel(GraphModel inputModel){
		// DO NOTHING IF THE MODEL IS ALREADY SET.
		if(inputModel == theModel)
			return;
		
		theModel = inputModel;
		theModel.addObserver(this);
	}
	
	
	/* PRINT STATEMENT JUST FOR CHECKING, REPAINT SO THAT WHEN THE OBSERVABLE IS CHANGED,
	 * (I.E. A NEW VERTEX HAS BEEN ADDED TO THE MODEL), THAT THE CHANGES SHOW IN THE PANEL.
	 */
	public void update(Observable obj, Object arg ){
		System.out.println("graphpanel getting updated.");
		repaint();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		
		theModel.drawAllVertices(g);
		theModel.drawAllEdges(g);
		/* DRAW VERTICES
		 
		for(Rectangle rect : theModel.getVertexList()){
			int x = (int)rect.getX(), y = (int)rect.getY(), width = (int)rect.getWidth(), height = (int)rect.getHeight();
			g.setColor(Color.WHITE);
			g.fillRect(x, y, width, height);
			g.setColor(Color.BLACK);
			g.drawRect(x, y, width, height);
		}
		*/
	}
}
