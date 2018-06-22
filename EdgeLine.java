package current;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

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
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3));
		
		g.drawLine(startX, startY, endX, endY);
	}
}
