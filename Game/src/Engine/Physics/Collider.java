package Engine.Physics;

import java.awt.Point;

import Engine.Object.Component;
import Engine.Object.GameObject;

public abstract class Collider extends Component
{
	Collider[] collisions;
	public Point position;
	//Object will automatically try to resolve its collision
	public boolean isTrigger;
	
	public boolean collided()
	{
		return collisions.length > 0;
	}
	
	public Collider[] getCollisions()
	{
		return collisions;
	}
	
	@Override
	protected void start()
	{
		setId("Collider", "INTENDED-COLLIDER");
	}
	
	//public void correctCollision()
}
