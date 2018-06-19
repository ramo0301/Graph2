package current;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JPanel;

public class GraphPanel extends JPanel {
	
	/*CONSTRUCTOR, WITH theModel AS ARGUMENT SO THAT WE CAN ACCESS THE ARRAYLISTS vertexList AND graphList.
	 */
	private GraphModel theModel;
	
	public GraphPanel(GraphModel theModel){
		super();
		setBackground(Color.LIGHT_GRAY);
		this.theModel = theModel;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3));
		
		
		/* DRAW VERTICES
		 */
		for(Rectangle rect : theModel.getVertexList()){
			int x = (int)rect.getX(), y = (int)rect.getY(), width = (int)rect.getWidth(), height = (int)rect.getHeight();
			g.setColor(Color.WHITE);
			g.fillRect(x, y, width, height);
			g.setColor(Color.BLACK);
			g.drawRect(x, y, width, height);
		}
	}
}
