package State;

import java.awt.Graphics2D;


//State of the game
//Not finished
//Will revamp later
public abstract class State 
{
	public abstract void tick();
	
	public abstract void render(Graphics2D g);
	
	public abstract void awake();
	
	public State()
	{
		
	}
	
	//State Manager
	
	static State currentState = null;
	
	
	
	public static void setState(State s)
	{
		currentState = s;
		
	}
	
	public static State getState()
	{
		return currentState;
	}
}
