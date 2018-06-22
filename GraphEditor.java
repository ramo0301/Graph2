package grapheditor;

import java.io.IOException;

//VERSION 49

public class GraphEditor {

	public static void main(String[] args) {
		GraphModel theModel = new GraphModel();
		if (args.length!= 0 ){
				GraphFrame theFrame;
				try {
					theFrame = new GraphFrame(args[0]);
					theFrame.setVisible(true);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		}else{
			GraphFrame theFrame = new GraphFrame(theModel);
			theFrame.setVisible(true);
		}
	}
}
