package Engine.Object;

import Engine.KeyManager;
import GFX.Camera;

public class Handler 
{
	static Game game;
	static Camera cam;
	static KeyManager key;
	
	public static void init(Game g, Camera c)
	{
		game = g;
		cam = c;
	}
	public static int getWidth() {
		return game.getSize().width;
	}
	public static int getHeight() {
		return game.getSize().height;
	}
	public static Camera getCamera() {
		return cam;
	}
	public static KeyManager getKey() {
		return key;
	}
}
