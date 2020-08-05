package endercrypt.hotline.engine.sprite;


import java.awt.geom.AffineTransform;
import java.io.Serializable;

import endercrypt.hotline.engine.graphics.HotlineGraphics;
import net.ddns.endercrypt.library.position.Position;


public class Sprite implements Serializable
{
	private static final long serialVersionUID = -4503798745690910893L;
	/**
	 * 
	 */
	
	private final String path;
	
	private transient SpriteData spriteData;
	
	public Sprite(String path)
	{
		this.path = path;
	}
	
	private boolean restoreSprite()
	{
		if (spriteData != null)
		{
			return false;
		}
		spriteData = SpriteManager.getSpriteData(getPath());
		return true;
	}
	
	public String getPath()
	{
		return path;
	}
	
	public void draw(HotlineGraphics graphics, Position position, SpriteInfo spriteInfo)
	{
		// sprite data
		restoreSprite();
		
		// transform
		AffineTransform transform = spriteInfo.generateTransform(position, spriteData);
		
		// draw
		graphics.drawImage(spriteData.getImage(), transform);
	}
}
