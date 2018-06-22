package current;

import java.awt.BasicStroke;
import java.util.Observable;
import java.util.Observer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GraphPanel extends JPanel implements ActionListener, Observer {
	
	private static final int NUMBER_OF_CHAR = 20;
	
	/*CONSTRUCTOR, WITH theModel AS ARGUMENT SO THAT WE CAN ACCESS THE ARRAYLISTS vertexList AND graphList.
	 */
	private GraphModel theModel = null;
	private SelectionController mouse;
	private JTextField textField;
	
	public GraphPanel(GraphModel theModel){
		super();
		setBackground(Color.LIGHT_GRAY);
		setLayout(new BorderLayout());
		
		//textField added to the bottom, if entered is fired the actionPerformed method at the bottom is called.
		textField = new JTextField(NUMBER_OF_CHAR);
		textField.setEditable(false);
		textField.addActionListener(this);
		add(textField, BorderLayout.SOUTH);
		
		/* include the model so we can access it, and make it an observer. Model is also input to mouse.
		 */
		this.theModel = theModel;
		theModel.addObserver(this);
		mouse = new SelectionController(this, theModel);
	}
	
	
	public void setVertexNameEditable(boolean bool){
		textField.setEditable(bool);
	}
	
	public void setVertexName(String string){
		textField.setText(string);
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
		repaint();
		if(theModel.getSelectedVertexIndex() == -1){
			textField.setText("");
			textField.setEditable(false);
		}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		//draw a line to the mouse (only while adding vertex)
		theModel.drawLine(g);
		//draw all edges
		theModel.drawAllEdges(g);
		//draw all vertices
		theModel.drawAllVertices(g);
		
	}


	@Override
	/* called after pressing enter in the text field
	 */
	public void actionPerformed(ActionEvent arg0) {
		String text = textField.getText();
		theModel.getVertexAtIndex(theModel.getSelectedVertexIndex()).setName(text); //set the name of the selected vertex
		textField.setEditable(false);
		repaint();
		System.out.println(text);
	}
}