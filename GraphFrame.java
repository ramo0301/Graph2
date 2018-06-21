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


public class GraphFrame extends JFrame {
	
	public static final int FRAME_WIDTH = 1000;
	public static final int FRAME_HEIGHT = 500;
	
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
		JMenuItem menuAddEdge = new JMenuItem("Add Edge");
		JMenuBar bar = new JMenuBar();
		
		theMenu.add(menuAddVertex);
		theMenu.add(menuAddEdge);
		bar.add(theMenu);
		setJMenuBar(bar);
		
		
		/* INITIALIZE ACTIONS AND SET TO MENU ITEMS
		 */
		Action addVertex = new AddVertexAction();
		Action addEdge = new AddEdgeAction();
		menuAddVertex.setAction(addVertex);
		menuAddEdge.setAction(addEdge);
		
		/* RELEVANT PANEL IN THE CENTER, BLANK PANELS AS BORDERS
		 */
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
		
		// DEFAULT CONSTRUCTOR
		public AddVertexAction(){
			super("Add Vertex");
		}
		
		/* ADDS A VERTEX TO THE MODEL,
		 */
		public void actionPerformed(ActionEvent e) {
			theModel.addVertex();
		}
	}
	
	
	private class AddEdgeAction extends AbstractAction {
		
		public AddEdgeAction(){
			super("Add Edge");
		}
		
		/* SET THE MOUSELISTENERS TO THE SETTING THAT MAKES IT SO THAT
		 * WHEN THE NEXT TWO VERTICES HAVE BEEN SELECTED, AN EDGE WILL BE DRAWN BETWEEN THEM.
		 */
		public void actionPerformed(ActionEvent e) {
			System.out.println("action performed");
			thePanel.setMouseSetting("edge");
		}
		
	}

}