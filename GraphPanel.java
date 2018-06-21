package current;

import java.awt.BasicStroke;
import java.util.Observable;
import java.util.Observer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class GraphPanel extends JPanel implements Observer {
	
	private static final int NUMBER_OF_CHAR = 20;
	
	/*CONSTRUCTOR, WITH theModel AS ARGUMENT SO THAT WE CAN ACCESS THE ARRAYLISTS vertexList AND graphList.
	 */
	private GraphModel theModel = null;
	private SelectionController mouse;
	
	public GraphPanel(GraphModel theModel){
		super();
		setBackground(Color.LIGHT_GRAY);
		setLayout(new BorderLayout());
		
		JTextField vertexName = new JTextField(NUMBER_OF_CHAR);
		add(vertexName, BorderLayout.SOUTH);
		
		/* include the model so we can access it, and make it an observer. Model is also input to mouse.
		 */
		this.theModel = theModel;
		theModel.addObserver(this);
		mouse = new SelectionController(this, theModel);
	}
	
	
	/* SETTER FOR MODEL
	 */
	public void setModel(GraphModel inputModel){
		// DO NOTHING IF THE MODEL IS ALREADY SET.
		if(inputModel == theModel)
			return;
		
		theModel = inputModel;
		theModel.addObserver(this);
	}


	public void setMouseSetting(String newSetting) {
		this.mouse.setSetting(newSetting);
	}

	/* PRINT STATEMENT JUST FOR CHECKING, REPAINT SO THAT WHEN THE OBSERVABLE IS CHANGED,
	 * (I.E. A NEW VERTEX OR EDGE HAS BEEN ADDED TO THE MODEL), THAT THE CHANGES SHOW IN THE PANEL.
	 */
	public void update(Observable obj, Object arg ){
		System.out.println("UPDATING GRAPH PANEL");
		repaint();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		//draw all edges
		theModel.drawAllEdges(g);
		//draw all vertices
		theModel.drawAllVertices(g);
	}
}