package endercrypt.hotline.engine.sprite;


import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.nio.file.Path;

import net.ddns.endercrypt.library.position.Position;


public class Sprite implements Serializable
{
	private static final long serialVersionUID = -4503798745690910893L;
	/**
	 * 
	 */
	
	private Path file;
	
	private Position center = new Position(0.0, 0.0);
	
	private transient BufferedImage image;
	
	private Sprite(Path file)
	{
		this.file = file;
	}
	
	public Path getFile()
	{
		return file;
	}
	
	public void setCenter(double x, double y)
	{
		center.x = x;
		center.y = y;
	}
	
	public Position getCenter()
	{
		return center;
	}
	
	public void draw(Graphics2D g2d, AffineTransform transform)
	{
		if (image == null)
		{
			image = SpriteManager.getSpriteData(file).getImage();
		}
		g2d.drawImage(image, transform, null);
	}
	
}
