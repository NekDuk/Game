package State;

import java.awt.Graphics2D;
import java.awt.Point;

import Engine.Object.GameObject;
import GFX.SpriteRenderer;
import Game.Player;

public class GameState extends State
{
	public void awake()
	{
		
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		GameObject.globalUpdate();
		
	}

	@Override
	public void render(Graphics2D g) {
		// TODO Auto-generated method stub
		GameObject.globalRender(g);
	}

}
