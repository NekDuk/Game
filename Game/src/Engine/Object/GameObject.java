package Engine.Object;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import GFX.SpriteRenderer;
import Game.Player;
import Utility.Util;

/* 
 * A GameObject
 * Every thing in the game can be split into 2 main parts: the object and the script
 * the object (or gameobject) is an invisible object that contains all your scripts
 * the gameobject also allows you move, rotate, resize, and do more stuff to an image
 * the gameobject is invisible until you add a specific script (See Sprite) that will allow it to be displayed on Screen
 */
public class GameObject 
{
	public String name;
	public Point position;
	public double rotation;
	public Dimension size;
	public List<Component> scripts = new ArrayList<Component>();
	
	//use `move` if you want to calculate for collisions
	public void move(Point amt)
	{
		position.x += amt.x;
		position.y += amt.y;
	}
	
	public Component getComponent(String c)
	{
		for(Component comp : scripts)
		{
			if(comp.getId().compareTo(c) == 0)
			{
				return comp;
			}
		}
		
		Util.log("Component of type " + c.toString() + " does not exist on object ", name, Util.Message.WARNING, "GAMEOBJECT");
		return null;
	}
	
	//Creating a new game object
	public GameObject(String _name)
	{
		init(_name, new Point(0, 0), 0);
	}
	public GameObject(String _name, Point _position, double _rotation)
	{
		init(_name, _position, _rotation);
	}
	void init(String _name, Point _position, double _rotation)
	{
		name = _name;
		position = _position;
		rotation = _rotation;
		size = new Dimension(1, 1);
		globalObjects.put(_name, this);
		
		active = true;
	}
	
	public void start()
	{
		for(Component c : scripts)
		{
			c.enable();
			c.start();
		}
	}
	
	public void tick()
	{
		if(active)
		{
			for(Component c : scripts)
			{
				c.tick();
			}
		}
	}
	
	public void render(Graphics2D g)
	{
		for(Component c : scripts)
		{
			c.render(g);
		}
	}
	
	public void destroy()
	{
		globalObjects.remove(this);
	}
	
	public void setActive(boolean state)
	{
		active = state;
	}
	
	//adds a component directly to the object
	//Not advisable to use unless you want to add components dynamically (Added during run time)
	public void addComponent(Component c)
	{
		scripts.add(c);
		c.transform = this;
	}
	
	/*
	 * Game Object Manager
	 * The code below this is incharge of managing all the game objects
	************************************************************/
	public static Map<String, GameObject> globalObjects;
	boolean active = false;
	
	public static void init()
	{
		Util.log("Initializing game objects...", "GAMEOBJECT");
		globalObjects = new HashMap<String, GameObject>();
		
		try {
			initPrefabs();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Util.logS("GameObjects initialized!", "GAMEOBJECT");
	}	
	
	public static boolean checkCollision(GameObject a, GameObject b)
	{
		return false;
	}
	
	public static GameObject checkCollsionAll(GameObject a)
	{
		return null;
	}
	
	//Call the 'update' function for all the objects
	public static void globalUpdate()
	{
		for(Entry<String, GameObject> obj : globalObjects.entrySet())
		{
			obj.getValue().tick();
		}
	}
	
	//Call the 'render' function for all the objects
	public static void globalRender(Graphics2D g)
	{
		for(Entry<String, GameObject> obj : globalObjects.entrySet())
		{
			obj.getValue().render(g);
		}
	}
	
	/*
	 * A Map* of all the spawnable prefabs*
	 * *A map in java is list of items that you can get by giving an 'id'
	 * *Prefabs are preset gameobjects that you can quickly spawn
	 */
	static Map<String, GameObjectInfo> prefabs = new HashMap<String, GameObjectInfo>();
	
	//Spawns a prefab at the location and rotation specified
	public static GameObject spawn(String prefabId, Point position, double rotation)
	{
		if(!prefabs.containsKey(prefabId))
		{
			Util.log("Prefab ID does not exist! ", prefabId, Util.Message.ERROR, "GAMEOBJECT");
			return null;
		}
		
		/*
		 * TLDR;
		 * Grabs the info
		 * Sets the main info (eg. Name, id, position, etc)
		 * Creates a new instance of components that should be on it
		 * Initializes the components (init comps, adds comps to list)
		 */
		GameObjectInfo info = prefabs.get(prefabId);
		GameObject obj = new GameObject(info.name, position, rotation);
		
		for(Component c : info.components)
		{
			c.init(obj);
			obj.addComponent(c);
		}
		
		if(!info.spriteId.isBlank())
		{
			Component c = new SpriteRenderer(info.spriteId);
			c.init(obj);
			obj.addComponent(c);
		}
		
		obj.start();
		return obj;
	}
	
	//Initializes the preset objects (Prefabs)
	//NOTE: This gets kinda complicated
	public static void initPrefabs() throws Exception
	{
		Util.log("Loading prefabs...", "GAMEOBJECT");
		//Creates a reference to where to get the objects from
		//NOTE: DO NOT touch this unless you change its directory
		//Sidenote: All prefab objects are in `.pfb` which means absolutely nothing, it just looks cool
		File dir = new File("res/game_objects");
		File[] pfabs = dir.listFiles();
		
		//goes through all the files in the folder
		for(File p : pfabs)
		{
			/*
			 * The main info of the object
			 * [0] ID
			 * [1] Name
			 * [2] Sprite ID
			 */
			//A list of the components to add 
			List<Component> comps = new ArrayList<Component>();
			
			//The individual parts of the data
			String[] data = Util.loadInfo(Util.loadFileAsString(p.getPath()), ":|;", new String[] {"ID", "Name", "SpriteID", "Components"});
			//All the components to add in string form
			String compStr = data[3];
			Util.log("Load prefab from file with data: ", "\n "
					+ "name: " + data[1]
					+ "\n id: " + data[0]
					+ "\n sprite id:" + data[2], Util.Message.MESSAGE, "GAMEOBJECT");
			
			Util.log("Loading components of", data[1], Util.Message.MESSAGE, "GAMEOBJECT");
			//Further splits the component string into the individual components (still in string)
			String[] strComps = compStr.split(">");
			for(String s : strComps)
			{	
				s = s.trim();
				//Same as before
				if(s.isBlank())
					continue;
				
				String[] cVars = s.split("#");
			
				//Turns the string into a path where the actual component is located
			    String className = "Game." + cVars[0].trim();
			    System.out.println(className);
			    //Creates a blank class, gets a constructor, and creates a new instant of class and passes it as the compoent
			    //Sidenote: Honestly I dont fully understand how it works; It just works
			    Class<?> clazz = Class.forName(className);                                                                
			    Component c = (Component)clazz.getConstructor().newInstance();
				c.initVars();
				
				enum VariableType 
				{
					INTEGER,
					DOUBLE,
					STRING
				}
				for(int i = 1; i < cVars.length; i++)
				{
					String[] varInfo = cVars[i].split("=");
					VariableType varType = VariableType.INTEGER;
					
					switch(varInfo[1])
					{
						case "Integer":
							varType = VariableType.INTEGER;
							break;
						case "Double":
							varType = VariableType.DOUBLE;
							break;
						case "String":
							varType = VariableType.STRING;
							break;
							
					}
					
					///IDK GL in understandin this
					for(Entry<String, Object> var : c.getVars().entrySet())
					{
						if(varInfo[0].compareTo(var.getKey()) == 0)
						{
							switch(varType)
							{
								case INTEGER:
									var.setValue(Util.parseInt(varInfo[1]));
									break;
								case DOUBLE:
									var.setValue(Util.parseDouble(varInfo[1]));
									break;
								case STRING:
									var.setValue(varInfo[1]);
									break;
							}
						}
					}
				}
				comps.add(c);
				Util.log("Loaded component", c.toString(), Util.Message.MESSAGE, "GAMEOBJECT");
			}

			//Adds all the info from the file into it and adds it to the list of prefabs
			prefabs.put(data[0], new GameObjectInfo(data[1], data[2], comps.toArray(new Component[0])));
			Util.log("Loaded prefab", data[1] + "!", Util.Message.MESSAGE, "GAMEOBJECT");
		}
			Util.logS("Loaded all prefabs!", "GAMEOBJECT");
	}
	
	
}
