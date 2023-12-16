package Engine.Object;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import Engine.KeyManager;
import GFX.*;
import Game.*;
import State.*;
import Utility.Util;

public class Game implements Runnable
{
	Display display;
	Dimension size;
	public Dimension getSize()
	{
		return size;
	}
	Thread thread;
	
	boolean running = false;
	String title;
	
	BufferStrategy bs;
	Graphics2D g;
	
	State gameState;
	
	public Game(int width, int height, String _title)
	{
		size = new Dimension(width, height);
		title = _title;
	}
	
	//Intializes everything
	void init()
	{
		Util.log("Intializing game...", "GAME");
		Util.init();
		Camera cam = new Camera();
		Handler.init(this, cam);
		GameObject.init();
		try {
			Assets.init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		KeyManager.init();
		display = new Display(size, title);
		GameManager.start();
		
		gameState = new GameState();
		gameState.awake();
		State.setState(gameState);
		Util.logS("Intializing handler...", "GAME");
		Util.logS("Intializing camera...", "GAME");
		
		Util.logS("Game intialized!", "GAME");
	}
	
	private void update()
	{	
		if(State.getState() != null)
			State.getState().tick();
		}
	
	//Creates a buffer strategy to display the graphics on the screen
	private void render() 
	{
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null)
		{
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		
		g = (Graphics2D) bs.getDrawGraphics();
		g.clearRect(0, 0, size.width, size.height);
		
		if(State.getState() != null)
			State.getState().render(g);
		
		bs.show();
		g.dispose();
	}
	
	int fps;
	public void run()
	{
		init();
		
		//Does the math to ensure code is the same regardless of the FPS
		int maxFps = 60;
		double timePerTick = 1000000000 / maxFps; //Maximum nanoseconds per frame
		double delta = 0; //Timer to know when to update
		long now; //current time
		long lastTime = System.nanoTime(); //time of the previous frame
		long timer = 0;
		int ticks = 0;
		
		while(running)
		{
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1)
			{
				update();
				render();		
				ticks++;
				delta--;
			}
			
			if(timer >= 1)
			{
				fps = ticks;
				ticks = 0;
			}
		}
		
		stop();
	}
	
	public synchronized void start()
	{
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
		Util.log("Game starting...", "GAME");
	}
	
	public synchronized void stop()
	{
		if(!running)
			return;
		
		try {
			thread.join();
			Util.log("","Game closed!", Util.Message.MESSAGE, "GAME");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
