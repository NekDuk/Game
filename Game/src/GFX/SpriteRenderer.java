package GFX;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Point;
import java.awt.Transparency;
import java.awt.image.BufferedImage;

import Engine.Object.Component;
import Engine.Object.Handler;

public class SpriteRenderer extends Component
{
	
	public SpriteRenderer(String assetId) 
	{
		sprite = Assets.get(assetId);
		pos = new Point(0, 0);
		size = new Dimension(0, 0);
		rot = 0;
	}
	@Override
	public void initVars() 
	{
		addVar("spriteId", "null");
	}

	Sprite sprite;
	Point pos;
	Point realPos;
	double rot;
	Dimension size;
	boolean onScreen;
	Camera cam;
	
	public Sprite getImage() {
		return sprite;
	}

	public void setImage(Sprite image) {
		sprite = image;
	}


	@Override
	protected void start() {
		// TODO Auto-generated method stub
		cam = Handler.getCamera();
		
	}

	@Override
	protected void update() 
	{
		
		/*Point cPos = cam.getPosition();
		int margin = 20;
		int xDist = Math.abs(cPos.x - transform().position.x);
		int yDist = Math.abs(cPos.y - transform().position.y);
		Dimension s = Camera.getScreenSize();
		onScreen = !(
				xDist - margin > s.width / 2 ||
				-xDist + margin + size.width < -s.height / 2 ||
				yDist - margin > s.height / 2 ||
				-yDist + margin + size.height < -s.height / 2
				);*/	
		
		realPos = new Point(transform().position.x - (int)cam.getXOffset(), transform().position.y - (int)cam.getYOffset());
		Point posT = transform().position;
		pos.x = (int) (posT.x - cam.getXOffset()) - size.width / 2; 
		pos.y = (int) (posT.y - cam.getYOffset()) - size.height / 2;
		
		size.width = (sprite.image.getWidth() / sprite.resolution) * transform().size.width;
		size.height = (sprite.image.getHeight() / sprite.resolution) * transform().size.height;
		rot = transform().rotation;
	}

	@Override
	public void render(Graphics2D g) 
	{
		/*if(!onScreen)
			return;*/
		
		g.drawImage(rotate(sprite.image, rot, g), pos.x, pos.y, size.width, size.height, null);
	}
	
	public void showRealCenter(Graphics2D g)
	{
		Dimension size = new Dimension(30, 30);
		g.drawOval(realPos.x - size.width / 2, realPos.y - size.height / 2, size.width, size.height);
	}
	
	public void showCollider(Dimension bounds, Graphics2D g)
	{
		g.drawRect(realPos.x - bounds.width / 2, realPos.y - bounds.height / 2, bounds.width, bounds.height);
	}
	
	public static BufferedImage rotate(BufferedImage image, double angle, Graphics2D g) {
	    double sin = Math.abs(Math.sin(angle)),
	           cos = Math.abs(Math.cos(angle));
	    int w = image.getWidth(),
	        h = image.getHeight();
	    int neww = (int) Math.floor(w * cos + h * sin),
	        newh = (int) Math.floor(h * cos + w * sin);
	    GraphicsConfiguration gc = g.getDeviceConfiguration();
	    BufferedImage result =
	            gc.createCompatibleImage(neww, newh, Transparency.TRANSLUCENT);
	    Graphics2D g2d = result.createGraphics();
	    g2d.translate((neww - w) / 2, (newh - h) / 2);
	    g2d.rotate(angle, w / 2, h / 2);
	    g2d.drawRenderedImage(image, null);
	    g2d.dispose();
	    return result;
	}

}
