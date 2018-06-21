package current;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class GraphFrame extends JFrame {
	
	public static final int FRAME_WIDTH = 1000;
	public static final int FRAME_HEIGHT = 500;
	public static final int	NUMBER_OF_CHAR = 20;
	
	GraphModel theModel = new GraphModel();
	GraphPanel thePanel = new GraphPanel(theModel);
	
	public GraphFrame(){
		
		super("Graph Frame");
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout() );
		setBackground(Color.DARK_GRAY);
		
		/* INITIALIZE MENU
		 */
		JMenu theMenu = new JMenu("Menu");
		JMenuItem menuAddVertex = new JMenuItem("Add Vertex");
		JMenuItem menuDeleteVertex = new JMenuItem("Delete Selected Vertex");
		JMenuItem menuAddEdge = new JMenuItem("Add Edge");
		JMenuBar bar = new JMenuBar();
		
		theMenu.add(menuAddVertex);
		theMenu.add(menuAddEdge);
		theMenu.add(menuDeleteVertex);
		bar.add(theMenu);
		setJMenuBar(bar);
		
		
		/* INITIALIZE ACTIONS AND SET TO MENU ITEMS
		 */
		Action addVertex = new AddVertexAction();
		Action deleteVertex = new DeleteVertexAction();
		Action addEdge = new AddEdgeAction();
		menuAddVertex.setAction(addVertex);
		menuDeleteVertex.setAction(deleteVertex);
		menuAddEdge.setAction(addEdge);
		
		// RELEVANT PANEL IN THE CENTER, BLANK PANELS AS BORDERS
		add(new EmptyPanel() , BorderLayout.NORTH);
		add(new EmptyPanel() , BorderLayout.SOUTH);
		add(new EmptyPanel() , BorderLayout.EAST);
		add(new EmptyPanel() , BorderLayout.WEST);
		add(thePanel , BorderLayout.CENTER);
		
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


}