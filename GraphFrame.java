package current;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


public class GraphFrame extends JFrame implements Observer {
	
	public static final int FRAME_WIDTH = 1000;
	public static final int FRAME_HEIGHT = 500;
	
	private JMenuItem menuDeleteVertex ;//instance variables so that they can...
	private JMenuItem menuAddEdge;		//...be accessed by update (to dis/enable them)
	private JMenuItem menuDeleteEdge;
	
	private GraphModel theModel;
	private GraphPanel thePanel;
	
	public GraphFrame(GraphModel theModel){
		
		super("Graph Frame");
		
		this.theModel = theModel;
		this.thePanel = new GraphPanel(theModel);
		
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout() );
		setBackground(Color.DARK_GRAY);
		
		theModel.addObserver(this);
		
		/* INITIALIZE MENU
		 */
		JMenu theMenu = new JMenu("Menu");
		JMenuItem menuAddVertex = new JMenuItem("Add Vertex");
		JMenuItem menuAddFrame = new JMenuItem("Add Frame");
		menuDeleteVertex = new JMenuItem("Delete Selected Vertex");
		menuAddEdge = new JMenuItem("Add Edge");
		menuDeleteEdge = new JMenuItem("Delete Edge");
		
		JMenuBar bar = new JMenuBar();
		
		theMenu.add(menuAddVertex);
		theMenu.add(menuDeleteVertex);
		theMenu.add(menuAddEdge);
		theMenu.add(menuDeleteEdge);
		theMenu.add(menuAddFrame);
		bar.add(theMenu);
		setJMenuBar(bar);
		
		menuDeleteVertex.setEnabled(false);
		menuAddEdge.setEnabled(false);
		menuDeleteEdge.setEnabled(false);
		
		
		/* INITIALIZE ACTIONS AND SET TO MENU ITEMS
		 */
		Action addVertex = new AddVertexAction();
		Action addFrame = new AddFrameAction();
		menuAddVertex.setAction(addVertex);
		menuAddFrame.setAction(addFrame);
		
		// RELEVANT PANEL IN THE CENTER, BLANK PANELS AS BORDERS
		add(new EmptyPanel() , BorderLayout.NORTH);
		add(new EmptyPanel() , BorderLayout.SOUTH);
		add(new EmptyPanel() , BorderLayout.EAST);
		add(new EmptyPanel() , BorderLayout.WEST);
		add(thePanel , BorderLayout.CENTER);
	}
	
	public void newFrame(){
		GraphFrame copyFrame = new GraphFrame(theModel);
		copyFrame.setVisible(true);
		
	}
	
	
	public GraphModel getModel(){
		return theModel;
	}

	
	/* INNNER CLASS USED TO GENERATE EMPTY PANELS
	 */
	public class EmptyPanel extends JPanel {
		public EmptyPanel(){
			super();
			setBackground(Color.DARK_GRAY);
		}
	}
	
	
	private class AddVertexAction extends AbstractAction {
		
		public AddVertexAction(){
			super("Add Vertex");
		}
		
		public void actionPerformed(ActionEvent e) {
			theModel.addVertex();		//adds a vertex to the model
		}
	}
	
	
	private class DeleteVertexAction extends AbstractAction {
		
		public DeleteVertexAction(){
			super("Delete Selected Vertex");
		}
		
		public void actionPerformed(ActionEvent e) {
			if(theModel.getSelectedVertexIndex() != -1)	//if a vertex is selected, delete it
				theModel.removeVertex(theModel.getSelectedVertexIndex());
				//^Removes the vertex with from the list, passing its index as argument
		}
	}
	
	
	private class AddEdgeAction extends AbstractAction {
		
		public AddEdgeAction(){
			super("Add Edge");
		}
		
		/* SET THE MOUSELISTENER TO THE SETTING THAT MAKES IT SO THAT
		 * WHEN THE NEXT TWO VERTICES HAVE BEEN SELECTED, AN EDGE WILL BE DRAWN BETWEEN THEM.
		 */
		public void actionPerformed(ActionEvent e) {
			thePanel.setMouseSetting("edge");
		}
		
	}
	
	
	private class DeleteEdgeAction extends AbstractAction {

		public DeleteEdgeAction(){
			super("Delete Edge");
		}

		/* SET THE MOUSELISTENER TO THE SETTING THAT MAKES IT SO THAT
		 * WHEN THE NEXT TWO VERTICES HAVE BEEN SELECTED, AN EDGE WILL BE DRAWN BETWEEN THEM.
		 */
		public void actionPerformed(ActionEvent e) {
			thePanel.setMouseSetting("deleteEdge");
		}

	}
	
	
	private class AddFrameAction extends AbstractAction {

		public AddFrameAction(){
			super("Add Frame");
		}

		/* SET THE MOUSELISTENER TO THE SETTING THAT MAKES IT SO THAT
		 * WHEN THE NEXT TWO VERTICES HAVE BEEN SELECTED, AN EDGE WILL BE DRAWN BETWEEN THEM.
		 */
		public void actionPerformed(ActionEvent e) {
			newFrame();
		}

	}


	@Override
	public void update(Observable arg0, Object arg1) {
		if(theModel.getSelectedVertexIndex()!=-1){	//if a vertex is selected
			
			menuDeleteVertex.setEnabled(true);
			menuAddEdge.setEnabled(true);
			menuDeleteEdge.setEnabled(true);
			
			menuDeleteVertex.setAction(new DeleteVertexAction());
			menuAddEdge.setAction(new AddEdgeAction());
			menuDeleteEdge.setAction(new DeleteEdgeAction());
		} else {									//if no vertex is selected
			menuDeleteVertex.setEnabled(false);
			menuAddEdge.setEnabled(false);
		}
		
	}


}