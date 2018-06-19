package current;

import java.awt.Rectangle;

public class GraphVertex extends Rectangle{
	public static final int DEFAULT_REC_X = 100;
	public static final int DEFAULT_REC_Y = 100;
	public static final int DEFAULT_REC_WIDTH = 300;
	public static final int DEFAULT_REC_HEIGHT = 200;
	
	private String name = "default name extra     letters";
	
	
	/*FIVE DIFFERENT CONSTRUCTORS, EACH WITH DIFFERENT PARAMETERS
	 */
	public GraphVertex() {
		super(DEFAULT_REC_X,DEFAULT_REC_Y, DEFAULT_REC_WIDTH, DEFAULT_REC_HEIGHT);
	}
	
	public GraphVertex(String inputName){
		super();
		name = inputName;
	}
	
	public GraphVertex(int width, int height){
		super(DEFAULT_REC_X,DEFAULT_REC_Y, width, height);
	}
	
	public GraphVertex(int x, int y, int width, int height){
		super(x, y, width, height);
	}
	
	public GraphVertex(String string, int x, int y){
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
