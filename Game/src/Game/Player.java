package Game;

import java.awt.Graphics2D;
import java.awt.Point;

import Engine.KeyManager;
import Engine.KeyManager.Axis;
import Engine.Object.Component;
import Engine.Object.Handler;
import GFX.SpriteRenderer;
import Utility.Util;

public class Player extends Component
{
	@Override
	public void initVars() 
	{
		addVar("speed", 5);
		addVar("smoothSpeed", .04);
	}
	//Runs once when the game first opens
	protected void start()
	{
		
	}
	
	int speed = (int) getVar("speed");
	double smooth = (double) getVar("smoothSpeed");
	//Repeatedly runs as long as the game is running
	protected void update()
	{
		int horizontal = KeyManager.getAxis(Axis.HORIZONTAL);
		int vertical = KeyManager.getAxis(Axis.VERTICAL);
		
		Point move = new Point(speed * horizontal, speed * vertical);
		move(move);
		
		Handler.getCamera().setPosition(Util.lerpPoint(Handler.getCamera().getPosition(), transform().position, smooth));
								
	}


	@Override
	public void render(Graphics2D g) 
	{
		
	}



}
