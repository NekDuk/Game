package Engine.Object;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import Utility.Util;
import Utility.Util.Message;

//An abstract class that allows a script to be added and interact with a game object
public abstract class Component 
{
	String id;
	Map<String, Object> vars = new HashMap<String, Object>();
	
	public class variable
	{
		String name;
		Object var;
	}
	
	public Object getVar(String s)
	{
		return vars.get(s);
	}
	
	public Map<String, Object> getVars()
	{
		return vars;
	}
	
	public void addVar(String name, Object value)
	{
		vars.put(name, value);
	}
	void genId()
	{
		id = getClass().getSimpleName();
	}
	
	public void setId(String _id, String reason)
	{
		Util.log("Ovveriding component " + id + " ID with ", _id, Message.WARNING, reason + "-COMMPONENT");
		id = _id;
	}
	
	public String getId()
	{
		return id;
	}
	
	boolean enable = false;
	GameObject transform;
	public GameObject transform()
	{
		return transform;
	}
	public void move(Point amt)
	{
		transform.move(amt);
	}
	public void init(GameObject t)
	{
		transform = t;
		genId();
	}
	
	//start is called once when the game object is created
	protected abstract void start();
	
	//update is called constantly as long as the game object or component is enabled
	protected abstract void update();
	
	public abstract void initVars();
	
	public void tick()
	{
		if(enable)
			update();
	}
	
	public void enable()
	{
		enable = true;
	}
	public void disable()
	{
		enable = false;
	}
	public abstract void render(Graphics2D g);
}
