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
	
	private Path path;
	
	private transient SpriteData spriteData;
	
	public Sprite(String path)
	{
		this(Paths.get(path));
	}
	
	private Sprite(Path path)
	{
		this.path = path;
	}
	
	public Path getPath()
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
		AffineTransform transform = spriteInfo.generateTransform(position, spriteInfo, spriteData);
		
		// draw
		g2d.drawImage(spriteData.getImage(), transform, null);
	}
	
}
