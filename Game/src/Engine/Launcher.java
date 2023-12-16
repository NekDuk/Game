package Engine;

import java.awt.Dimension;

import Engine.Object.Game;

//Launches the game?
//Not much to say honestly
public class Launcher 
{
	public static void main(String[] args) 
	{
		//makes game with set screen size
		//NOTE: dont touch the size unless you know what your doing (it will mess up a lot of measurements)
		Game game = new Game(1280, 720, "Game");
		game.start();
	}
}
