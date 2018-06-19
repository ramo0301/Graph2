package current;

import java.util.Scanner;

public class GraphEditor {

	public static void main(String[] args){
		GraphFrame theFrame = new GraphFrame();
		GraphModel theModel = new GraphModel();
		theFrame.setVisible(true);
		
		theModel.addVertex("Zero");
		theModel.addVertex("One");
		theModel.addVertex("Two");
		theModel.addVertex("Three");
		theModel.addEdge(0,1);
		theModel.addEdge(3,2);
		theModel.printVertexList();
		theModel.printEdgeList();
		
		//Scanner keyboard = new Scanner(System.in);
		//boolean continueInputLoop = true;
		//keyboard.close();
		
		/*
		while(continueInputLoop){
			System.out.println("Press 1 to see list of edges, 2 for list of vertices");
		}
		*/
	}
}
