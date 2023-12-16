package Game;

import java.awt.Graphics2D;
import java.awt.Point;

import Engine.Object.Component;
import GFX.SpriteRenderer;
import Utility.Util;

public class Obstacle extends Component
{

	@Override
	protected void start() {
		// TODO Auto-generated method stub
		
	}

	boolean turn = false;
	@Override
	protected void update() {
		// TODO Auto-generated method stub
		int x = 2;
		
		if(transform().position.x > 200)
		{
			turn = false;
		}else if(transform().position.x < -200)
		{
			turn = true;
		}
		
		if(turn)
			transform().move(new Point(x, 0));
		else
			transform().move(new Point(-x, 0));
	}

	@Override
	public void render(Graphics2D g) {
	}

	@Override
	public void initVars() {
		// TODO Auto-generated method stub
		
	}

}
