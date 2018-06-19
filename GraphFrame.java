package current;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


public class GraphFrame extends JFrame {
	
	public static final int FRAME_WIDTH = 1000;
	public static final int FRAME_HEIGHT = 500;
	
	GraphPanel thePanel = new GraphPanel();
	
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
		JMenuBar bar = new JMenuBar();
		
		theMenu.add(menuAddVertex);
		bar.add(theMenu);
		setJMenuBar(bar);
		
		
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

}
