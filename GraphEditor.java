package current;

//VERSION 48

public class GraphEditor {

	public static void main(String[] args){
		GraphModel theModel = new GraphModel();
		GraphFrame theFrame = new GraphFrame(theModel);
		theFrame.setVisible(true);
	}
}
