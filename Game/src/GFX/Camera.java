package GFX;

import java.awt.Dimension;
import java.awt.Point;

import Engine.Object.Handler;

public class Camera 
{
	float xOffset;
	float yOffset;
	
	public void move(float xAmt, float yAmt)
	{
		xOffset += xAmt;
		yOffset += yAmt;
	}
	
	public void setPosition(float x, float y)
	{
		xOffset = x;
		yOffset = y;
	}
	
	public void setPosition(Point pos)
	{
		xOffset = pos.x;
		yOffset = pos.y;
	}
	
	
	//Pre offsets the screen so that it centers at the offset
	public float getXOffset() {
		return xOffset - Handler.getWidth() / 2;
	}

	public float getYOffset() {
		return yOffset - Handler.getHeight() / 2;
	}
	
	public Point getPosition()
	{
		return new Point((int)xOffset, (int)yOffset);
	}
}
