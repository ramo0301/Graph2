package current;

import java.util.Scanner;

import javax.swing.JFrame;


public class GraphFrame extends JFrame {
	
	public static final int FRAME_WIDTH = 1000;
	public static final int FRAME_HEIGHT = 500;
	
	public GraphFrame(){
		
		super("Graph Frame");
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}
