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
	private GraphModel theModel;
	
	public GraphPanel(GraphModel theModel){
		super();
		setBackground(Color.LIGHT_GRAY);
		
		/* include the model so we can access it, and make it an observer
		 */
		this.theModel = theModel;
		theModel.addObserver(this);
	}
	
	
	/*update method to implement observable
	 */
	public void update(Observable obj, Object arg ){
		System.out.println("graphpanel getting updated.");
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		
		theModel.drawAllVertices(g);
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
