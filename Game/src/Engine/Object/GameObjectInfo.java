package Engine.Object;

import java.awt.Dimension;

//Holds the basic information of a game object so that it can be spawned
//(AKA preset info)
public class GameObjectInfo 
{
	public String name;
	public String spriteId;
	public Component[] components;
	
	public GameObjectInfo(String _name, String _spriteId, Component[] _components)
	{
		name = _name;
		spriteId = _spriteId;
		components = _components;
	}
}
