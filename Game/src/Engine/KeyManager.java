package Engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Utility.Util;


//THIS NEEDS TO BE COMPLETELY REVAMPED
//TOO LAZY TO DO IT NOW
//IF YOU ARE READING THIS REMIND INZO TO FIX IT
public class KeyManager implements KeyListener
{
	public static KeyManager input;
	public static boolean[] keys;
	public static enum Axis
	{
		HORIZONTAL,
		VERTICAL,
		MOUSE_X,
		MOUSE_Y
	}
	public static void init()
	{
		keys = new boolean[256];
	}
	
	public static int getAxis(Axis a)
	{
		switch(a)
		{
			case HORIZONTAL:
				boolean right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
				boolean left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
				
				return (right ? 1 : 0) + (left ? -1 : 0);
				
			case VERTICAL:
				boolean up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
				boolean down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
				
				return (up ? -1 : 0) + (down ? 1 : 0);
			default:
				return 0;
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) 
	{
		// TODO Auto-generated method stub
		keys[e.getKeyCode()] = true;
	}
	@Override
	public void keyReleased(KeyEvent e) 
	{
		// TODO Auto-generated method stub
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
