package GFX;

import java.awt.image.BufferedImage;

public class Sprite 
{
	public BufferedImage image;
	public int resolution;
	public String name;
	
	public Sprite(BufferedImage img, int res, String _name)
	{
		image = img;
		resolution = res;
		name = _name;
	}
}
