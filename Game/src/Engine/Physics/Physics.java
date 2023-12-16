package Engine.Physics;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;

import Engine.Object.GameObject;

public class Physics 
{
	public static Collider[] checkBox(Rectangle rect)
	{
		List<Collider> cols = getColliders();
		for(Collider c : cols)
		{
			BoxCollider cBox = (BoxCollider)c;
			if(cBox != null)
			{
				Rectangle cRect = new Rectangle(c.position, cBox.size);
				if(rect.intersects(cRect))
				{
					cols.add(c);
				}
			}
		}
		
		return cols.toArray(new Collider[0]);
	}
	
	public static List<Collider> getColliders()
	{
		List<Collider> cols = new ArrayList<Collider>();
		for(Entry<String, GameObject> obj : GameObject.globalObjects.entrySet())
		{
			Collider col = (Collider) obj.getValue().getComponent("Collider");
			if(col != null)
				cols.add(col);
		}
		
		return cols;
	}
}
