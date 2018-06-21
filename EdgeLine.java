package current;

import java.awt.Graphics;

public class EdgeLine {
	int startX=0, startY=0, endX=100, endY=100;
	
	public EdgeLine(){
	}
	
	public EdgeLine(int inputStartX, int inputStartY, int inputEndX, int inputEndY){
		startX = inputStartX;
		startY = inputStartY;
		endX = inputEndX;
		endY = inputEndY;
	}
	
	public void setCoordinates(int inputStartX, int inputStartY, int inputEndX, int inputEndY){
		startX = inputStartX;
		startY = inputStartY;
		endX = inputEndX;
		endY = inputEndY;
	}
	
	public void setCoordinates(int inputEndX, int inputEndY){
		endX = inputEndX;
		endY = inputEndY;
	}
	
	public void drawLine(Graphics g){
		g.drawLine(startX, startY, endX, endY);
	}
}
