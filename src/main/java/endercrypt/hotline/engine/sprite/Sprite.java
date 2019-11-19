package endercrypt.hotline.engine.sprite;


import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.io.Serializable;

import net.ddns.endercrypt.library.position.Position;


public class Sprite implements Serializable
{
	private static final long serialVersionUID = -4503798745690910893L;
	/**
	 * 
	 */
	
	private String path;
	
	private transient SpriteData spriteData;
	
	public Sprite(String path)
	{
		this.path = path;
		SpriteManager.getSpriteData(path);
	}
	
	public String getPath()
	{
		return path;
	}
	
	public void draw(Graphics2D g2d, Position position, SpriteInfo spriteInfo)
	{
		// sprite data
		if (spriteData == null)
		{
			spriteData = SpriteManager.getSpriteData(getPath());
		}
		
		// transform
		AffineTransform transform = spriteInfo.generateTransform(position, spriteData);
		
		// draw
		g2d.drawImage(spriteData.getImage(), transform, null);
	}
	
}
