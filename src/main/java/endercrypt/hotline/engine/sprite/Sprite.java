package endercrypt.hotline.engine.sprite;


import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;

import net.ddns.endercrypt.library.position.Position;


public class Sprite implements Serializable
{
	private static final long serialVersionUID = -4503798745690910893L;
	/**
	 * 
	 */
	
	private String file;
	
	private transient SpriteData spriteData;
	
	public Sprite(String file)
	{
		SpriteManager.getSpriteData(Paths.get(file));
		this.file = file;
	}
	
	public Path getPath()
	{
		return Paths.get(file);
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
