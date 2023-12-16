package Game;

import java.awt.Point;

import Engine.Object.GameObject;


//In charge of managing you own scripts
//Put whatever you wnat in here
public class GameManager 
{
	public static void start()
	{
		//Do some cool setup code here
		GameObject.spawn("player", new Point(0, 0), 0);
		GameObject.spawn("tri", new Point(100, 100), 0);
	}
}
