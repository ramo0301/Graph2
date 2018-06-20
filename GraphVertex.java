package current;

import java.awt.Rectangle;

public class GraphVertex extends Rectangle{
	public static final int DEFAULT_REC_X = 100;
	public static final int DEFAULT_REC_Y = 100;
	public static final int DEFAULT_REC_WIDTH = 200;
	public static final int DEFAULT_REC_HEIGHT = 100;
	
	private String name = "default name";
	
	
	/*SIX DIFFERENT CONSTRUCTORS, EACH WITH DIFFERENT PARAMETERS
	 */
	public GraphVertex() {							//CONSTRUCTOR 1
		super(DEFAULT_REC_X,DEFAULT_REC_Y, DEFAULT_REC_WIDTH, DEFAULT_REC_HEIGHT);
	}
	
	public GraphVertex(String inputName){			//CONSTRUCTOR 2
		super();
		name = inputName;
	}
	
	public GraphVertex(int index){					//CONSTRUCTOR 3
		super(DEFAULT_REC_WIDTH, DEFAULT_REC_HEIGHT);

		int xCenter = GraphFrame.FRAME_WIDTH/2-100, yCenter = GraphFrame.FRAME_HEIGHT/2-80;
		int radius = 150; //WILL BE USED TO PUT ITEMS IN AN OVAL (NOT A CIRCLE)
		double angle = 4.0/7*Math.PI*index; // 7 VERTICES FIT. AFTER MAKING 2 ROTATIONS, THE VERTICES WILL OVERLAP.
		
		int x = (int)Math.round( xCenter + 2* radius*Math.cos(angle) );
		int y = (int)Math.round( yCenter + radius*Math.sin(angle) );
		setLocation(x, y);
	}
	
	public GraphVertex(int width, int height){					//CONSTRUCTOR 4
		super(DEFAULT_REC_X,DEFAULT_REC_Y, width, height);
	}
	
	public GraphVertex(int x, int y, int width, int height){	//CONSTRUCTOR 5	
		super(x, y, width, height);
	}
	
	public GraphVertex(String string, int x, int y){			//CONSTRUCTOR 6
		/* the string is just a dummy parameter to differentiate
		 * the constructor from GraphVertex(int width, int height)*/
		super(x, y, DEFAULT_REC_WIDTH, DEFAULT_REC_HEIGHT);		
	}
	
	
	/* GETTER AND SETTER FOR NAME
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString(){
		String returnString = "name: " + name;
		return returnString;
	}
	
	
}
